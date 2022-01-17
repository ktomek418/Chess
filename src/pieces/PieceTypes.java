package pieces;

public enum PieceTypes {
    PAWN(1, "p"),
    KNIGHT(3, "s"),
    BISHOP(3, "b"),
    ROOK(5, "r"),
    QUEEN(9, "q"),
    KING(1000, "k");

    private int pieceValue;
    private String shortcut;

    PieceTypes(int value, String shortcut){
        this.pieceValue = value;
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return this.shortcut;
    }

    public int getPieceValue() {
        return pieceValue;
    }
}
