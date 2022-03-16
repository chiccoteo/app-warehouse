package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping
    public Result getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Result getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Result editProductById(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.editProductById(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteProductById(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }
}
