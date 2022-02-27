package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.DetailDao;

public class DetailControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DetailDao detailDao;

    @Test
    void getDetailTest() throws Exception {
        var requestBuilder = get("/project/{projectId}/detail", 15);

        var jsonContent = """
                [{"detailName":"Test Detail","detailName_TC":"測試","detailName_SC":"測試","detailDesc":"TestTESTTTTTTT","detailDesc_TC":"測試測試測試測試","detailDesc_SC":"測試","projectId":15,"detailId":1,"attachments":null},{"detailName":"English detail","detailName_TC":"TC Detail","detailName_SC":"SC Detail","detailDesc":"English detail","detailDesc_TC":"TC Detail","detailDesc_SC":"SC Detail","projectId":15,"detailId":4,"attachments":null}]
                """;

        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    @WithUserDetails("dev")
    void updateDetailTest() throws Exception {
        var json = """
                {
                    "detailId": 1,
                    "projectId": 15,
                    "detailName": "Test Test",
                    "detailName_TC": "測試",
                    "detailName_SC": "測試",
                    "detailDesc": "TestTESTTTTTTT",
                    "detailDesc_TC": "測試測試測試測試",
                    "detailDesc_SC": "測試"
                }
                """;

        var requestBuilder = put("/project/{projectId}/detail/{detailId}", 15, 1);
        requestBuilder.content(json);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
        var detail = detailDao.getAllDetailsByProjectId(15).stream().filter(d -> d.getDetailId() == 1).findFirst()
                .get();
        assertThat(detail.getDetailName()).isEqualTo("Test Test");
    }

    @Test
    @WithUserDetails("dev")
    void addDetailTest() throws Exception{
        var requestBuilder = post("/project/{projectId}/detail", 15);
        requestBuilder.content("detailName=Test");
        requestBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("dev")
    void deleteDetailTest() throws Exception{
        var requestBuilder = delete("/project/{projectId}/detail/{detailId}", 15,1);
                

        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
    }

}
