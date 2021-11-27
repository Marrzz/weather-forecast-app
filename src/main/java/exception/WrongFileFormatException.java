package exception;

public class WrongFileFormatException extends Exception{

    public WrongFileFormatException(){
        super("Wrong File Format, please use a txt file!");
    }
}
