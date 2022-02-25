package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import hk.edu.cityu.cs.FYP.AIRegistry.service.UserService;

public class TagControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void getTagTest() throws Exception {
        var mvcResult = this.mockMvc.perform(get("/project/15/tag")).andExpect(status().isOk()).andDo(print())
                .andReturn();

        String jsonStr = mvcResult.getResponse().getContentAsString();
        String expected = "[{\"projectId\":15,\"tag\":\"T\",\"tagTC\":\"TT\",\"tagSC\":\"TTT\"}]";

        JSONAssert.assertEquals(expected, jsonStr, false);

    }

    @Test
    void addTagTest() throws Exception {
        var request = post("/project/15/tag");
        String json = """
                {
                    "tag": "test",
                    "tagTC": "測試",
                    "tagSC": "測試",
                    "projectId": 15
                }
                """;
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);
        request.with(user(userService.getUserInfo("dev")));
        this.mockMvc.perform(request).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails("dev")
    void deleteTagTest() throws Exception {
        String json = """
                {
                    "projectId": 15,
                    "tag": "T",
                    "tagTC": "TT",
                    "tagSC": "TTT"
                }
                """;
        var request = delete("/project/15/tag");
        request.content(json);
        request.contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().is2xxSuccessful());
    }

}
