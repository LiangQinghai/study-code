package cn.liangqinghai.study.concurrent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title ShellCommand
 * @ProjectName study-code
 * @Description
 * @date 2020/3/10 17:16
 */
public class ShellCommand {

    public static void main(String[] args) throws IOException {

        Process process = Runtime.getRuntime().exec("notepad.exe");

        try {
            boolean b = process.waitFor(20, TimeUnit.SECONDS);

            System.out.println(b);
            System.out.println("exit");

            Thread.sleep(20000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
