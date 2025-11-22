package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.userRequest;
import com.example.BillingSoftware.io.userResponse;
import com.example.BillingSoftware.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class userController {

    private final userService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public userResponse registerUser(@RequestBody userRequest request){
        try{
            return userService.createUser(request);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Unable to create user" + e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<userResponse> readusers(){
        return userService.readUsers();
    }

    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable  String id){
        try{
            userService.deleteUser(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User not found");
        }
    }

}
