package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = { LoginController.class })
public class LoginControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleUserNotFoundException(Exception ex) {
        var resp = new ResponseEntity<>("Username and/or password incorrect", HttpStatus.BAD_REQUEST);
        return resp;
    }

}
