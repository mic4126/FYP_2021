package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.model.LoginReq;
import hk.edu.cityu.cs.FYP.AIRegistry.service.JWTServiceImpl;

@RestController
public class LoginController {

    @Autowired
    JWTServiceImpl jwtServiceImpl;

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        LOGGER.info("Received Login Request: " + loginReq.getUsername());
        var jwt = jwtServiceImpl.genToken(loginReq);
        LOGGER.info(loginReq.getUsername()+" login success");
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
