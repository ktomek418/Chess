package board;

import pieces.*;
import pieces.moves.IMove;
import player.Player;

import java.util.ArrayList;

public class ChessBoard {

    public static int SQUARES_PER_LINE = 8;
    public static int SQUARES = 64;
    public static int FIRST_SQUARE = 0;

    private final ArrayList<Square> boardSquares = new ArrayList<>();
    private final ArrayList<AbstractPiece> whitePieces = new ArrayList<>();
    private final ArrayList<AbstractPiece> blackPieces = new ArrayList<>();
    private King whiteKing;
    private King blackKing;

    public ChessBoard(){
        for(int position=FIRST_SQUARE; position < SQUARES; position++){
            boardSquares.add(new Square(position, null));
        }
    }
    public ChessBoard(King whiteKing, King blackKing){
        this();
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;
    }

    public void addPiece(int position, AbstractPiece piece){
        boardSquares.get(position).setPiece(piece);
        if(piece.getPieceColor() == Player.BLACK) blackPieces.add(piece);
        else whitePieces.add(piece);
    }

    public Square getSquare(int squarePosition) {
        return boardSquares.get(squarePosition);
    }

    public static ChessBoard generateClassicGame(){
        King whiteKing = new King(4, Player.WHITE);
        King blackKing = new King(60, Player.BLACK);
        ChessBoard chessBoard = new ChessBoard(whiteKing, blackKing);
        chessBoard.addPiece(0, new Rook(0, Player.WHITE));
        chessBoard.addPiece(1, new Knight(1, Player.WHITE));
        chessBoard.addPiece(2, new Bishop(2, Player.WHITE));
        chessBoard.addPiece(3, new Queen(3, Player.WHITE));
        chessBoard.addPiece(4, whiteKing);
        chessBoard.addPiece(5, new Bishop(5, Player.WHITE));
        chessBoard.addPiece(6, new Knight(6, Player.WHITE));
        chessBoard.addPiece(7, new Rook(7, Player.WHITE));
        chessBoard.addPiece(8, new Pawn(8, Player.WHITE));
        chessBoard.addPiece(9, new Pawn(9, Player.WHITE));
        chessBoard.addPiece(10, new Pawn(10, Player.WHITE));
        chessBoard.addPiece(11, new Pawn(11, Player.WHITE));
        chessBoard.addPiece(12, new Pawn(12, Player.WHITE));
        chessBoard.addPiece(13, new Pawn(13, Player.WHITE));
        chessBoard.addPiece(14, new Pawn(14, Player.WHITE));
        chessBoard.addPiece(15, new Pawn(15, Player.WHITE));

        chessBoard.addPiece(56, new Rook(56, Player.BLACK));
        chessBoard.addPiece(57, new Knight(57, Player.BLACK));
        chessBoard.addPiece(58, new Bishop(58, Player.BLACK));
        chessBoard.addPiece(59, new Queen(59, Player.BLACK));
        chessBoard.addPiece(60, blackKing);
        chessBoard.addPiece(61, new Bishop(61, Player.BLACK));
        chessBoard.addPiece(62, new Knight(62, Player.BLACK));
        chessBoard.addPiece(63, new Rook(63, Player.BLACK));
        chessBoard.addPiece(55, new Pawn(55, Player.BLACK));
        chessBoard.addPiece(54, new Pawn(54, Player.BLACK));
        chessBoard.addPiece(53, new Pawn(53, Player.BLACK));
        chessBoard.addPiece(52, new Pawn(52, Player.BLACK));
        chessBoard.addPiece(51, new Pawn(51, Player.BLACK));
        chessBoard.addPiece(50, new Pawn(50, Player.BLACK));
        chessBoard.addPiece(49, new Pawn(49, Player.BLACK));
        chessBoard.addPiece(48, new Pawn(48, Player.BLACK));
        return chessBoard;
    }

    public AbstractPiece getPieceFromSquare(int position){
        return boardSquares.get(position).getPiece();
    }

    public boolean isSquareAttackByPlayer(int position, Player player){
        ArrayList<IMove> moves;
        if(player == Player.BLACK){
            moves = getAllPlayerMove(Player.BLACK);
        } else{
            moves = getAllPlayerMove(Player.WHITE);
        }
        for(IMove move: moves){
            if(move.getDestination() == position) return true;
        }
        return false;
    }

    public ArrayList<AbstractPiece> getPlayerPieces(Player playerColor){
        if(playerColor == Player.BLACK) return blackPieces; else return whitePieces;
    }

    public ArrayList<IMove> getAllPlayerMove(Player playerColor){
        ArrayList<IMove> moves = new ArrayList<>();
        for(AbstractPiece piece: getPlayerPieces(playerColor)){
            moves.addAll(piece.getLegalMoves(this));
        }
        return moves;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public ArrayList<IMove> getPieceMoves(AbstractPiece piece){
        return piece.getLegalMoves(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 56; i>= FIRST_SQUARE; i -= 8){
            for(int j = 0; j< SQUARES_PER_LINE; j ++){
                builder.append(boardSquares.get(i + j).toString());
                builder.append("  ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
