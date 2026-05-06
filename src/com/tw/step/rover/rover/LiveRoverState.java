package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Navigator;

public class LiveRoverState implements RoverState {
    private final Rover rover;

    public LiveRoverState(Rover rover) {
        this.rover = rover;
    }

    @Override
    public RoverState turnLeft(Navigator navigator, Boundary boundary) {
        rover.turnLeftInternal(navigator);
        return this;
    }

    @Override
    public RoverState turnRight(Navigator navigator, Boundary boundary) {
        rover.turnRightInternal(navigator);
        return this;
    }

    @Override
    public RoverState move(Navigator navigator, Boundary boundary) {
        Coordinate nextCoordinate =
                rover.getNextCoordinateInternal(navigator);

        if (!boundary.isWithin(nextCoordinate)) {
            rover.markLost();
            return new DeadRoverState(rover);
        }

        rover.setCoordinate(nextCoordinate);

        return this;
    }
}
