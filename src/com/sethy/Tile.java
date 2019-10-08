package com.sethy;

public abstract class Tile {
    public boolean matches(Tile other){
        if(this == other){
            return true;
        }
        if(this == null){
            return false;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        return true;
    }
}
