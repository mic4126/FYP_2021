package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TagController.class)
public class TagControllerAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleDuplicatedRecord(Exception e) {
        var resp = ResponseEntity.badRequest().body("English Tag name is duplicated");
        return resp;
    }
}
