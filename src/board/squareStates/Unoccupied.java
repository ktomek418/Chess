package board.squareStates;

import board.squareStates.SquareState;

public class Unoccupied implements SquareState {

    @Override
    public boolean isOccupied() {
        return false;
    }
}
