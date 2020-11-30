package com.example.dynamo.controller;

import com.example.dynamo.model.ProductInfo;
import com.example.dynamo.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductInfoController {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @GetMapping
    private List<ProductInfo> getAll() {
        System.out.println(productInfoRepository.findAll());
        return new ArrayList<ProductInfo>();
    }


    @PostMapping
    private ProductInfo create(@RequestBody ProductInfo productInfo) {
        return this.productInfoRepository.save(productInfo);
    }
}
