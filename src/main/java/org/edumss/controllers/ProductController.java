package org.edumss.controllers;

import lombok.var;
import org.edumss.domain.product.Product;
import org.edumss.domain.product.ProductRepository;
import org.edumss.domain.product.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping()
    public ResponseEntity getAllProducts(){
        //var allProducts = repository.findAll();
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity getIdProducts(@PathVariable String id){
        var Product = repository.findById(id);
        return ResponseEntity.ok(Product);
    }

    //não é uma boa pratica passar mais parametros "/name/" na url, e melhor criar uma nova classe, mas como e so para aprender como faz, eu deixei
    @GetMapping("/name/{name}")
    public ResponseEntity getNameProducts(@PathVariable String name){
        var Product = repository.findByNameStartsWithIgnoreCaseAndActiveTrue(name);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/priceInCents/{priceInCents}")
    public ResponseEntity getPriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCents(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/apartirPriceInCents/{priceInCents}")
    public ResponseEntity getApartirPriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCentsGreaterThanEqualAndActiveTrue(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @GetMapping("/atePriceInCents/{priceInCents}")
    public ResponseEntity getAtePriceInCentsProducts(@PathVariable Integer priceInCents){
        var Product = repository.findByPriceInCentsLessThanEqualAndActiveTrue(priceInCents);
        return ResponseEntity.ok(Product);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional // para editar um item dentro do banco de dados
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = repository.findById(data.getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.getName());
            product.setPriceInCents(data.getPriceInCents());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    //não é uma boa pratica excluir uma informação direta no banco de dados, por isso atualizei a tabela adicionando o actived, para só desativar a tabela
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok("Excluido!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity disableProduct(@PathVariable String id){
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

}
