package hk.edu.cityu.cs.FYP.AIRegistry.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

class TagMapperTest extends BaseMappertest {

    @Autowired
    private TagMapper tagMapper;
    private Tag t = new Tag(15, "English", "Chinese", "SC");

    @Test
    void getTagsByProjectIdTest() {
        List<Tag> tagList = tagMapper.getTagsByProjectId(15);
        System.out.println(tagList.get(0).getTag() + ":" + tagList.get(0).getTagSC() + ":" + tagList.get(0).getTagTC());
        Tag t = tagList.get(0);
        assertThat(t.getTag()).isEqualTo("T");
        assertThat(t.getTagTC()).isEqualTo("TT");
        assertThat(t.getTagSC()).isEqualTo("TTT");

    }


    @Test
    void addTagTest(){        
        tagMapper.addTag(t);
        List<Tag> tagList = tagMapper.getTagsByProjectId(15);        
        assertThat(tagList).contains(t);
    }

    @Test
    void deleteTest(){
        tagMapper.addTag(t);
        tagMapper.deleteTag(t);
        List<Tag> tagList = tagMapper.getTagsByProjectId(15);
        assertThat(tagList).doesNotContain(t);
    }

    @Test
    void searchByTagTest(){
        var projectIds = tagMapper.searchByTag("T");
        assertThat(projectIds).contains(15);
    }

}
