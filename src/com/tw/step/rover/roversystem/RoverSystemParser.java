package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        try {
            Coordinate coordinate = scanner.scanCoordinate();
            Direction heading = scanner.scanDirection();
            return new Rover(coordinate, heading);
        } catch (Exception exception) {
            throw new ParsingException("Failed to parse rover");
        }
    }

    public RoverSystem parse() {
        RoverSystem roverSystem = new RoverSystem();

        Rover rover = parseRover();
        roverSystem.addRover(rover);

        RoverCommands roverCommands = parseRoverCommands();
        roverSystem.addCommands(roverCommands);

        return roverSystem;
    }

    private RoverCommands parseRoverCommands() {
        String instructions = scanner.consume();

        if (instructions == null || instructions.isEmpty()) {
            throw new ParsingException("No instructions found");
        }

        RoverCommands roverCommands = new RoverCommands();

        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(
                    instructions.charAt(i),
                    navigator,
                    boundary
            );

            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
