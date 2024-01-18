package exceptions;

public class InvalidWinningStrategy extends RuntimeException{
    public InvalidWinningStrategy(String message) {
        super(message);
    }
}
