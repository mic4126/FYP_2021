package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

public class TagDaoImplTest extends BaseTest {

    @Autowired
    private TagDaoImpl tagDaoImpl;

    private Tag t = new Tag(15, "English", "Chinese", "SC");

    @Test
    void getTagsTest() {
        List<Tag> tagList = tagDaoImpl.getTags(15);
        System.out.println(tagList.get(0).getTag() + ":" + tagList.get(0).getTagSC() + ":" + tagList.get(0).getTagTC());
        Tag t = tagList.get(0);
        assertThat(t.getTag()).isEqualTo("T");
        assertThat(t.getTagTC()).isEqualTo("TT");
        assertThat(t.getTagSC()).isEqualTo("TTT");
    }

    @Test
    void deleteTagTest() {
        tagDaoImpl.addTag(t);
        tagDaoImpl.deleteTag(t);
        List<Tag> tagList = tagDaoImpl.getTags(15);
        assertThat(tagList).doesNotContain(t);
    }

    @Test
    void addTagTest() {
        tagDaoImpl.addTag(t);
        List<Tag> tagList = tagDaoImpl.getTags(15);
        assertThat(tagList).contains(t);
    }

    @Test
    void searchByTagTest(){
        var projectIds = tagDaoImpl.searchByTag("T");
        assertThat(projectIds).contains(15);
    }

}
