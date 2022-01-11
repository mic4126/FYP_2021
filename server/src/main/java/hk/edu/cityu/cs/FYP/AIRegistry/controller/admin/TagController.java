package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;
import hk.edu.cityu.cs.FYP.AIRegistry.service.TagService;

@RestController()
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping(value = "/project/{projectId}/tag")
    public ResponseEntity<?> addTag(@PathVariable int projectId, @RequestBody Tag tag){
        
        if (projectId != tag.getProjectId()){
            return ResponseEntity.badRequest().body("Project Id not match");
        }
        tagService.addTag(tag);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/project/{projectId}/tag")
    public ResponseEntity<?> getTag(@PathVariable int projectId){
        var tagList = tagService.findAllTagsByprojectId(projectId);

        return new ResponseEntity<List<Tag>>(tagList, HttpStatus.OK);
        
    }

    @DeleteMapping(value = "/project/{projectId}/tag")
    public ResponseEntity<?> deleteTag(@PathVariable int projectId, @RequestBody Tag tag){
        tagService.deleteTag(tag);
        return ResponseEntity.ok().build();
    }
}
