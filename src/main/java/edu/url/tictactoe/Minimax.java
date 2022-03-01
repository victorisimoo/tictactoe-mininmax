package edu.url.tictactoe;

import edu.url.adapter.Board;
import edu.url.adapter.Piece;

public class Minimax {

    public static<Move> double minimax(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth) {
        if (board.isWin() || board.isDraw() || maxDepth == 0) {
            return board.evaluate(originalPlayer);
        }

        //Minimax
        if(maximizing) {
            double v = Double.NEGATIVE_INFINITY;
            for(Move move : board.getLegalMoves()) {
                double result = alphabetapruning(board.move(move), false,
                        originalPlayer, maxDepth - 1);
                v = Math.max(v, result);
            }
            return v;
        }else {
            double v = Double.POSITIVE_INFINITY;
            for (Move move : board.getLegalMoves()) {
                double result = minimax(board.move(move), true,
                        originalPlayer, maxDepth - 1);
                v = Math.min(v, result);
            }
            return v;
        }
    }

    public static <Move> Move findBestMove(Board<Move> board, int maxDepth){
        double bestValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for(Move move : board.getLegalMoves()) {
            double result = minimax(board.move(move), false, board.getTurn(), maxDepth);
            if(result > bestValue) {
                bestValue = result;
                bestMove = move;
            }
        }
        return bestMove;
    }

    //Método para machine vs machine
    /*public static <Move> Move findBestMoveEnemy(Board<Move> board, int maxDepth){
        double bestValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for(Move move : board.getLegalMoves()) {
            double result = minimax(board.move(move), false, board.getTurn(), maxDepth);
            if(result > bestValue) {
                bestValue = result;
                bestMove = move;
            }
        }
        return bestMove;
    }*/

    public static <Move> double alphabetapruning(Board<Move> board, boolean miximazing, Piece originalPlayer, int maxDepth){
        return alphabetapruning(board, miximazing, originalPlayer, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private static <Move> double alphabetapruning(Board<Move> board, boolean miximazing, Piece originalPlayer, int maxDepth, double alpha, double beta){
        if(board.isWin() || board.isDraw() || maxDepth == 0){
            return board.evaluate(originalPlayer);
        }

        if(miximazing){
            for(Move m: board.getLegalMoves()){
                alpha = Math.max(alpha, alphabetapruning(board.move(m), false, originalPlayer, maxDepth - 1, alpha, beta));
                if(beta <= alpha){
                    break;
                }
            }
            return alpha;
        }else {
            for(Move m: board.getLegalMoves()){
                beta = Math.min(beta, alphabetapruning(board.move(m), true, originalPlayer, maxDepth - 1, alpha, beta));
                if(beta <= alpha){
                    break;
                }
            }
            return beta;
        }
    }

}
