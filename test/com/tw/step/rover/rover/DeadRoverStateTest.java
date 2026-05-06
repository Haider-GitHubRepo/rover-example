package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class DeadRoverStateTest {

    @Test
    void shouldIgnoreAllCommands() {

        Rover rover = new Rover(new Coordinate(1, 1), Direction.N);

        DeadRoverState state = new DeadRoverState(rover);

        Navigator navigator = Navigator.create();

        Plateau boundary = new Plateau(new Coordinate(0, 0), new Coordinate(5, 5));

        assertSame(state, state.turnLeft(navigator, boundary));

        assertSame(state, state.turnRight(navigator, boundary));

        assertSame(state, state.move(navigator, boundary));

        assertEquals("1 1 N", rover.toString());
    }
}
