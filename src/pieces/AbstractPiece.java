package pieces;

import board.ChessBoard;
import board.Square;
import pieces.moves.AttackMove;
import pieces.moves.IMove;
import pieces.moves.RegularMove;
import player.Player;

import java.util.ArrayList;

public abstract class AbstractPiece {

    private final static int[] PAWN_MOVEMENT = {7, 9};
    private final static int[] BISHOP_MOVEMENT = {-7, 7, -9, 9};
    private final static int[] KNIGHT_MOVEMENT = {-6, 6, -10, 10, -15, 15, -17, 17};
    private final static int[] ROOK_MOVEMENT = {1, -1, 8, -8};
    private final static int[] QUEEN_MOVEMENT = {-7, 7, -9, 9, 1, -1, 8, -8};
    private final static int[] KING_MOVEMENT = {-1, 1, 8, -8, -7, 7, -9, 9};

    private int position;
    private Player pieceColor;
    protected PieceTypes pieceTypes;


    public AbstractPiece(int position, Player color){
        this.position = position;
        this.pieceColor = color;
    }

    public int getPosition() {
        return position;
    }

    public Player getPieceColor() {
        return pieceColor;
    }

    public PieceTypes getPieceTypes() {
        return pieceTypes;
    }

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public static int calculateColumn(int position){ return  position % ChessBoard.SQUARES_PER_LINE;}

    public static int calculateRow(int position) { return  position / ChessBoard.SQUARES_PER_LINE;}

    public ArrayList<IMove> getLegalMoves(ChessBoard chessBoard) {
        ArrayList<IMove> legalMoves = new ArrayList<>();
        for(int move: getCorrectMovement()) {
            int previousPosition = getPosition();
            int newPosition = previousPosition + move;

            while (isTheMovementCorrect(previousPosition, newPosition)) {
                Square nextSquare = chessBoard.getSquare(newPosition);
                if (nextSquare.isOccupied()) {
                    if (nextSquare.getPiece().getPieceColor() != this.getPieceColor()) {
                        legalMoves.add(new AttackMove(this, nextSquare.getPiece(), newPosition));
                    }
                    break;
                } else {
                    legalMoves.add(new RegularMove(this, newPosition));
                    newPosition += move;
                    previousPosition  = newPosition - move;
                }
            }
        }
        return legalMoves;
    }

    protected abstract boolean isTheMovementCorrect(int currentPosition, int newPosition);

    public int[] getCorrectMovement(){
        if(pieceTypes == PieceTypes.PAWN) return PAWN_MOVEMENT;
        else if(pieceTypes == PieceTypes.BISHOP) return BISHOP_MOVEMENT;
        else if(pieceTypes == PieceTypes.KNIGHT) return KNIGHT_MOVEMENT;
        else if(pieceTypes == PieceTypes.ROOK) return ROOK_MOVEMENT;
        else if(pieceTypes == PieceTypes.QUEEN) return QUEEN_MOVEMENT;
        else if(pieceTypes == PieceTypes.KING) return KING_MOVEMENT;
        else return null;
    }

    public int getPieceValue(){
        return pieceTypes.getPieceValue();
    }

    @Override
    public String toString() {
        return pieceTypes.toString();
    }
}
