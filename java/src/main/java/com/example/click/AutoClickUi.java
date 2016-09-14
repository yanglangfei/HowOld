package com.example.click;

import com.example.utils.RobotUtils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AutoClickUi extends JFrame {

    public AutoClickUi autoClick;
    private boolean isStop;
    private Robot robot;
    private RobotUtils utils;

    public AutoClickUi() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        utils = RobotUtils.getInstance();
        JButton button = new JButton();
        button.setText("开始");

        JButton button1 = new JButton();
        button1.setText("结束");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(400, 400);
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
        this.setLayout(new GridLayout());
        this.add(button);
        this.add(button1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStop) {
                    isStop = false;
                }
                new RunTask(autoClick).start();
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isStop) {
                    isStop = true;
                }
            }
        });

    }

    public static void main(String args[]) {
        AutoClickUi autoClickUi = new AutoClickUi();

    }

    private class RunTask extends Thread {

        private AutoClickUi frame;

        public RunTask(AutoClickUi frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            //http://www.jucaipen.com/
            String url = "http://www.jucaipen.com/";
            String chromePath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe  ";
            //192, 458
            while (!isStop) {
                //frame.setVisible(false);
                utils.openApplication(robot, chromePath + url, 1000 * 5);
                robot.mouseMove(192, 458);
                utils.clickLeftMouse(robot, 192, 458, 1000 * 5);

                utils.onCloseApp(robot, 1000 * 5);
            }

        }
    }
}
