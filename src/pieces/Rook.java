package pieces;

import player.Player;

public class Rook extends AbstractPiece{

    private boolean firstMove = true;

    public Rook(int position, Player color){
        super(position, color);
        this.pieceTypes = PieceTypes.ROOK;
    }

    @Override
    protected boolean isTheMovementCorrect(int previousPosition, int newPosition){
        if(newPosition < 0 || newPosition > 63) return false;

        int column = calculateColumn(previousPosition);
        int newColumn = calculateColumn(newPosition);

        return (Math.abs(previousPosition - newPosition) == 1 && Math.abs(column - newColumn) == 1)
                || Math.abs(previousPosition - newPosition) == 8;
    }
}
