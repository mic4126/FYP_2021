package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hk.edu.cityu.cs.FYP.AIRegistry.annotation.IsProjectDeveloperOrAdmin;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.service.DetailService;

@RestController()
public class DetailController {

    @Autowired
    DetailService detailService;

    @GetMapping(path = "/project/{projectId}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetail(@PathVariable int projectId) {

        var detailList = detailService.getAllDetailsByProjectId(projectId);

        var response = new ResponseEntity<List<Detail>>(detailList, HttpStatus.OK);

        return response;
    }

    @IsProjectDeveloperOrAdmin
    @PutMapping(path = "/project/{projectId}/detail/{detailId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDetail(@PathVariable int projectId, @PathVariable int detailId,
            @RequestBody Detail detail) {

        if (projectId != detail.getProjectId() && detailId != detail.getDetailId()) {
            return ResponseEntity.badRequest().body("ProjectId or DetailId not match");
        }

        detailService.setDetail(detail);

        return ResponseEntity.ok().build();
    }

    @IsProjectDeveloperOrAdmin
    @PostMapping(path = "/project/{projectId}/detail", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> addDetail(@PathVariable int projectId, @RequestParam String detailName) {
        var detailId = detailService.addDetail(projectId, detailName);
        return new ResponseEntity<Integer>(detailId, HttpStatus.OK);

    }

    @IsProjectDeveloperOrAdmin
    @DeleteMapping("/project/{projectId}/detail/{detailId}")
    public ResponseEntity<?> deleteDetail(@PathVariable int projectId, @PathVariable int detailId) {

        detailService.deleteDetail(detailId);
        return ResponseEntity.ok().build();
    }

}
