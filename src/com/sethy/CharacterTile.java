package com.sethy;

public class CharacterTile extends Tile {
    protected char symbol;

    public CharacterTile(char symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        switch (symbol){
            case 'N':
            case 'n':
                return "North Wind";
            case 'E':
            case 'e':
                return "East Wind";
            case 'W':
            case 'w':
                return "West Wind";
            case 'S':
            case 's':
                return "South Wind";
            case 'C':
            case 'c':
                return "Red Dragon";
            case 'F':
            case 'f':
                return "Green Dragon";
            default:
                return "Character " + symbol;
        }
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
        return this.symbol == ((CharacterTile)other).symbol;
    }
}
