package com.buas_team.buas_backend2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@Slf4j
public class RiskAndPredictConfig {
    @Scheduled(cron = "0 0 0/2 * * ?") //每两个小时的整点执行一次 比如16:00:00秒
    private void configureTasks(){
        log.debug("执行python定时任务的时间："+ LocalDateTime.now());
        try{
            log.info("start Risk Assessment");
            //路径为python脚本绝对路径
            Process pr = Runtime.getRuntime().exec("python C:\\Users\\KALA\\Desktop\\data_analysis\\bikmeans.py");
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            log.info("end");
        } catch (Exception e){
            log.info("Risk Assessment error");
            e.printStackTrace();
        }

        try{
            System.out.println("start Predict");
            //路径为python脚本绝对路径
            Process pr = Runtime.getRuntime().exec("python C:\\Users\\KALA\\Desktop\\data_analysis\\ARIMA-province.py");
            System.out.println(2);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    String line;
                    try {
                        BufferedReader stderr = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
                        while ((line = stderr.readLine()) != null) {
                            System.out.println("stderr:" + line);
                        }
                    }
                    catch (Exception e) {

                    }

                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    String line;
                    try {
                        BufferedReader stdout = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                        while ((line = stdout.readLine()) != null) {
                            System.out.println("stdout:" + line);
                        }
                    }
                    catch (Exception e) {

                    }
                }
            }.start();
            int exitVal = pr.waitFor();
            if (0 != exitVal) {
                System.out.println("执行预测脚本失败");
            }
            System.out.println("执行预测脚本成功");
//            BufferedReader in = new BufferedReader(new
//                    InputStreamReader(pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            pr.waitFor();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
