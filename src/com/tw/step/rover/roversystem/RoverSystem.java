package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.rover.Rover;

public class RoverSystem {
    private Rover rover;
    private RoverCommands roverCommands;

    public void addRover(Rover rover) {
        this.rover = rover;
    }

    public void addCommands(RoverCommands roverCommands) {
        this.roverCommands = roverCommands;
    }

    public void execute() {
        if (this.rover == null) {
            throw new ParsingException("Rover not found");
        }

        if (this.roverCommands == null) {
            throw new ParsingException("Rover commands not found");
        }

        this.roverCommands.execute(this.rover);
    }

    @Override
    public String toString() {
        return rover.toString();
    }
}
