package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;

public class DetailDaoTest extends BaseTest {
    
    @Autowired
    private DetailDaoImpl detailDaoImpl;

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
    void getAllDetailsByProjectId() {
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        assertThat(detailList).hasSize(2);
        assertThat(detailList)
                .filteredOn(d -> 4 == d.getDetailId())
                .usingRecursiveFieldByFieldElementComparator()
                .contains(defaultDetail);

    }

    @Test
    void setDetailNameTest() {
        String detailName = "Test";
        detailDaoImpl.setDetailName(4, detailName);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName()).isEqualTo(detailName);
    }

    @Test
    void setDetailNameTCTest() {
        String detailName = "Test";
        detailDaoImpl.setDetailNameTC(4, detailName);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName_TC()).isEqualTo(detailName);
    }

    @Test
    void setDetailNameSCTest() {
        String detailName = "Test";
        detailDaoImpl.setDetailNameSC(4, detailName);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailName_SC()).isEqualTo(detailName);
    }

    @Test
    void setDetailDescTest() {
        String desc = "Test";
        detailDaoImpl.setDetailDesc(4, desc);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailDesc()).isEqualTo(desc);
    }

    @Test
    void setDetailDescTCTest() {
        String desc = "Test";
        detailDaoImpl.setDetailDescTC(4, desc);
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail.getDetailDesc_TC()).isEqualTo(desc);
    }

    @Test
    void setDetailDescSCTest() {
        String desc = "Test";
        detailDaoImpl.setDetailDescSC(4, desc);
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

        detailDaoImpl.setDetail(defaultDetail);

        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);
        var detail = detailList.stream()
                .filter(d -> 4 == d.getDetailId()).findFirst().get();
        assertThat(detail).usingRecursiveComparison().isEqualTo(defaultDetail);
    }

    @Test
    void addDetail() {
        var origDetailId = defaultDetail.getDetailId();
        detailDaoImpl.addDetail(defaultDetail);
        assertThat(defaultDetail.getDetailId()).isNotEqualTo(origDetailId);
    }

    @Test
    void deleteDetailTest() {

        detailDaoImpl.deleteDetail(defaultDetail.getDetailId());
        var detailList = detailDaoImpl.getAllDetailsByProjectId(15);

        assertThat(detailList)
                .usingRecursiveFieldByFieldElementComparator()
                .doesNotContain(defaultDetail);

    }
}
