package pieces.moves;

import pieces.AbstractPiece;

public class AttackMove implements IMove{

    private AbstractPiece piece;
    private AbstractPiece attackedPiece;
    private int newPosition;

    public AttackMove(AbstractPiece piece, AbstractPiece attackedPiece, int newPosition){
        this.piece = piece;
        this.attackedPiece = attackedPiece;
        this.newPosition = newPosition;
    }

    @Override
    public void executeMove() {

    }

    @Override
    public int getDestination() {
        return newPosition;
    }

}
