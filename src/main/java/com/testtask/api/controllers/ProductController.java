package com.testtask.api.controllers;

import com.testtask.api.models.DealerModel;
import com.testtask.api.models.ProductModel;
import com.testtask.services.interfaces.IDealerService;
import com.testtask.services.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private IProductService productService;

    public ProductController(IProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody ProductModel requestModel)
    {
        try {
            ProductModel productModel = productService.create(requestModel);
            return new ResponseEntity<>(productModel, HttpStatus.OK);
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
    public ResponseEntity update(@RequestBody ProductModel requestModel)
    {
        try {
            ProductModel productModel = productService.update(requestModel);
            return new ResponseEntity<>(productModel, HttpStatus.OK);
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

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id)
    {
        try {
            ProductModel productModel = productService.get(id);
            return productModel == null? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<>(productModel, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {

        try {
            boolean isDeleted = productService.delete(id);
            return new ResponseEntity<>(null, isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
