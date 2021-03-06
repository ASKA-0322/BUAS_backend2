package com.buas_team.buas_backend2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.AreaCateVOMapper;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.mapper.ConsumRiskVOMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.vo.AreaCateVO;
import com.buas_team.buas_backend2.vo.ConsumRiskVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootTest
class BuasBackend2ApplicationTests {
    @Resource
    private AreaCateVOMapper areaCateVOMapper;
    @Resource
    private BankUserMapper bankUserMapper;
    @Resource
    private UserService userService;
    @Resource
    private BankUserService bankUserService;
    @Resource
    private ConsumRiskVOMapper consumRiskVOMapper;
    @Test
    void contextLoads() {
//        IPage<UserInfo> ipage = userService.page(new Page<>(1,1));
        IPage<BankUser> ipage = bankUserService.page(new Page<>(1,1));
        System.out.println(ipage);
    }
    @Test
    void t(){
        try{
            System.out.println("start");
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
            System.out.println("end");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void t1(){
        try{
            System.out.println("start");
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
                System.out.println("执行脚本失败");
            }
            System.out.println("执行脚本成功");
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
