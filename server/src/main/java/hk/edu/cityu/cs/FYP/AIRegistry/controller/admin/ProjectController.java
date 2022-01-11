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

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
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

    @GetMapping(path = {"/name","/{projectId}/name"})
    public ResponseEntity<String> getProjectName(@RequestParam(name = "projectId",required = false) @PathVariable(name = "projectId",required = false) String projectId, @RequestParam("lang") Lang lang) {

        if (projectId == null || projectId.equals("")){
            return ResponseEntity.badRequest().body("Missing project ID");
        }
        
        String projectName = projectService.getProjectName(Integer.parseInt(projectId), lang);

        return new ResponseEntity<String>(projectName, HttpStatus.OK);
    }


    @PutMapping(path = "/desc")
    public ResponseEntity<?> setDesc(@RequestBody MultiValueMap<String,String> mvm){


        Lang langT = Lang.valueOf(mvm.getFirst("lang"));
        var projectId = mvm.getFirst("projectId");
        var desc = mvm.getFirst("desc");
        
        projectService.setDesc(Integer.parseInt(projectId), desc, langT);


        return ResponseEntity.ok().build();
    }

    
    @PutMapping(path = {"/name","/{projectId}/name"})
    public ResponseEntity<?> setProjectName(@RequestBody MultiValueMap<String,String> mvm , @PathVariable(name = "projectId",required = false) Integer projId){


        Lang langT = Lang.valueOf(mvm.getFirst("lang"));
        var projectId = mvm.getFirst("projectId");
        var projectName = mvm.getFirst("projectName");
        
        projectService.setProjectName(Integer.parseInt(projectId), projectName, langT);


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

    @GetMapping(path={"/{projectId}/contact","/contact"})
    public ResponseEntity<?> getContact(@RequestParam(name = "projectId",required = false) @PathVariable(name = "projectId",required = false) Integer projectId){
        if (projectId == null){
            return ResponseEntity.badRequest().body("Missing Project Id");
        }
        var contact =  projectService.getContact(projectId);
        return ResponseEntity.ok(contact);
    }

    @PutMapping(path={"/{projectId}/contact","/contact"})
    public ResponseEntity<?> setContact(@PathVariable(name = "projectId",required = false) Integer projectId , @RequestBody Contact contact){
        if (projectId != null && projectId.intValue() == contact.getProjectId()){
            return ResponseEntity.badRequest().body("Project ID not match");
        }
        projectService.setContact(contact);
        return ResponseEntity.ok().build();
    }   


}
