package PlayGame.exception;

public class CommonExceptions extends Exception{
    public CommonExceptions(String errorMessage) {
        super(errorMessage);
    }
    public CommonExceptions(String errorMessage,String s) {
        super(String.format(errorMessage,s));
    }

}
