package com.example.BillingSoftware.controller;


import com.example.BillingSoftware.io.categoryRequest;
import com.example.BillingSoftware.io.categoryResponse;
import com.example.BillingSoftware.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class categoryController {

    private final categoryService categoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public categoryResponse addCategory(@RequestBody categoryRequest request){
        return categoryService.add(request);
    }

    @GetMapping
    public List<categoryResponse> fetchCategories(){
        return categoryService.read();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void remove (@PathVariable String categoryId){
        try{
            categoryService.delete(categoryId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , e.getMessage() );
        }
    }
}
