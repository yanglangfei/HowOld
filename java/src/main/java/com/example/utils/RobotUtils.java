package com.example.utils;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Created by Administrator on 2016/9/13.
 * 自动化测试工具类
 */
public class RobotUtils {
    private static RobotUtils robotUtils = new RobotUtils();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static RobotUtils getInstance() {
        if (robotUtils == null) {
            robotUtils = new RobotUtils();
        }
        return robotUtils;
    }

    /**
     * @param robot
     * @param dely  复制
     */
    public void doCopy(Robot robot, int dely) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
        robot.delay(dely);
    }


    /**
     * @param robot
     * @param dely  粘贴
     */
    public void doCut(Robot robot, int dely) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(dely);
    }

    /**
     * @param robot
     * @param x
     * @param y
     * @param move
     * @param dery  选中指定的文字
     */
    public void doSelect(Robot robot, int x, int y, int move, int dery) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(x, move);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(dery);
    }

    /**
     * @param robot
     * @param url   打开应用程序   浏览器
     */
    public void openApplication(Robot robot, String url) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(url);
            robot.delay(1000 * 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param url 打开指定URL
     */
    public void openBresh(String url) {
        //JDK  1.6 以上使用
        Desktop desktop = Desktop.getDesktop();
        //   JDK 1.6  以上并且  支持浏览器
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(url));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("error");
            }
        }


    }

    /**
     * @param robot
     * @param keys  键盘输入指定键
     */
    public void doKeyWord(Robot robot, int[] keys, int dery) {
        for (int i = 0; i < keys.length; i++) {
            robot.keyPress(keys[i]);
            robot.keyRelease(keys[i]);
            robot.delay(dery);
        }
    }


    /**
     * @param robot
     * @param dery  关闭应用程序
     */
    public void onCloseApp(Robot robot, int dery) {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.delay(dery);
    }

    /**
     * @param robot
     * @param dery  保存文件
     */
    public void onSave(Robot robot, int dery) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        robot.delay(dery);
    }


    /**
     * @param robot
     * @param montions
     * @param dery     执行鼠标系列动作
     */
    public void doMotionAction(Robot robot, int montions[], int dery) {
        for (int i = 0; i < montions.length; i++) {
            robot.mousePress(montions[i]);
            robot.delay(10);
            robot.mouseRelease(montions[i]);
            robot.delay(dery);
        }
    }


    /**
     * @param robot
     * @param x
     * @param y
     * @param dery  点击鼠标左键
     */
    public void clickLeftMouse(Robot robot, int x, int y, int dery) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(dery);
    }


    /**
     * @param robot
     * @param x
     * @param y
     * @param dery  点击鼠标右键
     */
    public void clickRightMouse(Robot robot, int x, int y, int dery) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(dery);
    }


    /**
     * @param robot
     * @param distance
     * @param dery     滚动鼠标转轮
     */
    public void doWhelllScroll(Robot robot, int distance, int dery) {
        robot.mouseWheel(distance);
        robot.delay(dery);
    }

    /**
     * @param robot
     * @param x
     * @param y
     * @param dery  移动鼠标
     */
    public void doMoveMotion(Robot robot, int x, int y, int dery) {
        robot.mouseMove(x, y);
        robot.delay(dery);
    }


    /**
     * @param robot
     * @return 截取电脑屏幕
     */
    public Icon captureFullScreen(Robot robot, String path, String format, boolean isWrit, int dery) {
        BufferedImage fullScreenImage = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        if (isWrit) {
            try {
                File file = new File(path, sdf.format(new Date()) + ".png");
                if (!file.exists()) {
                    file.mkdirs();
                }
                boolean write = ImageIO.write(fullScreenImage, format, file);
                System.out.print("file create:" + write);
                robot.delay(dery);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("error");
            }
        }
        ImageIcon imageIcon = new ImageIcon(fullScreenImage);
        return imageIcon;
    }

    /**
     * @param robot
     * @param x
     * @param y
     * @param width
     * @param height
     * @return 截取电脑区域屏幕
     */
    public Icon capturePartScreen(Robot robot, int x, int y, int width, int height, boolean isWrit, String path) {
        BufferedImage partImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
        if (isWrit) {
            try {
                ImageIO.write(partImage, "png", new File(path + "/" + sdf.format(new Date())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ImageIcon imageIcon = new ImageIcon(partImage);
        return imageIcon;
    }


}
