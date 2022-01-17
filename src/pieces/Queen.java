package pieces;

import player.Player;

public class Queen extends AbstractPiece{

    public Queen(int position, Player color){
        super(position, color);
        this.pieceTypes = PieceTypes.QUEEN;
    }

    @Override
    protected boolean isTheMovementCorrect(int previousPosition, int newPosition){
        if(newPosition < 0 || newPosition > 63) return false;

        int column = calculateColumn(previousPosition);
        int newColumn = calculateColumn(newPosition);

        return  Math.abs(column - newColumn) == 1
                || Math.abs(previousPosition - newPosition) == 8;
    }
}
