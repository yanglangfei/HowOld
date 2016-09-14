package com.example;

import com.example.utils.RobotUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyJava {

    private static Process pro;
    private static Robot robot;
    private static RobotUtils utils;
    private static String filePath = "C:\\Users\\Administrator\\Desktop\\alc.bat";
    private static int account[] = {KeyEvent.VK_2, KeyEvent.VK_4, KeyEvent.VK_9, KeyEvent.VK_5, KeyEvent.VK_5, KeyEvent.VK_5, KeyEvent.VK_1, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_1};
    private static int password[] = {KeyEvent.VK_W, KeyEvent.VK_Z, KeyEvent.VK_5, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_9, KeyEvent.VK_1, KeyEvent.VK_1, KeyEvent.VK_7};
    private static int qqs[] = {KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_Q};
    private static int res;

    public static void main(String args[]) {
        try {
            robot = new Robot();
            utils = RobotUtils.getInstance();
            // res=utils.openApplication(robot, filePath, 1000 * 2);
            utils.onKeyTogetherDown(robot, qqs, 1000 * 3);
            res = 0;
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (res == 0) {
            //2495551891
            robot.mouseMove(952, 582);
            robot.setAutoDelay(100);
            utils.clickLeftMouse(robot, 952, 582, 100);

            utils.doKeyWord(robot, account, 100);

            utils.clickLeftMouse(robot, 906, 608, 100);

            utils.doKeyWord(robot, password, 100);

            robot.delay(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


            robot.delay(1000 * 2);


            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_Z);
            robot.delay(10);
            robot.keyRelease(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.delay(1000 * 2);
            robot.mouseMove(690, 749);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyPress(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_O);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        }

    }

}
