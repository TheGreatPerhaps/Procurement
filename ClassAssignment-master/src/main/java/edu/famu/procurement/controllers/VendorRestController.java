package edu.famu.procurement.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.famu.procurement.models.Vendor;
import edu.famu.procurement.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class VendorRestController {

    public VendorService vendorService;

    @Autowired
    public VendorRestController(VendorService vendorService) {this.vendorService = vendorService;}

    @GetMapping("/vendor")
    public List<Vendor> getAllVendors() throws ExecutionException, InterruptedException {
        return vendorService.getAllVendors();
    }

    @PostMapping(path = "/vendor")
    public String createPost(@RequestBody String json) throws ExecutionException, InterruptedException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        Vendor tempVendor = new Vendor(rootNode.get("vendorId"),rootNode.get("company"),rootNode.get("street"),rootNode.get("city"),rootNode.get("state"),rootNode.get("postalCode"),
                rootNode.get("country"), rootNode.get("contact"),rootNode.get("phoneNumber"),rootNode.get("emailAddress"), rootNode.get("paymentTerms"));

        return vendorService.createVendor(tempVendor);
    }

}
