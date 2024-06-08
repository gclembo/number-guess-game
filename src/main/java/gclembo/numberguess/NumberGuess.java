package gclembo.numberguess;

/**
 * This class is a number guessing game
 */
public class NumberGuess {
    private final int maxNum;
    private final int secretNum;
    private int numberOfGuesses;

    /**
     * Given a maximum number for the range of guesses, creates a new NumberGuess game
     * and the chooses a random integer between zero and the max number (inclusive) as
     * a secret number for the player to guess.
     * @param maxNum maximum possible number for the secret number chosen by the computer
     * @throws IllegalArgumentException if the given max number is less than or equal to zero.
     */
    public NumberGuess(int maxNum) {
        if (maxNum <= 0) {
            throw new IllegalArgumentException("Max number must be greater than 0");
        }
        this.maxNum = maxNum;
        this.numberOfGuesses = 0;
        this.secretNum = (int) (Math.random() * (this.maxNum + 1));
    }

    /**
     * Given a guess, returns difference between the given guess and the secret number
     * chosen by the computer.
     * @param guess Attempted guess for the secret number
     * @return Difference between the given guess and the true secret number
     * @throws IllegalArgumentException if guess is not in the range of the current game
     */
    public int makeGuess(int guess) {
        if (guess < 0 || guess > this.maxNum) {
            throw new IllegalArgumentException("Guess must be between 0 and " + this.maxNum);
        }

        this.numberOfGuesses++;
        return guess - this.secretNum;
    }

    /**
     * @return Number of guesses made by the player
     */
    public int getNumberOfGuesses() {
        return this.numberOfGuesses;
    }
}
