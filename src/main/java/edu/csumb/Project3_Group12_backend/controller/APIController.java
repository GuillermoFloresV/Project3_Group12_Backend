package edu.csumb.Project3_Group12_backend.controller;
import edu.csumb.Project3_Group12_backend.Fullfiller;
import edu.csumb.Project3_Group12_backend.FullfillerService;
import edu.csumb.Project3_Group12_backend.firebase.FirebaseService;
import org.apache.catalina.User;
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



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getAllUsers")
    public List<Fullfiller> getAllUsers() throws ExecutionException, InterruptedException {
        return firebaseService.getUsers();
    }


    @PostMapping("/createNewUser")
    public ResponseEntity<Object> createNewUser(@RequestBody Fullfiller fullfiller)
            throws IOException, ExecutionException, InterruptedException {
        firebaseService.saveNewUser(new Fullfiller(fullfiller));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @PostMapping("/createNewUser")
//    public ResponseEntity<Object> createNewUser(@RequestParam String email, @RequestParam String username, @RequestParam String password)
//                                                throws IOException, ExecutionException, InterruptedException {
//        firebaseService.saveNewUser(new Fullfiller(email, username, password));
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }

    /**
     this is the admin's get user route for now. The returned object contains the password, name, and other sensitve data
     */
    @GetMapping("/adminGetUser/{email}")
    public ResponseEntity<Object> adminGetUserDocument(@PathVariable String email) throws ExecutionException, InterruptedException {
        Optional<Fullfiller> user = Optional.ofNullable(firebaseService.adminGetUserDocument(email));
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     this route is for users: I think the map will make it easier to control who is seeing what.
     ie: if you are in a current project with a user you should be able to see their contact info or
     perhaps this could lead to having regular clients for printing, ect.
     */
    @GetMapping("/GetUser/{email}")
    public ResponseEntity<Object> getUserDocumentsAsMap(@PathVariable String email) throws Exception {
        Optional<Map<String, Object>> userMap = Optional.ofNullable(firebaseService.getUserDocumentAsMap(email));
        if (userMap.isPresent()) {
            return new ResponseEntity<>(userMap.get(), HttpStatus.OK);
        } else {
            throw new NoSuchElementException();
        }
    }




}

//      -- Testing --
//    @GetMapping("/")
//    public String test(){
//        return "It works!";
//    }

//      -- Old mapping --

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