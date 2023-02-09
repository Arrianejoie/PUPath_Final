package PUPath_Final;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Good Day!");
        while (true) {
            // added toLowerCase to be case insensitive
            System.out.println("Where do you want to go?");
            String inputDestination = scanner.nextLine().toLowerCase();

            System.out.println("Please tell me where you are right now.");
            String inputLocation = scanner.nextLine().toLowerCase();

            // cannot be changed

            GraphGenerator generator = new GraphGenerator();

            Integer destination = generator.analyzeChat(inputDestination); // put the destination chat response
            Integer location = generator.analyzeChat(inputLocation); // put the current location chat response

            // added while loop for invalid input of location and destintion
            while (location == null || destination == null) {
                if (location == null) {
                    System.out.println("Sorry, we could not determine your location. Please tell me where you are right now.");
                    inputLocation = scanner.nextLine().toLowerCase();
                    location = generator.analyzeChat(inputLocation);
                }
                if (destination == null) {
                    System.out.println("Sorry, we could not determine your destination. Where do you want to go?");
                    inputDestination = scanner.nextLine().toLowerCase();
                    destination = generator.analyzeChat(inputDestination);
                }
            }


            System.out.println("Finding path from " + GraphGenerator.destinations.get(location) + " to " + GraphGenerator.destinations.get(destination) + "...");

            Dijkstra dijkstra = new Dijkstra();
            List<Integer> paths = dijkstra.generateShortestPath(generator.generateMatrixGraph(), location, destination);

            Collections.reverse(paths);

            List<String> instructions = generator.generateInstructions(paths);

            // variable instruction contains the steps


            // change on how you will display the data
            System.out.println("Follow these steps to arrive at your destination: ");
            for (String step : instructions)
                System.out.println(step);
                System.out.println("Wishing you reach your desired destination!");
                scanner.nextLine();// for random input
            
            // added choice if user wants to have another transaction
            System.out.println("Are you heading to another destination?(Yes/No)");
            String input = scanner.nextLine().toLowerCase();
            Boolean repeat = true;

            while(repeat) {
                switch(input) {
                    case "yes":
                        repeat = false;
                        break;
                    case "no":
                        System.out.println("Thank you for using PUPath");
                        scanner.nextLine();// for random input
                        System.out.println("Do you want to have another transaction?(Yes/No)");
                        input = scanner.nextLine().toLowerCase();
                        break;
                    default:
                        System.out.println("Invalid answer, please answer yes or no only");
                        input = scanner.nextLine().toLowerCase();
                }
            }
        }
    }
}