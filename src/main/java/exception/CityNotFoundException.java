package exception;

public class CityNotFoundException extends Exception{

    public CityNotFoundException() {
        super("City not found!");
    }
}
