package edu.csumb.Project3_Group12_backend.controller;
import edu.csumb.Project3_Group12_backend.Fullfiller;
import edu.csumb.Project3_Group12_backend.FullfillerService;
import edu.csumb.Project3_Group12_backend.firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
//    @GetMapping("/")
//    public String test(){
//        return "It works!";
//    }

    @Autowired
    FirebaseService firebaseService;
//
//    @GetMapping("/getFullfillerDetails")
//    public Fullfiller getFullfiller(@RequestParam String name ) throws InterruptedException, ExecutionException{
//        return fullfillerService.getFullfillerDetails(name);
//    }
//
//    @PostMapping("/createFulllfiller")
//    public String createFullfiller(@RequestBody Fullfiller fullfiller ) throws InterruptedException, ExecutionException {
//        return fullfillerService.saveFullfillerDetails(fullfiller);
//    }
//
//    @PutMapping("/updateFullfiller")
//    public String updateFullfiller(@RequestBody Fullfiller fullfiller  ) throws InterruptedException, ExecutionException {
//        return fullfillerService.updateFullfillerDetails(fullfiller);
//    }
//
//    @DeleteMapping("/deleteFullfiller")
//    public String deleteFullfiller(@RequestParam String name){
//        return fullfillerService.deleteFullfiller(name);
//    }
//
    @GetMapping("/getAllUsers")
    public List<Fullfiller> getAllUsers() throws ExecutionException, InterruptedException {
        return firebaseService.getUsers();
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<Object> createNewUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) throws IOException, ExecutionException, InterruptedException {
        firebaseService.saveNewUser(new Fullfiller(email, username, password));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
