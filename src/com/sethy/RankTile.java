package com.sethy;

public abstract class RankTile extends Tile {
    protected int rank;

    public RankTile(int rank){
        this.rank = rank;
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
        return this.rank == ((RankTile)other).rank;
    }
}
