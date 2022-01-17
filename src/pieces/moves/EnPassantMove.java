package pieces.moves;

import pieces.AbstractPiece;

public class EnPassantMove implements IMove{
    private AbstractPiece pawn;
    private AbstractPiece attackedPawn;
    private final int newPosition;

    public EnPassantMove(AbstractPiece piece, AbstractPiece attackedPiece, int newPosition){
        this.pawn = piece;
        this.attackedPawn = attackedPiece;
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
