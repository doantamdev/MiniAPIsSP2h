package com.SpringBootTutorial.FirstProject.controllers;

import com.SpringBootTutorial.FirstProject.models.Product;
import com.SpringBootTutorial.FirstProject.models.ResponseObject;
import com.SpringBootTutorial.FirstProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Products")
public class ProductController {
    //http://localhost:8081/api/v1/Products
    @Autowired
    private ProductRepository repository;

    //getAllProduct
    @GetMapping("")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }


    //getByID
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findByID(@PathVariable Long id)
    {
        Optional<Product> foundProduct = repository.findById(id);
        return  foundProduct.isPresent() ?
        ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok","Query product successfully",foundProduct)
            ) :
           ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new ResponseObject("failed","Cannot find Product","")
            );
    }


    //insert Product
    //Raw, json
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct)
    {
        //validate
    List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
       if(foundProducts.size() > 0)
        {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Already has ProductName",""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert product successfully",repository.save(newProduct))
        );
    }


    //update , upsert = update if found, otherwise insert
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id)
    {
        Product updateProduct =repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear((newProduct.getYear()));
                    product.setPrice((newProduct.getPrice()));
                    return  repository.save(product);
                }).orElseGet(() ->{
                    newProduct.setId(id);
                    return  repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update product successfully",updateProduct)
        );
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id)
    {
        boolean exitsProduct = repository.existsById(id);
        if(exitsProduct){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully",""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can't found product",""));
    }
}
