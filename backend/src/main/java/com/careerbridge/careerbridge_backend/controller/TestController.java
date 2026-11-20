
package com.careerbridge.careerbridge_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to CareerBridge - Internship and Placement Tracking Portal!";
    }

    @GetMapping("/api/test")
    public String test() {
        return "CareerBridge REST API is working successfully!";
    }
}
