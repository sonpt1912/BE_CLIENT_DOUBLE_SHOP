package com.example.be_customer_double_shop.controller.ghn;

import com.example.be_customer_double_shop.dto.request.GHNRequest;
import com.example.be_customer_double_shop.service.GHNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/ghn")
public class GhnController {

    @Autowired
    private GHNService ghnService;

    @PostMapping("/get-shipping-fee")
    public ResponseEntity getFeeShipping(@RequestBody GHNRequest request) {
        return new ResponseEntity<>(ghnService.getFeeShipping(request), HttpStatus.OK);
    }

    @GetMapping("/get-province")
    public ResponseEntity getProvince() {
        return new ResponseEntity(ghnService.getProvince(), HttpStatus.OK);
    }

    @GetMapping("/get-district")
    public ResponseEntity getDistrict(@RequestParam String district) {
        return new ResponseEntity(ghnService.getDistrict(district), HttpStatus.OK);
    }

    @GetMapping("/get-ward")
    public ResponseEntity getWard(@RequestParam int ward) {
        return new ResponseEntity(ghnService.getWard(ward), HttpStatus.OK);
    }


}
