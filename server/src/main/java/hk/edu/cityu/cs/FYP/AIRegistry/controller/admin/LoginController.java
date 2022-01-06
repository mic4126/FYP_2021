package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.LoginReq;
import hk.edu.cityu.cs.FYP.AIRegistry.service.JWTServiceImpl;

@RestController
public class LoginController  {
    
    @Autowired
    JWTServiceImpl jwtServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @ModelAttribute LoginReq loginReq){
        var jwt = jwtServiceImpl.genToken(loginReq);
        return new ResponseEntity<>(jwt,HttpStatus.OK);
    }
}
