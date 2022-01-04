package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.service.SearchService;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("q") String query, @RequestParam("lang") Lang lang) {
        var projects = searchService.search(query, lang);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    @GetMapping("/search/tag")
    public ResponseEntity<?> searchTag(@RequestParam("q") String query, @RequestParam("lang") Lang lang) {
        var projects = searchService.searchTag(query, lang);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
