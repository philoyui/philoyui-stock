package io.philoyui.qmier.qmiermanager.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MonthPythonAnalysisTimer {

    @Scheduled(cron="0 0/15 * * * ?") //每30分钟执行一次
    public void fetcher(){
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 /root/python/month_task.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
