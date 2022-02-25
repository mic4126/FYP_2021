package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.DetailDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

public class DetailServiceTest extends BaseTest {
    
    @Autowired
    private DetailService detailService;
    
    @Autowired
    private DetailDao detailDaoImpl;

    private Detail defaultDetail = new Detail();

    @BeforeEach
    private void init() {
        defaultDetail.setDetailName("English detail");
        defaultDetail.setDetailDesc("English detail");
        defaultDetail.setDetailName_TC("TC Detail");
        defaultDetail.setDetailDesc_TC("TC Detail");
        defaultDetail.setDetailName_SC("SC Detail");
        defaultDetail.setDetailDesc_SC("SC Detail");
        defaultDetail.setProjectId(15);
        defaultDetail.setDetailId(4);
    }

    @Test
    void setDetailNameTest() {
        String detailName = "Test";
        detailService.setDetailName(4, detailName,Lang.ENG);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName()).isEqualTo(detailName);
    }

    @Test
    void setDetailNameTCTest() {
        String detailName = "Test";
        detailService.setDetailName(4, detailName,Lang.TC);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName_TC()).isEqualTo(detailName);
    }

    @Test
    void setDetailNameSCTest() {
        String detailName = "Test";
        detailService.setDetailName(4, detailName,Lang.SC);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName_SC()).isEqualTo(detailName);
    }

    @Test
    void getAllDetailsByProjectId() {
        var detailList = detailService.getAllDetailsByProjectId(15);
        assertThat(detailList).hasSize(2);
        assertThat(detailList)
                .filteredOn(d -> 4 == d.getDetailId())
                .usingRecursiveFieldByFieldElementComparator()
                .contains(defaultDetail);
    }
    
    @Test
    void setDetailDescTest() {
        String desc = "Test";
        detailService.setDetailDesc(4, desc,Lang.ENG);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailDesc()).isEqualTo(desc);
    }

    @Test
    void setDetailDescTCTest() {
        String desc = "Test";
        detailService.setDetailDesc(4, desc,Lang.TC);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailDesc_TC()).isEqualTo(desc);
    }

    @Test
    void setDetailDescSCTest() {
        String desc = "Test";
        detailService.setDetailDesc(4, desc,Lang.SC);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailDesc_SC()).isEqualTo(desc);
    }

    @Test
    void setDetailTest() {
        defaultDetail.setDetailName("TestEng");
        defaultDetail.setDetailName_TC("TestTC");
        defaultDetail.setDetailName_SC("TestSC");
        defaultDetail.setDetailDesc("TestDesc");
        defaultDetail.setDetailDesc_TC("TestDescTc");
        defaultDetail.setDetailDesc_SC("TestDescSC");

        detailService.setDetail(defaultDetail);

        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail).usingRecursiveComparison().isEqualTo(defaultDetail);
    }

    @Test
    void addDetail() {
        var origDetailId = defaultDetail.getDetailId();
        var id = detailService.addDetail(defaultDetail.getProjectId(),defaultDetail.getDetailName());
        assertThat(id).isNotEqualTo(origDetailId);
    }

    @Test
    void deleteDetailTest() {

        detailService.deleteDetail(defaultDetail.getDetailId());
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);

        assertThat(detailList)
                .usingRecursiveFieldByFieldElementComparator()
                .doesNotContain(defaultDetail);

    }

}
