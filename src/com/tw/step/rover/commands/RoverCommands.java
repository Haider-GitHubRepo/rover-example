package com.tw.step.rover.commands;

import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.rover.Rover;

import java.util.ArrayList;

public class RoverCommands extends ArrayList<RoverCommand> implements RoverCommand {

    @Override
    public void execute(Rover rover) {
        this.stream()
                .peek(command -> {
                    if (command == null) {
                        throw new ParsingException("Null rover command found");
                    }
                })
                .forEach(command -> command.execute(rover));
    }
}
