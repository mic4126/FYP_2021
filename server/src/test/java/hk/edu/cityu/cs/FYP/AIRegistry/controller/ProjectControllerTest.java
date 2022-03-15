package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;

public class ProjectControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProjectDao projectDao;

    @Test
    @WithUserDetails("admin")
    void addProjectTest() throws Exception {
        String json = """
                {
                    "projectName":"Test",
                    "developers":["dev"]
                }
                """;

        var request = post("/project");
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void getDescTest_ENG() throws Exception {
        var request = get("/project/desc");
        request.param("lang", "ENG");
        request.param("projectId", 15 + "");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Lorem_ipsum"));
    }

    @Test
    void getDescTest_TC() throws Exception {
        var request = get("/project/desc");
        request.param("lang", "TC");
        request.param("projectId", 15 + "");
        request.accept(MediaType.TEXT_PLAIN_VALUE + " ;charset=UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("繁中測試"));
    }

    @Test
    void getDescTest_SC() throws Exception {
        var request = get("/project/desc");
        request.param("lang", "SC");
        request.param("projectId", 15 + "");
        request.accept(MediaType.TEXT_PLAIN_VALUE + " ;charset=UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("簡中簡中簡中"));
    }

    @Test
    void getProjectNameTest_TC() throws Exception {
        var request = get("/project/name");
        request.param("lang", "TC");
        request.param("projectId", 15 + "");
        request.accept(MediaType.TEXT_PLAIN_VALUE + " ;charset=UTF-8");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("測試"));

    }

    @Test
    void getProjectNameTest_ENG() throws Exception {
        var request = get("/project/name");
        request.param("lang", "ENG");
        request.param("projectId", 15 + "");
        request.accept(MediaType.TEXT_PLAIN_VALUE + " ;charset=UTF-8");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("測試測試"));

    }

    @Test
    void getProjectNameTest_SC() throws Exception {
        var request = get("/project/name");
        request.param("lang", "SC");
        request.param("projectId", 15 + "");
        request.accept(MediaType.TEXT_PLAIN_VALUE + " ;charset=UTF-8");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("測試測試"));

    }

    @Test
    @WithUserDetails("dev")
    void setProjectDescTest_TC() throws Exception {
        String json = """
                {
                    "lang":"TC",
                    "projectId":"15",
                    "desc":"Test"
                }
                """;

        var request = put("/project/desc");
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request).andDo(print()).andExpect(status().isOk());
        assertThat(projectDao.getDescTC(15)).isEqualTo("Test");

    }

    @Test
    @WithUserDetails("dev")
    void setProjectDescTest_ENG() throws Exception {
        String json = """
                {
                    "lang":"ENG",
                    "projectId":"15",
                    "desc":"Test"
                }
                """;

        var request = put("/project/desc");
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request).andDo(print()).andExpect(status().isOk());
        assertThat(projectDao.getDesc(15)).isEqualTo("Test");

    }

    @Test
    @WithUserDetails("dev")
    void setProjectDescTest_SC() throws Exception {
        String json = """
                {
                    "lang":"SC",
                    "projectId":"15",
                    "desc":"Test"
                }
                """;

        var request = put("/project/desc");
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request).andDo(print()).andExpect(status().isOk());
        assertThat(projectDao.getDescSC(15)).isEqualTo("Test");

    }

    @Test
    @WithUserDetails("dev")
    void setProjectNameTest_TC() throws Exception {

        var request = put("/project/15/name");
        request.contentType(MediaType.APPLICATION_FORM_URLENCODED);
        var params = new HashMap<String, String>();
        params.put("projectId", 15 + "");
        params.put("lang", "TC");
        params.put("projectName", "Test");
        var body = ParamToString(params);
        request.content(body);
        request.characterEncoding(StandardCharsets.UTF_8);

        mvc.perform(request).andExpect(status().isOk());
        assertThat(projectDao.getProjectNameTC(15));

    }

    @Test
    @WithUserDetails("dev")
    void setProjectNameTest_ENG() throws Exception {

        var request = put("/project/15/name");
        request.contentType(MediaType.APPLICATION_FORM_URLENCODED);
        var params = new HashMap<String, String>();
        params.put("projectId", 15 + "");
        params.put("lang", "ENG");
        params.put("projectName", "Test");
        var body = ParamToString(params);
        request.content(body);
        request.characterEncoding(StandardCharsets.UTF_8);

        mvc.perform(request).andExpect(status().isOk());
        assertThat(projectDao.getProjectName(15));

    }

    @Test
    @WithUserDetails("dev")
    void setProjectNameTest_SC() throws Exception {

        var request = put("/project/15/name");
        request.contentType(MediaType.APPLICATION_FORM_URLENCODED);
        var params = new HashMap<String, String>();
        params.put("projectId", 15 + "");
        params.put("lang", "SC");
        params.put("projectName", "Test");
        var body = ParamToString(params);
        request.content(body);
        request.characterEncoding(StandardCharsets.UTF_8);

        mvc.perform(request).andExpect(status().isOk());
        assertThat(projectDao.getProjectNameSC(15));
    }

    @Test
    @WithUserDetails("admin")
    void addDevToProjectTest() throws Exception {
        var requestBuilder = post("/project/{projectId}/user", 15 + "");
        requestBuilder.param("username", "dev6");

        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithUserDetails("admin")
    void deleteDevFromProjectTest() throws Exception {
        var requestBuilder = delete("/project/{projectId}/user/dev", 15 + "");

        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithUserDetails("admin")
    void getProjectDevelopersTest() throws Exception {

        String expectedContent = """
                [{"username":"dev","firstName":"admin1","lastName":"admin","email":"mic4126@gmail.com","userType":"dev"}]
                """;
        var requestBuilder = get("/project/{projectId}/user", 15 + "");

        var mvcResult = mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedContent)).andReturn();

    }

    @Test
    void getContactTest() throws Exception {
        var requestBuilder = get("/project/{projectId}/contact", 15 + "");
        requestBuilder.param("projectId", 15 + "");

        var jsonContent = """
                {"projectId":15,"email":"ab.com@abc.com","phoneNumber":"12345678","department":"321","department_TC":"123","department_SC":"456","url":"example.com"}
                """;
        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jsonContent));
    }

    @Test
    @WithUserDetails("dev")
    void setContactTest() throws Exception {
        var requestBuilder = put("/project/{projectId}/contact", 15);
        var jsonContent = """
                {"projectId":15,"email":"abc@abc.com","phoneNumber":"12345678","department":"321","department_TC":"123","department_SC":"456","url":"example.com"}
                """;
        requestBuilder.content(jsonContent);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
        assertThat(projectDao.getContact(15).getEmail()).isEqualTo("abc@abc.com");
    }

    @Test
    void getAllProjectsTest() throws Exception {
        var requestBuilder = get("/project");
        var jsonContent = """
                [{"projectId":15,"projectName":"測試測試","projectDesc":"Lorem_ipsum","contact":null,"detail":null},{"projectId":16,"projectName":"test1","projectDesc":null,"contact":null,"detail":null},{"projectId":18,"projectName":"test2","projectDesc":null,"contact":null,"detail":null},{"projectId":19,"projectName":"test5","projectDesc":null,"contact":null,"detail":null},{"projectId":20,"projectName":"test3","projectDesc":"test projectID 20 eng","contact":null,"detail":null},{"projectId":21,"projectName":"TESTPROJECT","projectDesc":null,"contact":null,"detail":null}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

    }

    @Test
    void getAllProjectsTest_ENG() throws Exception {
        var requestBuilder = get("/project");
        requestBuilder.param("lang", "ENG");
        var jsonContent = """
                [{"projectId":15,"projectName":"測試測試","projectDesc":"Lorem_ipsum","contact":null,"detail":null},{"projectId":16,"projectName":"test1","projectDesc":null,"contact":null,"detail":null},{"projectId":18,"projectName":"test2","projectDesc":null,"contact":null,"detail":null},{"projectId":19,"projectName":"test5","projectDesc":null,"contact":null,"detail":null},{"projectId":20,"projectName":"test3","projectDesc":"test projectID 20 eng","contact":null,"detail":null},{"projectId":21,"projectName":"TESTPROJECT","projectDesc":null,"contact":null,"detail":null}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

    }

    @Test
    void getAllProjectsTest_TC() throws Exception {
        var requestBuilder = get("/project");
        requestBuilder.param("lang", "TC");
        var jsonContent = """
                [{"projectId":15,"projectName":"測試","projectDesc":"繁中測試","contact":null,"detail":null},{"projectId":16,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":18,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":19,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":20,"projectName":null,"projectDesc":"test projectID 20 tc","contact":null,"detail":null},{"projectId":21,"projectName":null,"projectDesc":null,"contact":null,"detail":null}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

    }

    @Test
    void getAllProjectsTest_SC() throws Exception {
        var requestBuilder = get("/project");
        requestBuilder.param("lang", "SC");
        var jsonContent = """
                [{"projectId":15,"projectName":"測試測試","projectDesc":"簡中簡中簡中","contact":null,"detail":null},{"projectId":16,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":18,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":19,"projectName":null,"projectDesc":null,"contact":null,"detail":null},{"projectId":20,"projectName":null,"projectDesc":"test projectID 20 sc","contact":null,"detail":null},{"projectId":21,"projectName":null,"projectDesc":null,"contact":null,"detail":null}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

    }

    @WithUserDetails("dev")
    @Test
    void getProjectStatusTest() throws Exception {
        var requestBuilder = get("/project/15/status");
        mvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    @WithUserDetails("dev")
    @Test
    void enableProjectTest() throws Exception {
        var requestBuilder = put("/project/15/status/enable");
        mvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    @WithUserDetails("dev")
    @Test
    void disableProjectTest() throws Exception {
        var requestBuilder = put("/project/15/status/disable");
        mvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    private String ParamToString(Map<String, String> params) {

        var keys = params.keySet();
        var it = keys.iterator();
        String sb = "";
        while (it.hasNext()) {
            var key = it.next();
            var value = params.get(key);
            sb = String.join("&", sb, key + "=" + value);

        }
        return sb.toString();

    }

}
