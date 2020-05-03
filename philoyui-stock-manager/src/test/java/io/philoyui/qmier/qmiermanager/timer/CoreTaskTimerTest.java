package io.philoyui.qmier.qmiermanager.timer;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoreTaskTimerTest {


    @Test
    public void test_core(){
        Process process;
        try {
            process = Runtime.getRuntime().exec("C:\\Users\\DELL\\PycharmProjects\\untitled\\venv\\Scripts\\python C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\docker\\macd.py sh600000 Day");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}