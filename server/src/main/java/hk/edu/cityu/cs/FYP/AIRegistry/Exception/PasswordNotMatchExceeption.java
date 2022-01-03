package hk.edu.cityu.cs.FYP.AIRegistry.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PasswordNotMatchExceeption extends RuntimeException {

    public PasswordNotMatchExceeption() {
    }

    public PasswordNotMatchExceeption(String arg0) {
        super(arg0);
    }

    public PasswordNotMatchExceeption(Throwable arg0) {
        super(arg0);
    }

    public PasswordNotMatchExceeption(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public PasswordNotMatchExceeption(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
    
}
