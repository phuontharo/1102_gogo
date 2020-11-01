package com.example.testimg;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int imgId;
    private int positionImgInList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getPositionImgInList() {
        return positionImgInList;
    }

    public void setPositionImgInList(int positionImgInList) {
        this.positionImgInList = positionImgInList;
    }

    public Player(String name, int imgId, int positionImgInList) {
        this.name = name;
        this.imgId = imgId;
        this.positionImgInList = positionImgInList;
    }


}
