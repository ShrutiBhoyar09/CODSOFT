import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String playAgain;

        int totalRounds = 0;
        int roundsWon = 0;
        int finalScore = 0;

        do {
            totalRounds++;

            int number = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 5;
            boolean isWon = false;

            System.out.println("\nGuess a number between 1 and 100");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == number) {
                    isWon = true;
                    roundsWon++;

                    int scoreThisRound = (maxAttempts - attempts + 1) * 10;
                    finalScore += scoreThisRound;

                    System.out.println("🎉 Correct!");
                    System.out.println("Attempts taken: " + attempts);
                    System.out.println("Score for this round: " + scoreThisRound);
                    break;

                } else if (guess > number) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }
            }

            if (!isWon) {
                System.out.println("❌ You lost! Number was: " + number);
            }

            System.out.print("Play again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\n===== GAME SUMMARY =====");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Final Score: " + finalScore);

        sc.close();
        System.out.println("Thanks for playing!");
    }
}