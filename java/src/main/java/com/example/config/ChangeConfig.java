package com.example.config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ChangeConfig {


    /**
     * 获取主机名   和ip地址
     */
    public static void getIpAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String adr = address.getHostAddress();
            String name = address.getHostName();
            System.out.print("ad:" + adr + " n:" + name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public static void main(String arge[]) {
        Process pro = null;
        try {
            pro = Runtime.getRuntime().exec("C:\\Users\\Administrator\\Desktop\\alc.bat");
            pro.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int value = pro.exitValue();
        if (value == 0) {
            System.out.print("执行完毕");
        } else {
            System.out.print("执行失败");
        }
        pro.destroy();
        pro = null;
    }

    /**
     * 其中netsh中的参数255.255.255.0是指子网掩码，
     * 192.168.32.1 指的是默认网关，
     * 最后一个参数1指的是网关跳数  newip   "192.168.32.100"
     */
    public void changeIpAddress(String newip) {
        Process pro = null;
        try {
            pro = Runtime.getRuntime().exec("netsh interface ip set addr \"本地连接\" static " + newip + " 255.255.255.0 192.168.32.1 1");
            pro.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int value = pro.exitValue();
        if (value == 0) {
            System.out.print("执行完毕");
        } else {
            System.out.print("执行失败");
        }
        pro.destroy();
        pro = null;
    }


}
