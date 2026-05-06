package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;

import java.util.Objects;

public class Rover {
    private final String id;
    private RoverState roverState;
    private Coordinate coordinate;
    private Direction heading;
    private boolean lost;

    public Rover(String id, Coordinate coordinate, Direction heading) {
        this.id = id;
        this.coordinate = Objects.requireNonNull(coordinate);
        this.heading = Objects.requireNonNull(heading);
        this.roverState = new LiveRoverState(this);
        this.lost = false;
    }

    public Rover(Coordinate coordinate, Direction heading) {
        this("", coordinate, heading);
    }

    public void turnLeft(Navigator navigator, Boundary boundary) {
        roverState = roverState.turnLeft(
                Objects.requireNonNull(navigator),
                Objects.requireNonNull(boundary)
        );
    }

    public void turnRight(Navigator navigator, Boundary boundary) {
        roverState = roverState.turnRight(
                Objects.requireNonNull(navigator),
                Objects.requireNonNull(boundary)
        );
    }

    public void move(Navigator navigator, Boundary boundary) {
        roverState = roverState.move(
                Objects.requireNonNull(navigator),
                Objects.requireNonNull(boundary)
        );
    }

    void turnLeftInternal(Navigator navigator) {
        heading = navigator.leftOf(heading);
    }

    void turnRightInternal(Navigator navigator) {
        heading = navigator.rightOf(heading);
    }

    Coordinate getNextCoordinateInternal(Navigator navigator) {
        return navigator.nextCoordinate(coordinate, heading);
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = Objects.requireNonNull(coordinate);
    }

    void markLost() {
        this.lost = true;
    }

    boolean isWithin(Boundary boundary) {
        return boundary.isWithin(coordinate);
    }

    @Override
    public String toString() {
        return coordinate + " " + heading + (lost ? " LOST" : "");
    }
}
