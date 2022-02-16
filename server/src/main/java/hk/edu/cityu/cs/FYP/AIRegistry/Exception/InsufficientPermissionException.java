package hk.edu.cityu.cs.FYP.AIRegistry.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InsufficientPermissionException extends RuntimeException {

    public InsufficientPermissionException() {
    }

    public InsufficientPermissionException(String message) {
        super(message);
    }

    public InsufficientPermissionException(Throwable cause) {
        super(cause);
    }

    public InsufficientPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientPermissionException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
