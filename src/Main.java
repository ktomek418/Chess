import GUI.ChessGameWindow;
import board.ChessBoard;
import player.Player;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoard.generateClassicGame();
        System.out.println(chessBoard);
        chessBoard.getAllPlayerMove(Player.BLACK);
        System.out.println(chessBoard.isSquareAttackByPlayer(16, Player.WHITE));
        new ChessGameWindow(ChessBoard.generateClassicGame());


    }
}
