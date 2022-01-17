package pieces.moves;

import pieces.AbstractPiece;

public class RegularMove implements IMove {

    private AbstractPiece piece;
    private int newPosition;

    public RegularMove(AbstractPiece piece, int newPosition){
        this.piece = piece;
        this.newPosition = newPosition;
    }

    @Override
    public void executeMove() {
        piece.setPosition(newPosition);
    }

    @Override
    public int getDestination() {
        return newPosition;
    }

}
