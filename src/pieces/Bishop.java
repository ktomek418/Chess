package pieces;

import player.Player;

public class Bishop extends AbstractPiece{

    public Bishop(int position, Player color){
        super(position, color);
        this.pieceTypes = PieceTypes.BISHOP;
    }

    @Override
    protected boolean isTheMovementCorrect(int previousPosition, int newPosition){
        if(newPosition < 0 || newPosition > 63) return false;

        int column = calculateColumn(previousPosition);
        int newColumn = calculateColumn(newPosition);

        return Math.abs(column - newColumn) == 1;
    }
}
