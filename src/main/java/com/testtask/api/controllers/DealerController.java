package com.testtask.api.controllers;

import com.testtask.api.models.DealerModel;
import com.testtask.services.interfaces.IDealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealer")
public class DealerController{

    private IDealerService dealerService;

    public DealerController(IDealerService dealerService)
    {
        this.dealerService = dealerService;
    }

    @PostMapping(path ="/")
    public ResponseEntity create(@RequestBody DealerModel requestModel)
    {
        try {
            DealerModel dealerModel = dealerService.create(requestModel);
            return new ResponseEntity<>(dealerModel, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody DealerModel requestModel)
    {
        try {
            DealerModel dealerModel = dealerService.update(requestModel);
            return new ResponseEntity<>(dealerModel, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity get(@PathVariable Long id)
    {
        try {
            DealerModel dealerModel = dealerService.get(id);
            return dealerModel == null? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<>(dealerModel, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {

        try {
            boolean isDeleted = dealerService.delete(id);
            return new ResponseEntity<>(null, isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
