package com.EMPMANAGE.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMPMANAGE.DTO.RegisterRequestDTO;

@RestController
@RequestMapping("/api/auth") 
public class AuthController {
	
	@org.springframework.web.bind.annotation.GetMapping("/hello")
	public org.springframework.http.ResponseEntity<String> sayHello() {
	    return org.springframework.http.ResponseEntity.ok("Hello! Employee Management Backend is running perfectly.");
	}

    @PostMapping("/register") 
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequest) {
        
       
        System.out.println("Data received from Postman: " + registerRequest.getUsername());
        
        return ResponseEntity.ok("User registered successfully!");
    }
}
