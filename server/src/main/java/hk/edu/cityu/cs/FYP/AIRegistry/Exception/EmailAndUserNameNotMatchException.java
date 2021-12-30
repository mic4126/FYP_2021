package hk.edu.cityu.cs.FYP.AIRegistry.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailAndUserNameNotMatchException extends RuntimeException{
    public EmailAndUserNameNotMatchException(){
        super();
    };

    public EmailAndUserNameNotMatchException(String message){
        super(message);
    };
}
