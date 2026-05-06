package com.tw.step.rover.commands;

import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Navigator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CommandCreatorTest {

    @Test
    void shouldCreateCommandsForKnownInstructions() {

        CommandCreator commandCreator = new CommandCreator();

        Navigator navigator = Navigator.create();

        Plateau boundary = new Plateau(new Coordinate(0, 0), new Coordinate(5, 5));

        assertInstanceOf(TurnLeftCommand.class, commandCreator.create('L', navigator, boundary));

        assertInstanceOf(TurnRightCommand.class, commandCreator.create('R', navigator, boundary));

        assertInstanceOf(MoveCommand.class, commandCreator.create('F', navigator, boundary));
    }


}
