package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

public class TagServiceTest extends BaseTest{
    
    @Autowired
    private TagService tagService;

    private Tag t = new Tag(15, "English", "Chinese", "SC");


    @Test
    void findAllTagsByprojectIdTest(){
        List<Tag> tagList = tagService.findAllTagsByprojectId(15);
        Tag t = tagList.get(0);
        assertThat(t.getTag()).isEqualTo("T");
        assertThat(t.getTagTC()).isEqualTo("TT");
        assertThat(t.getTagSC()).isEqualTo("TTT");
    }

    @Test
    void addTagTest(){
        tagService.addTag(t);
        List<Tag> tagList = tagService.findAllTagsByprojectId(15);
        assertThat(tagList).contains(t);
    }

    @Test
    void deleteTagTest()    {
        tagService.addTag(t);
        tagService.deleteTag(t);
        List<Tag> tagList = tagService.findAllTagsByprojectId(15);
        assertThat(tagList).doesNotContain(t);
    }

}
