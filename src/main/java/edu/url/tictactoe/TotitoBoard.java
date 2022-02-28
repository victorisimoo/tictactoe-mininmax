package edu.url.tictactoe;

import edu.url.adapter.Board;
import edu.url.adapter.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TotitoBoard implements Board<Integer> {

    //Cantidad de casillas
    public static final int NUM_SQUARES = 9;

    //Casillas
    private TotitoPiece[] position;

    //Turno en ejecución
    private TotitoPiece turn;

    //Constructor
    public TotitoBoard(){
        this.position = new TotitoPiece[NUM_SQUARES];
        this.turn = TotitoPiece.X;
        Arrays.fill(position, TotitoPiece.E);
    }

    public TotitoBoard(TotitoPiece[] position, TotitoPiece turn){
        this.position = position;
        this.turn = turn;
    }

    @Override
    public Piece getTurn() {
        return this.turn;
    }

    @Override
    public Board<Integer> move(Integer location) {
        TotitoPiece[] tempPosition = Arrays.copyOf(position, position.length);
        tempPosition[location] = turn;
        return new TotitoBoard(tempPosition, turn.opposite());
    }

    @Override
    public List<Integer> getLegalMoves() {
        var legalMoves = new ArrayList<Integer>();
        for (int i = 0; i < position.length; i++){
            if(position[i] == TotitoPiece.E){
                legalMoves.add(i);
            }
        }
        return legalMoves;
    }

    private boolean checkPosition(int p0, int p1, int p2){
        return position[p0] == position[p1] && position[p0] == position[p2] && position[p0] != TotitoPiece.E;
    }

    @Override
    public boolean isWin() {
        return checkPosition(0, 1, 2) || checkPosition(3, 4, 5) || checkPosition(6, 7, 8) ||
                checkPosition(0, 3, 6) || checkPosition(1, 4, 7) || checkPosition(2, 5, 8) ||
                checkPosition(0, 4, 8) || checkPosition(2, 4, 6);
    }

    @Override
    public double evaluate(Piece player) {
        if(isWin() && turn == player){
            return -1;
        }else if (isWin() && turn != player){
            return 1;
        }else {
            return 0.0;
        }
    }

    //to String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                sb.append( position[row * 3 + col].toString() );
                if(col != 2){
                    sb.append("|");
                }
            }
            sb.append(System.lineSeparator());
            if(row !=2){
                sb.append("-----");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
