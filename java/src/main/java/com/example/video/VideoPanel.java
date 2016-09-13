package com.example.video;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Created by Administrator on 2016/9/13.
 */
public class VideoPanel extends JPanel {


    private int width;
    private int height;
    private String path;

    public VideoPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPath(String path) {
        this.path = path;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(path);
        g.drawImage(imageIcon.getImage(), 20, 20, width, height, this);
        System.out.print("paint......:" + path + "\n");
    }
}
