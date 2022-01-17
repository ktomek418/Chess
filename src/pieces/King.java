package pieces;

import board.ChessBoard;
import board.Square;
import pieces.moves.AttackMove;
import pieces.moves.IMove;
import pieces.moves.RegularMove;
import player.Player;

import java.util.ArrayList;

public class King extends AbstractPiece{

    boolean firstMove = true;

    public King(int position, Player color){
        super(position, color);
        this.pieceTypes = PieceTypes.KING;
    }

    @Override
    public ArrayList<IMove> getLegalMoves(ChessBoard chessBoard) {
        ArrayList<IMove> legalMoves = new ArrayList<>();
        for(int move: getCorrectMovement()){
            int newPosition = this.getPosition() + move;
            if(isTheMovementCorrect(this.getPosition(), newPosition)){
                Square square = chessBoard.getSquare(newPosition);
                if(!square.isOccupied()){
                    legalMoves.add(new RegularMove(this, newPosition));
                } else if(square.getPiece().getPieceColor() != this.getPieceColor()){
                    legalMoves.add(new AttackMove(this, square.getPiece(), newPosition));
                }
            }
        }
        return legalMoves;
    }


    @Override
    public boolean isTheMovementCorrect(int currentPosition, int newPosition){
        if(newPosition < 0 || newPosition > 63) return false;
        int column = calculateColumn(currentPosition);
        int newColumn = calculateColumn(newPosition);
        return !((column == 0 && newColumn == 7) || (column == 7 && newColumn == 0));
    }


}
