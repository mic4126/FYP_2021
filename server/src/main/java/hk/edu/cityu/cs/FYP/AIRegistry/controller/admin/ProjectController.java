package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.request.CreateProjectReq;
import hk.edu.cityu.cs.FYP.AIRegistry.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    
    @PostMapping(path = "")
    public ResponseEntity<?> addProject(@RequestBody CreateProjectReq createProjectReq) {

        var projectId = projectService.addProject(createProjectReq.getProjectName(), createProjectReq.getDevelopers());

        return new ResponseEntity<Integer>(projectId, HttpStatus.OK);
    }

    @GetMapping(path = "/desc")
    public ResponseEntity<String> getDesc(@RequestParam("projectId") String projectId, @RequestParam("lang") String lang) {
        Lang langT = Lang.valueOf(lang);
        String desc = projectService.getDesc(Integer.parseInt(projectId), langT);

        
        return new ResponseEntity<String>(desc, HttpStatus.OK);
    }

    @PutMapping(path = "/desc")
    public ResponseEntity<?> setDesc(@RequestBody MultiValueMap<String,String> mvm){


        Lang langT = Lang.valueOf(mvm.getFirst("lang"));
        var projectId = mvm.getFirst("projectId");
        var desc = mvm.getFirst("desc");
        
        projectService.setDesc(Integer.parseInt(projectId), desc, langT);


        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/{projectId}/user")
    public ResponseEntity<?> addDevToProject(@PathVariable("projectId")int projectId,@RequestParam("username") String username){
        projectService.addDeveloper(projectId, username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path="/{projectId}/user/{username}")
    public ResponseEntity<?> deleteDevFromProject(@PathVariable("projectId")int projectId, @PathVariable("username") String username){
        projectService.deleteDeleloperAssign(projectId, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{projectId}/user")
    public ResponseEntity<?> getProjectDevelopers(@PathVariable("projectId") int projectId){
        var devs = projectService.getDevelopers(projectId);
        return ResponseEntity.ok(devs);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAllProject(){
        var projects = projectService.getAllProject();
        return ResponseEntity.ok(projects);
    }


}
