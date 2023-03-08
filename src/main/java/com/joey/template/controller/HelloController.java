package com.joey.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joey
 */
@RestController
@Tag(name = "Index", description = "TEST首页")
public class HelloController {
    @GetMapping("/")
    @Operation(summary = "Hello", description = "无需验证")
    public ResponseEntity hello() {
        return ResponseEntity.ok("Hello");
    }
}