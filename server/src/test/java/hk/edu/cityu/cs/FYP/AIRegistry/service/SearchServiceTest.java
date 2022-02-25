package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;


public class SearchServiceTest extends BaseTest {
    
    @Autowired
    private SearchService searchService;
    private String query = "測試測試";
    private String tag = "T";

    @Test
    void searchTest_TC(){        
        Lang lang = Lang.TC;
        var projects = searchService.search(query, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }

    @Test
    void searchTest_SC(){        
        Lang lang = Lang.SC;
        var projects = searchService.search(query, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }

    @Test
    void searchTest_ENG(){
        Lang lang = Lang.ENG;
        var projects = searchService.search(query, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }

    @Test
    void searchTagTest_TC(){
        Lang lang = Lang.TC;
        var projects = searchService.searchTag(tag, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }

    @Test
    void searchTagTest_SC(){
        Lang lang = Lang.SC;
        var projects = searchService.searchTag(tag, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }

    @Test
    void searchTagTest_ENG(){
        Lang lang = Lang.ENG;
        var projects = searchService.searchTag(tag, lang);
        assertThat(projects).hasSizeGreaterThan(0);
    }
}
