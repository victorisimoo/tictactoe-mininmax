package edu.url.tictactoe;
import edu.url.adapter.Piece;

public enum TotitoPiece implements Piece {

    //Estructura que representa los componentes del juego
    X, O, E;

    @Override
    public String toString() {
        switch(this){
            case X:
                return "X";
            case O:
                return "O";
            default:
                return " ";
        }
    }

    //Método para llevar a cabo el cambio de turnos
    @Override
    public TotitoPiece opposite() {
        switch (this){
            case X:
                return O;
            case O:
                return X;
            default:
                return E;
        }
    }
}
