package com.sethy;

public class CircleTile extends RankTile {

    public CircleTile(int rank){
        super(rank);
    }

    @Override
    public String toString() {
        return "Circle " + rank;
    }

    @Override
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
        return this.rank == ((CircleTile)other).rank;
    }
}
