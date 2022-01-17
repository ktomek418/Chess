package game;

import board.ChessBoard;
import player.Player;

public class ChessGame {

    private ChessBoard board;
    private GameResult gameResult = GameResult.IN_PROGRESS;
    private Player nextMove = Player.WHITE;

    public ChessGame(){
        generateBoard();
//        this.whitePlayer = new WhitePlayer(board.getWhiteKing());
//        this.blackPlayer = new BlackPlayer(board.getBlackKing());
    }

    public boolean isGameOver(){
        return gameResult != GameResult.IN_PROGRESS;
    }

    private void generateBoard(){
        this.board = ChessBoard.generateClassicGame();
    }

    public Player getWinningPlayer(){
        if(gameResult == GameResult.BLACK_WON) return Player.BLACK;
        else if (gameResult == GameResult.WHITE_WON) return Player.WHITE;
        return null;
    }

    public Player whoHasNextMove(){
        return nextMove;
    }

    public void nextRound(){

    }
}
