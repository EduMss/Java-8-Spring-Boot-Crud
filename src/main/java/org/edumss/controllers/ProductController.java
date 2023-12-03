package org.edumss.controllers;

import lombok.var;
import org.edumss.domain.product.Product;
import org.edumss.domain.product.ProductRepository;
import org.edumss.domain.product.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping()
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity getIdProducts(@PathVariable String id){
        var Product = repository.findById(id);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getNameProducts(@PathVariable String name){
        var Product = repository.findByNameStartsWithIgnoreCase(name);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/priceInCents/{priceInCents}")
    public ResponseEntity getPriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCents(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/apartirPriceInCents/{priceInCents}")
    public ResponseEntity getApartirPriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCentsGreaterThanEqual(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/atePriceInCents/{priceInCents}")
    public ResponseEntity getAtePriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCentsLessThanEqual(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = repository.findById(data.getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.getName());
            product.setPriceInCents(data.getPriceInCents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok("Excluido!");
    }

}
