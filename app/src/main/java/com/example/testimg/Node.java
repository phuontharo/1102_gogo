package com.example.testimg;

import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

public class Node {
    private int id;
    private int x;
    private int y;
    private Button button;
    private boolean isVisited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node(int id, int x, int y, Button button, boolean isVisited) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.button = button;
        this.isVisited = isVisited;
    }

    public int getColor(){
        ColorDrawable color = (ColorDrawable) button.getBackground();
        return color.getColor();
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
