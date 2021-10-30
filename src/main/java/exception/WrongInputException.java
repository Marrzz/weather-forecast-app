package exception;

public class WrongInputException extends Exception{

    public WrongInputException() {
        super("City name should contain only letters!");
    }
}
