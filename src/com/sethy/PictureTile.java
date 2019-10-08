package com.sethy;

public abstract class PictureTile extends Tile{
    private String name;

    public PictureTile(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
