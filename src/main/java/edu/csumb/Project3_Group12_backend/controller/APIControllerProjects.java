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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
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
        firebaseService.saveNewProject(new Project(projectName, budget, currency, description, email, proposer, urlString,
                                                    datePublished, isClaimed, claimedBy, anon));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/claimProject")
    public List<Project> claimProject(@RequestParam Project project, @RequestParam List<Project> projectsClaimedList, @RequestParam Fullfiller fullfiller)
            throws IOException, ExecutionException, InterruptedException {
        return firebaseService.claimProject(project, projectsClaimedList, fullfiller);
    }


}

//      -- Testing --
//    @GetMapping("/")
//    public String test(){
//        return "It works!";
//    }

