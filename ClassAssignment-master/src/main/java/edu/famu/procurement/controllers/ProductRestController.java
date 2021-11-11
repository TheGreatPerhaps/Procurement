package edu.famu.procurement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.famu.procurement.models.Products;
import edu.famu.procurement.models.RestProduct;
import edu.famu.procurement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    public ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {this.productService = productService;}

    @GetMapping("/product")
    public List<Products> getAllProducts() throws ExecutionException, InterruptedException, JsonProcessingException {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/product")
    public String createProduct(@RequestBody String json) throws ExecutionException, InterruptedException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        RestProduct tempProduct = new RestProduct(rootNode.get("productId"),rootNode.get("sku"),rootNode.get("name"),rootNode.get("description"),rootNode.get("unit"),rootNode.get("unitPrice"),rootNode.get("productVendor"));

        return  productService.createProduct(tempProduct);
    }




}
