package com.example.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
/**
 * Created by Administrator on 2016/9/14.
 */
public class FunctionUtils {

    public static void lockWindows(Robot robot, int delay) {
        if (robot == null) {
            return;
        }
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_L);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.delay(delay);
    }


    public static void showWindow(Robot robot, int delay) {
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_D);
        robot.delay(delay);

    }


    public static void main(String args[]) {
        try {
            Robot robot = new Robot();
            showWindow(robot, 1000);
            lockWindows(robot, 1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


}
