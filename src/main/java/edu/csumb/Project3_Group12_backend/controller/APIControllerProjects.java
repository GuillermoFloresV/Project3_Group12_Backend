package edu.csumb.Project3_Group12_backend.controller;

import edu.csumb.Project3_Group12_backend.Fullfiller;
import edu.csumb.Project3_Group12_backend.Project;
import edu.csumb.Project3_Group12_backend.firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class APIControllerProjects {


    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getAllProjects")
    public List<Project> getAllProjects() throws ExecutionException, InterruptedException {
        return firebaseService.getProjects();
    }
        //Integer project_id, String projectName, float budget, String description, String email,
        //                   String proposer, String currency, String urlString,
        //                   String datePublished, boolean isClaimed, String claimedBy, boolean anon)
    @PostMapping("/createNewProject")
    public ResponseEntity<Object> createNewProject(@RequestParam String projectName, @RequestParam float budget, @RequestParam String currency,
                                                   @RequestParam String description, @RequestParam String email, @RequestParam String proposer,
                                                   @RequestParam String urlString, @RequestParam String datePublished, @RequestParam boolean isClaimed,
                                                   @RequestParam String claimedBy, @RequestParam boolean anon)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.saveNewProject(new Project(projectName.trim(), budget, currency.trim(), description.trim(), email.trim(), proposer.trim(), urlString.trim(),
                                                    datePublished.trim(), isClaimed, claimedBy.trim(), anon));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/claimProject")
    public ResponseEntity<Object> claimProject(@RequestParam String id, @RequestParam String email)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.claimTheProject(id.trim(), email.trim());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProject")
    public ResponseEntity<Object> deleteProject(@RequestParam String id)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.deleteTheProject(id.trim());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/updateProject")
    public ResponseEntity<Object> updateProject(@RequestParam String id, @RequestParam String projectName, @RequestParam float budget, @RequestParam String description,
                                                @RequestParam String email, @RequestParam String currency,  @RequestParam String urlString,
                                                @RequestParam String datePublished, @RequestParam boolean anon)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.updateTheProject(id.trim(), projectName.trim(), budget, description.trim(), email.trim(), currency.trim(),
                urlString.trim(), datePublished.trim(), anon);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




}



