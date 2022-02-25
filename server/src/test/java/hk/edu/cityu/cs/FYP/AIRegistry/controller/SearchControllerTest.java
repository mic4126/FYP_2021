package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


public class SearchControllerTest extends BaseControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchTest() throws Exception{
        var request = get("/search");
        request.param("q", "Test");
        request.param("lang", "ENG");
        var mvcResult = this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();        

        var json = mvcResult.getResponse().getContentAsString();        

        var ctx = JsonPath.parse(json);

        var projectJson = ctx.read("$[?(@.projectId==15)]");

        assertThat(projectJson).isNotNull();
     
    }

    
    @Test
    void searchTagTest() throws Exception{
        var request = get("/search/tag");
        request.param("q", "T");
        request.param("lang", "ENG");
        var mvcResult = this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();        

        var json = mvcResult.getResponse().getContentAsString();        

        var ctx = JsonPath.parse(json);

        var projectJson = ctx.read("$[?(@.projectId==15)]");

        assertThat(projectJson).isNotNull();
     
    }

}
