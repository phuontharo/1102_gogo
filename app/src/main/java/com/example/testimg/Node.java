package com.example.testimg;

import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.Serializable;

public class Node implements Serializable {
    private int id;
    private int x;
    private int y;
    private ImageButton button;
    private int value;
    private boolean isVisited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node(int id, int x, int y, ImageButton button, int value, boolean isVisited) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.button = button;
        this.value = value;
        this.isVisited = isVisited;
    }

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    void setColorButton(int color){
        button.setBackgroundColor(color);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageButton getButton() {
        return button;
    }

    public void setButton(ImageButton button) {
        this.button = button;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
