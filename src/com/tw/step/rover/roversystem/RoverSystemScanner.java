package com.tw.step.rover.roversystem;

import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;

public class RoverSystemScanner {
    private final String[] tokens;
    private int currentIndex;

    private RoverSystemScanner(String[] tokens) {
        this.tokens = tokens;
        this.currentIndex = 0;
    }

    public String peek() {
        if (this.isDone()) {
            return null;
        }

        return this.tokens[this.currentIndex];
    }

    private boolean isDone() {
        return this.currentIndex >= this.tokens.length;
    }

    public String consume() {
        if (this.isDone()) {
            throw new ParsingException("end of input");
        }

        String token = this.tokens[this.currentIndex];
        this.currentIndex++;

        return token;
    }

    public static RoverSystemScanner from(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new ParsingException("Input cannot be empty");
        }

        String[] tokens = input.trim().split("[\n\t ]+");
        return new RoverSystemScanner(tokens);
    }

    public int scanNumber() {
        try {
            return Integer.parseInt(consume());
        } catch (NumberFormatException exception) {
            throw new ParsingException("Invalid number format");
        }
    }

    public Coordinate scanCoordinate() {
        int x = this.scanNumber();
        int y = this.scanNumber();
        return new Coordinate(x, y);
    }

    public Direction scanDirection() {
        return Direction.valueOf(this.consume());
    }
}
