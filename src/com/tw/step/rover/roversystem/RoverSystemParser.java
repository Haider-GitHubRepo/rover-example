package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final CommandCreator commandCreator;

    public RoverSystemParser(
            RoverSystemScanner scanner,
            Navigator navigator,
            CommandCreator commandCreator
    ) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.commandCreator = commandCreator;
    }

    private Boundary parseBoundary() {
        Coordinate topRight = scanner.scanCoordinate();
        return new Plateau(new Coordinate(0, 0), topRight);
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
        Boundary boundary = parseBoundary();

        Rover rover = parseRover();

        RoverCommands roverCommands = parseRoverCommands(boundary);

        RoverSystem roverSystem = new RoverSystem();

        roverSystem.addRover(rover);
        roverSystem.addCommands(roverCommands);

        return roverSystem;
    }

    private RoverCommands parseRoverCommands(Boundary boundary) {
        String instructions = scanner.consume();

        if (instructions == null || instructions.isEmpty()) {
            throw new ParsingException("Missing rover instructions");
        }

        return instructions
                .chars()
                .mapToObj(instruction ->
                        commandCreator.create(
                                (char) instruction,
                                navigator,
                                boundary
                        )
                )
                .collect(
                        RoverCommands::new,
                        RoverCommands::add,
                        RoverCommands::addAll
                );
    }
}
