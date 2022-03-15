package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import hk.edu.cityu.cs.FYP.AIRegistry.service.EmailService;

public class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmailService emailService;

    @Test
    @WithMockUser(roles = "admin")
    void createUserTest() throws Exception {

        doNothing().when(emailService).sendInitPassword(any());

        var request = post("/admin/user");
        request.content("""
                {
                    "username": "admin10",
                    "firstName": "admin",
                    "lastName": "admin",
                    "email": "example@example.com",
                    "userType": "admin"
                }
                """);
        request.contentType(MediaType.APPLICATION_JSON_VALUE);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void resetPasswordTest() throws Exception {
        doNothing().when(emailService).sendResetPassword(any());

        var request = post("/admin/user/password/admin");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content("""
                {
                    "email":"mic4126@gmail.com",
                    "username":"admin"
                }
                """);

        mvc.perform(request).andExpect(status().is2xxSuccessful());

    }

    @Test
    void resetPasswordTest_EmailNotMatch() throws Exception {
        doNothing().when(emailService).sendResetPassword(any());

        var request = post("/admin/user/password/admin");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content("""
                {
                    "email":"example@example.com",
                    "username":"admin"
                }
                """);

        mvc.perform(request).andExpect(status().is(500));

    }

    @Test
    @WithUserDetails("admin")
    void changePasswordTest() throws Exception {
        var request = put("/admin/user/password/admin");
        request.content("""
                {
                    "username": "admin",
                    "password": "123",
                    "newPassword": "123",
                    "newPasswordCheck": "123"
                }
                """);
        request.contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithUserDetails("admin")
    void changeUserInfoTest() throws Exception {
        var request = put("/admin/user/admin");
        request.content("""
                {
                    "username": "admin",
                    "email": "mic4126@gmail.com",
                    "firstName": "nimda",
                    "lastName": "1234",
                    "password": "123"
                }
                """);
        request.contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void getAllUsersTest_AllUser() throws Exception {
        var request = get("/admin/user");
        var mvcResult = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var json = mvcResult.getResponse().getContentAsString();

        var ctx = JsonPath.parse(json);
        var userJsonMap = ctx.read("$[?(@.username=='admin')]");

        assertThat(userJsonMap).isNotNull();
    }

    @Test
    @WithUserDetails("admin")
    void getAllUsersTest_Admin() throws Exception {
        var request = get("/admin/user");
        request.param("userType", "dev");
        var mvcResult = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var json = mvcResult.getResponse().getContentAsString();

        var ctx = JsonPath.parse(json);
        var userJsonMap = ctx.read("$[?(@.username=='dev')]");

        assertThat(userJsonMap).isNotNull();
    }

    @Test
    @WithUserDetails("admin")
    void getAllUsersTest_Dev() throws Exception {
        var request = get("/admin/user");
        request.param("userType", "admin");
        var mvcResult = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var json = mvcResult.getResponse().getContentAsString();

        var ctx = JsonPath.parse(json);
        var userJsonMap = ctx.read("$[?(@.username=='admin')]");

        assertThat(userJsonMap).isNotNull();
    }

    @Test
    @WithUserDetails("dev")
    void getUsersProjectsTest() throws Exception{
        var request = get("/admin/user/{username}/project", "dev");
        var mvcResult = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        var json = mvcResult.getResponse().getContentAsString();

        var projectJson = JsonPath.read(json, "$[?(@.projectId==15)]");

        assertThat(projectJson).isNotNull();
    }

    @Test
    @WithUserDetails("admin")
    void getUserInfoTest() throws Exception{
        var request = get("/admin/user/{username}","admin");
        var mvcResult = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        var json = mvcResult.getResponse().getContentAsString();
        String usrename = JsonPath.read(json, "$.username");

        assertThat(usrename).isEqualTo("admin");
    }

}
