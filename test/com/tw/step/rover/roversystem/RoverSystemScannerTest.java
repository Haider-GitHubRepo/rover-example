package com.tw.step.rover.roversystem;

import com.tw.step.rover.exceptions.ParsingException;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverSystemScannerTest {

    @Test
    void shouldScanValuesFromInput() {

        RoverSystemScanner scanner = RoverSystemScanner.from("1 2 N");

        RoverSystemScanner coordinateScanner = RoverSystemScanner.from("2 0");

        RoverSystemScanner directionScanner = RoverSystemScanner.from("N");

        RoverSystemScanner consumeScanner = RoverSystemScanner.from("X");

        assertEquals("1", scanner.peek());

        assertEquals(1, scanner.scanNumber());

        assertEquals(new Coordinate(2, 0), coordinateScanner.scanCoordinate());

        assertEquals(Direction.N, directionScanner.scanDirection());

        assertEquals("X", consumeScanner.consume());
    }

    @Test
    void shouldThrowExceptionWhenInputEnds() {

        RoverSystemScanner scanner = RoverSystemScanner.from("X");

        scanner.consume();

        assertThrows(ParsingException.class, scanner::consume);
    }
}
