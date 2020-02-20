package corbos.montyhallproblem;

import java.util.Random;
import java.util.Scanner;

public class MontyHallMain {

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Let's Make a Deal!");

        int wins = 0;
        int rounds = 0;
        do {
            if (playGame(rand, scanner)) {
                System.out.println("You win!");
                wins++;
            } else {
                System.out.println("You lost :(");
            }
            rounds++;
        } while (readBoolean(scanner, "Do you want to play again? [y/n]"));

        System.out.println("Final Results:");
        System.out.println("===================");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + (rounds - wins));

    }

    public static boolean playGame(Random r, Scanner sc) {

        int carDoor = r.nextInt(3) + 1;
        //System.out.println("Car is behind: " + carDoor);
        int guess = makeGuess(sc);
        System.out.println("Guess: " + guess);

        int revealedDoor = revealDoor(guess, carDoor, r);
        System.out.println("There is a goat behind door #" + revealedDoor + ".");
        if (readBoolean(sc, "Do you want to switch? [y/n]")) {
            guess = 6 - guess - revealedDoor;
        }

        return guess == carDoor;
    }

    public static int revealDoor(int guess, int carDoor, Random r) {
        int revealedDoor = 0;
        if (guess == carDoor) {
            if (r.nextBoolean()) {
                revealedDoor = ((carDoor) % 3) + 1;
            } else {
                revealedDoor = ((carDoor + 1) % 3) + 1;
            }
        } else {
            revealedDoor = 6 - guess - carDoor;
        }
        return revealedDoor;
    }

    public static int makeGuess(Scanner sc) {
        int door = 0;
        do {
            System.out.println("Choose a door [1-3].");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    door = 1;
                    break;
                case "2":
                    door = 2;
                    break;
                case "3":
                    door = 3;
                    break;
                default:
                    System.out.println("That's not a valid door.");

            }
        } while (door < 1 || door > 3);
        return door;
    }

    public static boolean readBoolean(Scanner sc, String prompt) {
        System.out.println(prompt);
        return sc.nextLine().equalsIgnoreCase("y");
    }
}
