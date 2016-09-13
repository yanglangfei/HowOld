package com.example.click;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AutoClickUi {

    public static void main(String args[]) {
        //http://www.jucaipen.com/
        String url = "http://www.jucaipen.com/";
        String chromePath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe  ";
        try {
            Runtime.getRuntime().exec(chromePath + url);
            try {
                Robot robot = new Robot();
                robot.delay(1000 * 2);
                robot.mouseMove(192, 458);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
