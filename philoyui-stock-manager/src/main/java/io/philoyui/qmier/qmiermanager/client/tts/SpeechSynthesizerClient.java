package io.philoyui.qmier.qmiermanager.client.tts;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizer;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerListener;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerResponse;
import com.google.common.base.Splitter;
import io.philoyui.qmier.qmiermanager.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成声音文件
 */
public class SpeechSynthesizerClient {

    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizerClient.class);

    private static long startTime;

    private String appKey;

    private NlsClient client;

    public SpeechSynthesizerClient(String appKey, String accessKeyId, String accessKeySecret, String url) {
        this.appKey = appKey;
        AccessToken accessToken = new AccessToken(accessKeyId, accessKeySecret);
        try {
            accessToken.apply();
            System.out.println("get token: " + accessToken.getToken() + ", expire time: " + accessToken.getExpireTime());
            if(url.isEmpty()) {
                client = new NlsClient(accessToken.getToken());
            }else {
                client = new NlsClient(url, accessToken.getToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步响应结果
     * @return
     */
    private static SpeechSynthesizerListener getSynthesizerListener(String filePath) {

        SpeechSynthesizerListener listener = null;
        try {

            listener = new SpeechSynthesizerListener() {
                File file = new File(filePath);
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                @Override
                public void onComplete(SpeechSynthesizerResponse response) {
                    System.out.println("name: " + response.getName() + ", status: " + response.getStatus()+ ", output file :"+ file.getAbsolutePath());
                }

                @Override
                public void onMessage(ByteBuffer message) {
                    try {
                        byte[] bytesArray = new byte[message.remaining()];
                        message.get(bytesArray, 0, bytesArray.length);
                        fileOutputStream.write(bytesArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(SpeechSynthesizerResponse response){
                    System.out.println("task_id: " + response.getTaskId() + ", status: " + response.getStatus() + ", status_text: " + response.getStatusText());
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listener;
    }

    /**
     * 正文转wav文件
     * @param content
     * @param filePath
     */
    public void process(String content,String filePath) {

        List<String> listArr = new ArrayList<>();

        int index = 1;

        for (String partContent : Splitter.fixedLength(299).split(content)) {

            listArr.add(generateTempWav(filePath, index, partContent));

            index++;

        }

        if(listArr.size()==0){
            return;
        }

        joinTempFile(listArr, filePath);

        for (String file : listArr) {
            new File(file).delete();
        }

    }

    /**
     * 合并音频文件
     * @param listArr
     */
    private void joinTempFile(List<String> listArr, String filePath) {
        File fileOut = new File(filePath);
        if(listArr.size()==1){
            new File(listArr.get(0)).renameTo(fileOut);
        }else{
            try {
                AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File(listArr.get(0)));
                AudioInputStream audio2 = AudioSystem.getAudioInputStream(new File(listArr.get(1)));
                AudioInputStream audioBuild = new AudioInputStream(
                        new SequenceInputStream(audio1, audio2),
                        audio1.getFormat(),
                        audio1.getFrameLength() +
                                audio2.getFrameLength()
                );
                AudioInputStream audio3;
                //大于两个时继续合并
                for(int i = 2; i<listArr.size();i++){
                    audio3 = AudioSystem.getAudioInputStream(new File(listArr.get(i)));
                    audioBuild = new AudioInputStream(
                            new SequenceInputStream(audioBuild, audio3),
                            audioBuild.getFormat(), audioBuild.getFrameLength() +
                            audio3.getFrameLength()
                    );
                }
                //生成语音
                AudioSystem.write(audioBuild, AudioFileFormat.Type.WAVE, fileOut );
            } catch (IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateTempWav(String filePath, int index, String substring) {
        String tempFile = UploadUtils.getImgDirFile() + "/temp_" + filePath  + "_" + index + ".wav";
        SpeechSynthesizer synthesizer = null;
        try {
            synthesizer = new SpeechSynthesizer(client, getSynthesizerListener(tempFile));
            synthesizer.setAppKey(appKey);
            synthesizer.setFormat(OutputFormatEnum.WAV); //设置返回音频的编码格式
            synthesizer.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);//设置返回音频的采样率
            synthesizer.setVoice("siyue");//发音人
            synthesizer.setPitchRate(0);//语调，范围是-500~500，可选，默认是0
            synthesizer.setSpeechRate(0);//语速，范围是-500~500，默认是0
            synthesizer.setText(substring);//设置用于语音合成的文本
            synthesizer.start();
            synthesizer.waitForComplete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != synthesizer) {
                synthesizer.close();
            }
        }

        return tempFile;
    }

}
