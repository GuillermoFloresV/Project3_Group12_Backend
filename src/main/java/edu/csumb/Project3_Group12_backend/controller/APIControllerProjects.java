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

    @PostMapping("/createNewProject")
    public ResponseEntity<Object> createNewProject(@RequestParam Integer project_id, @RequestParam String projectName, @RequestParam float budget, String currency,
                                                        @RequestParam boolean isOpen, @RequestParam String urlString, @RequestParam String datePublished, @RequestParam boolean isClaimed, @RequestParam boolean anon)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.saveNewProject(new Project(project_id, projectName, budget, currency, urlString,  datePublished,isOpen, isClaimed , anon));
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

