package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "DEALER")
public interface DealerClient {

    @GetMapping("/dealers/{id}")
    Object getDealerById(@PathVariable("id") Long id);
}