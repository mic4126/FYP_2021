package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.UserAlreadyExistException;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody UserInfo userInfo) {

        try {
            userService.createUser(userInfo);
        } catch (UserAlreadyExistException e) {
            LOGGER.error(e.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();

    }

    @PostMapping(path = { "/user/password/{username}", "/user/password" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> resetPassword(@PathVariable(required = false) String username,
            @RequestBody ResetPasswordInfo resetPasswordInfo) {
        try {
            userService.resetPassword(resetPasswordInfo);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Reset Password for " + resetPasswordInfo.getUsername() + " done.");
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = { "/user/password/{username}", "/user/password" })
    public @ResponseBody ResponseEntity<?> changePassword(@PathVariable(required = false) String username,
            @RequestBody UserInfo userInfo) {

        userService.changePassword(userInfo);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user/{username}")
    public @ResponseBody ResponseEntity<?> changeUserInfo(@PathVariable String username,
            @RequestBody UserInfo userInfo) {

        userService.changeUserInfo(userInfo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user/{username}")
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable String username) {

        userService.deleteUser(username);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user" )
    public @ResponseBody ResponseEntity<?> getAllUsers(@RequestParam(required = false) String userType) {
        List<UserInfo> userInfoList = new ArrayList<>();
        if ("dev".equals(userType)){
            userInfoList = userService.getAllDevs();
        } else if( "admin".equals(userType)){
            userInfoList = userService.getAllAdmins();
        }else {
            userInfoList = userService.getAllUsers();
        }

        return ResponseEntity.ok(userInfoList);

    }
}
