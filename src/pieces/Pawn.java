package pieces;

import board.ChessBoard;
import board.Square;
import pieces.moves.AttackMove;
import pieces.moves.IMove;
import pieces.moves.RegularMove;
import player.Player;

import java.util.ArrayList;

public class Pawn extends AbstractPiece{

    private boolean firstMove = true;
    private int direction = 1;

    public Pawn(int position, Player color){
        super(position, color);
        this.pieceTypes = PieceTypes.PAWN;
        if(color == Player.BLACK) direction *= -1;
    }

    public ArrayList<IMove> getLegalMoves(ChessBoard chessBoard){
        ArrayList<IMove> legalMoves = new ArrayList<>();
            IMove forwardMovement = checkForwardMovement(getPosition(), chessBoard);
            if(forwardMovement != null){
                if(firstMove){
                    IMove doubleMove = checkForwardMovement(getPosition() +
                            ChessBoard.SQUARES_PER_LINE, chessBoard);
                    if(doubleMove != null) legalMoves.add(doubleMove);
                }
                legalMoves.add(forwardMovement);
            }
            for(int move: getCorrectMovement()){
                int newPosition = getPosition() + move * direction;
                if(isTheMovementCorrect(getPosition(), newPosition)) {
                    Square squareToAttack = chessBoard.getSquare(newPosition);
                    if(squareToAttack.isOccupied() && squareToAttack.getPiece().getPieceColor() != this.getPieceColor()){
                        legalMoves.add(new AttackMove(this, squareToAttack.getPiece(), newPosition));
                    }
                }
            }
            return legalMoves;
    }

    public IMove checkForwardMovement(int currentPosition, ChessBoard chessBoard){
        int newPosition = currentPosition + ChessBoard.SQUARES_PER_LINE* direction;
        Square squareToCheck = chessBoard.getSquare(newPosition);
        if(!squareToCheck.isOccupied()){
            return new RegularMove(this, newPosition);
        }
        else return null;
    }

    @Override
    protected boolean isTheMovementCorrect(int currentPosition, int newPosition) {
        if(newPosition < 0 || newPosition > 63 ) return false;
        int column = AbstractPiece.calculateColumn(currentPosition);
        int newColumn = AbstractPiece.calculateColumn(newPosition);
        if(column == 0 && newColumn == 7) return false;
        else if (column == 7 && newColumn == 0) return false;
        else return true;
    }
}
