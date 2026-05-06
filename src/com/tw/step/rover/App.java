package com.tw.step.rover;

import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

public class App {

    public static void main(String[] args) {

        String text = """
                5 5
                1 2 N
                LFFRFFFF
                """;

        try {

            RoverSystemScanner scanner = RoverSystemScanner.from(text);

            Navigator navigator = Navigator.create();

            CommandCreator commandCreator = new CommandCreator();

            RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, commandCreator);

            RoverSystem roverSystem = roverSystemParser.parse();

            roverSystem.execute();

            System.out.println(roverSystem);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
