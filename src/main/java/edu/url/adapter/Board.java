package edu.url.adapter;

import java.util.List;

public interface Board<Move>{

    Piece getTurn(); //return turns
    Board<Move> move(Move move); //register move
    List<Move> getLegalMoves(); //get all moves
    boolean isWin(); //win verification
    double evaluate(Piece player);
    default boolean isDraw(){
        return !isWin() && getLegalMoves().isEmpty();
    }



}
