package com.example.ui;

import com.example.utils.RobotUtils;
import com.example.video.VideoPanel;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Administrator on 2016/9/12.
 */
public class MyUi extends JFrame {

    private final VideoPanel panelVideo;
    private boolean isStop;

    public MyUi() {
        setTitle("截屏软件");

        final ImageIcon icon = new ImageIcon("");
        Image image = icon.getImage();
        setIconImage(image);
        setBackground(Color.PINK);
        //软件永远在栈顶
        setAlwaysOnTop(true);

        final JPanel panel1 = new JPanel();
        panel1.setSize(100, 100);


        final JButton button = new JButton();
        button.setText("截屏");

        JButton button1 = new JButton();
        button1.setText("放映");

        JButton button2 = new JButton();
        button2.setText("停止");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = !isStop;

                new Thread() {
                    @Override
                    public void run() {
                        while (!isStop) {
                            //  setVisible(false);
                            try {
                                button.setText("截图...");
                                button.setEnabled(false);
                                // JOptionPane.showMessageDialog(null, "正在处理任务...");
                                Robot robot = new Robot();
                                RobotUtils utils = RobotUtils.getInstance();
                                Icon icon1 = utils.captureFullScreen(robot, "cap", "png", true, 1000 * 5);

                 /*   utils .openApplication(robot, "notepad");
                    int [] keys={KeyEvent.VK_I,KeyEvent.VK_SPACE,KeyEvent.VK_L,KeyEvent.VK_O,KeyEvent.VK_V,KeyEvent.VK_E, KeyEvent.VK_SPACE,KeyEvent.VK_Y
                    , KeyEvent.VK_O, KeyEvent.VK_U};*/
                                // utils.doKeyWord(robot, keys,3000);
                                //   setVisible(true);
                                robot.delay(1000 * 5);
                            } catch (AWTException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }.start();


            }
        });
        panelVideo = new VideoPanel(300, 300);
        panelVideo.setSize(200, 200);
        panelVideo.setLocation(20, 200);
        panelVideo.setBackground(Color.GRAY);
        add(panelVideo);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    setVisible(false);
                    Robot robot = new Robot();
                  /*  robot.keyPress(KeyEvent.VK_WINDOWS);
                    robot.keyPress(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_R);
                    robot.keyRelease(KeyEvent.VK_WINDOWS);
                    robot.delay(10);*/
                    robot.delay(1000 * 2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.delay(1000);
                    robot.mouseMove(100, 100);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);

                  /*  robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);*/

                  /*  Process pro = Runtime.getRuntime().exec("cmd.exe /c start");
                    robot.mouseMove(533,389);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    robot.keyPress(KeyEvent.VK_E);
                    robot.keyRelease(KeyEvent.VK_E);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyPress(KeyEvent.VK_C);*/
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.out.print("error");
                }


              /*  String p = "D:\\ide\\QPLiveSDK_Andorid_1.0.3\\QupaiLiveSDK\\test\\HowOld\\cap";
                File files = new File(p);
                for (int i = 0; i < files.listFiles().length; i++) {
                    String path = p + "\\" + files.listFiles()[i].getName();
                    System.out.print("path:" + path + "\n");
                    panelVideo.setPath(path);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                        System.out.print("error");
                    }
                }*/


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = true;
                button.setEnabled(true);
                button.setText("截图");
            }
        });


        panel1.add(button);
        panel1.add(button1);

        panel1.add(button2);

        add(panel1);

        //获取屏幕尺寸
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        //设置主布局宽高
        this.setSize(500, 500);
        //设置主布局位置
        this.setLocation(size.width / 2 - this.getWidth() / 2, size.height / 2 - this.getHeight() / 2);
    }

    public static void main(String a[]) {
        MyUi ui = new MyUi();
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setName("name");

    }

}
