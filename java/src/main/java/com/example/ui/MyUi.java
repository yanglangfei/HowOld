package com.example.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Administrator on 2016/9/12.
 */
public class MyUi extends JFrame {

    public MyUi() {
        setTitle("qq");

        ImageIcon icon = new ImageIcon("");
        Image image = icon.getImage();
        setIconImage(image);
        setBackground(Color.PINK);

        JLabel label = new JLabel();
        label.setText("账号:");
        label.setForeground(Color.BLUE);

        JTextField field = new JTextField(20);
        field.setSize(200, 100);
        JPanel panel1 = new JPanel();
        panel1.setSize(100, 100);
        panel1.setLayout(new GridLayout());
        panel1.add(label);
        panel1.add(field);

        JPanel panel2 = new JPanel();
        panel2.setSize(100, 100);
        panel2.setLayout(new GridLayout());
        panel2.add(label);
        panel2.add(field);


        add(panel1);
        add(panel2);

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
