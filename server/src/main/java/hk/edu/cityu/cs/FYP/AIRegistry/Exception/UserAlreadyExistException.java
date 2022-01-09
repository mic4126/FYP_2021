package hk.edu.cityu.cs.FYP.AIRegistry.Exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String arg0) {
        super(arg0);
    }

    public UserAlreadyExistException(Throwable arg0) {
        super(arg0);
    }

    public UserAlreadyExistException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public UserAlreadyExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
    
}
