package GUI;

import board.ChessBoard;
import board.Square;
import pieces.moves.IMove;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChessGameWindow extends JFrame {

    private final Color WHITE_SQUARE = new Color(247, 247,247);
    private final Color BLACK_SQUARE = new Color(8, 32, 50);
    private ImageIcon imgIcon = new ImageIcon("img/gameIcon.png");

    private ChessBoard gameBoard;

    private Square selectedSquare;
    private final Map<Integer, SquareWindow> squares = new HashMap<>();
    private ArrayList<IMove> legalMoves;

    public ChessGameWindow(ChessBoard chessBoard){
        this.gameBoard = chessBoard;
        this.setTitle("Chess");
        this.setLayout(new GridLayout(8,8));
        this.setPreferredSize(new Dimension(SquareWindow.SQUARE_SIZE * 9, SquareWindow.SQUARE_SIZE * 9));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createChessBoard();
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(imgIcon.getImage());
        this.pack();
    }

    public void createChessBoard(){
        for(int i = 56; i>= 0; i -= 8){
            Color squareColor = (i/8) %2 == 0 ? WHITE_SQUARE : BLACK_SQUARE;
            for(int j = 0; j< 8; j ++){
                squareColor = squareColor == WHITE_SQUARE ? BLACK_SQUARE : WHITE_SQUARE;
                this.addSquare(new SquareWindow(i+j, this, squareColor));
            }
        }
    }

    private void addSquare(SquareWindow square){
        this.add(square);
        this.squares.put(square.getId(), square);
    }


    public Square getSelectedSquare() {
        return this.selectedSquare;
    }

    public ChessBoard getBoard(){
        return this.gameBoard;
    }

    private IMove getLegalMovesForPieceInSelectedSquare(Square square){
        for(IMove move: legalMoves) if(move.getDestination() == square.getPosition()) return move;
        return null;
    }


    public void firstClickAction(Square firstSelectedSquare) {
        if(this.selectedSquare != null){
            this.selectedSquare = null;
            this.highlightLegalMoves(false);
        }
        if(firstSelectedSquare.isOccupied()){
            this.selectedSquare = firstSelectedSquare;
            this.legalMoves = firstSelectedSquare.getPiece().getLegalMoves(gameBoard);
            this.highlightLegalMoves(true);
        }

    }

    public void secondClickAction(Square secondSelectedSquare) {
        IMove move = this.getLegalMovesForPieceInSelectedSquare(secondSelectedSquare);
        if(move == null){
            this.firstClickAction(secondSelectedSquare);
        } else{
            // TODO dodać możliwość wykonywania ruchów
            System.out.println("execute move");
            this.selectedSquare = null;
            this.highlightLegalMoves(false);
        }
    }

    private void highlightLegalMoves(boolean on){
        for(IMove move: legalMoves){
            this.squares.get(move.getDestination()).highlightSquare(on);
        }
        this.revalidate();
    }

}
