package board.squareStates;

import board.squareStates.SquareState;

public class Occupied implements SquareState {
    @Override
    public boolean isOccupied() {
        return true;
    }
}
