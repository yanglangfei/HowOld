package com.example.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/13.
 */
public class FunctionUtils {
    public Map<String, Integer> keys = new HashMap<>();

    public FunctionUtils() {
        initKeys();
    }

    /**
     * @param robot
     * @param dery  弹出QQ
     */
    public void popQQ(Robot robot, int dery) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(dery);
    }

    private void initKeys() {
        addNumberKeys();
        addCharKeys();
        addFuncKeys();
    }

    private void addFuncKeys() {
        keys.put("f1", KeyEvent.VK_F1);
        keys.put("f2", KeyEvent.VK_F2);
        keys.put("f3", KeyEvent.VK_F3);
        keys.put("f4", KeyEvent.VK_F4);
        keys.put("f5", KeyEvent.VK_F5);
        keys.put("f6", KeyEvent.VK_F6);
        keys.put("f7", KeyEvent.VK_F7);
        keys.put("f8", KeyEvent.VK_F8);
        keys.put("f9", KeyEvent.VK_F9);
        keys.put("f10", KeyEvent.VK_F10);
        keys.put("f11", KeyEvent.VK_F11);
        keys.put("f12", KeyEvent.VK_F12);
        keys.put("space", KeyEvent.VK_SPACE);
        keys.put("win", KeyEvent.VK_WINDOWS);
        keys.put("ctrl", KeyEvent.VK_CONTROL);
        keys.put("alt", KeyEvent.VK_ALT);
        keys.put("shift", KeyEvent.VK_SHIFT);
        keys.put("tab", KeyEvent.VK_TAB);
        keys.put("caps", KeyEvent.VK_CAPS_LOCK);
        keys.put("enter", KeyEvent.VK_ENTER);
        keys.put("esc", KeyEvent.VK_ESCAPE);
        keys.put("del", KeyEvent.VK_DELETE);
        keys.put("insert", KeyEvent.VK_INSERT);
        keys.put("home", KeyEvent.VK_HOME);
        keys.put("pd", KeyEvent.VK_PAGE_DOWN);
        keys.put("pu", KeyEvent.VK_PAGE_UP);
        keys.put("end", KeyEvent.VK_END);
        keys.put("left", KeyEvent.VK_LEFT);
        keys.put("right", KeyEvent.VK_RIGHT);
        keys.put("up", KeyEvent.VK_UP);
        keys.put("down", KeyEvent.VK_DOWN);
        keys.put("scrollLock", KeyEvent.VK_SCROLL_LOCK);
        keys.put("printScreen", KeyEvent.VK_PRINTSCREEN);
        keys.put("pauseBreak", KeyEvent.VK_PAUSE);

    }

    private void addCharKeys() {
        keys.put("q", KeyEvent.VK_Q);
        keys.put("w", KeyEvent.VK_W);
        keys.put("e", KeyEvent.VK_E);
        keys.put("r", KeyEvent.VK_R);
        keys.put("t", KeyEvent.VK_T);
        keys.put("y", KeyEvent.VK_Y);
        keys.put("u", KeyEvent.VK_U);
        keys.put("i", KeyEvent.VK_I);
        keys.put("o", KeyEvent.VK_O);
        keys.put("p", KeyEvent.VK_P);
        keys.put("a", KeyEvent.VK_A);
        keys.put("s", KeyEvent.VK_S);
        keys.put("d", KeyEvent.VK_D);
        keys.put("f", KeyEvent.VK_F);
        keys.put("g", KeyEvent.VK_G);
        keys.put("h", KeyEvent.VK_H);
        keys.put("j", KeyEvent.VK_J);
        keys.put("k", KeyEvent.VK_K);
        keys.put("l", KeyEvent.VK_L);
        keys.put("z", KeyEvent.VK_Z);
        keys.put("x", KeyEvent.VK_X);
        keys.put("c", KeyEvent.VK_C);
        keys.put("v", KeyEvent.VK_V);
        keys.put("b", KeyEvent.VK_B);
        keys.put("n", KeyEvent.VK_N);
        keys.put("m", KeyEvent.VK_M);
    }

    private void addNumberKeys() {
        keys.clear();
        keys.put("0", KeyEvent.VK_0);
        keys.put("1", KeyEvent.VK_1);
        keys.put("2", KeyEvent.VK_2);
        keys.put("3", KeyEvent.VK_3);
        keys.put("4", KeyEvent.VK_4);
        keys.put("5", KeyEvent.VK_5);
        keys.put("6", KeyEvent.VK_6);
        keys.put("7", KeyEvent.VK_7);
        keys.put("8", KeyEvent.VK_8);
        keys.put("9", KeyEvent.VK_9);
    }


}
