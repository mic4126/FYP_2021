package hk.edu.cityu.cs.FYP.AIRegistry.Exception;

public class StorageFolderCannotAccessException extends RuntimeException{

    public StorageFolderCannotAccessException() {
    }

    public StorageFolderCannotAccessException(String arg0) {
        super(arg0);
    }

    public StorageFolderCannotAccessException(Throwable arg0) {
        super(arg0);
    }

    public StorageFolderCannotAccessException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public StorageFolderCannotAccessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
    
}
