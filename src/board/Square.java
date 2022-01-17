package board;

import board.squareStates.Occupied;
import board.squareStates.SquareState;
import board.squareStates.Unoccupied;
import pieces.AbstractPiece;

import java.awt.*;

public class Square {

    private final int position;
    private final Color squareColor;
    private SquareState squareState;
    private AbstractPiece piece;

    public Square(int position, AbstractPiece piece){
        this.position = position;
        this.squareColor = position % 2 == 0 ? Color.black : Color.white;
        this.squareState = piece == null ? new Unoccupied() : new Occupied();
        this.piece = piece;
    }

    public int getPosition() {
        return position;
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    public boolean isOccupied(){
        return squareState.isOccupied();
    }

    public void setPiece(AbstractPiece piece){
        this.piece = piece;
        this.squareState = new Occupied();
    }

    public void removePiece(){
        this.piece = null;
        this.squareState = new Unoccupied();
    }

    @Override
    public String toString() {
        return this.piece == null ? "-": this.piece.toString();
    }
}
