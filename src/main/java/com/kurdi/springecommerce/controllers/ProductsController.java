package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.repositories.ProductsRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductsController {
    @Autowired
    ProductsRepository productsRepository;
    @GetMapping("/")
    public String get(Model model)
    {
        model.addAttribute("products", productsRepository.findAll());
        return "products/products";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    /*

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productsRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity post(@RequestBody Product product)
    {
        product.setId(null);
        productsRepository.save(product);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody Product product, @PathVariable String id)
    {
        if (!productsRepository.existsById(id) || id != product.getId())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        productsRepository.save(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        productsRepository.delete(productsRepository.findById(id).get());
        return new ResponseEntity(productsRepository.findById(id), HttpStatus.OK);
    }*/


}
