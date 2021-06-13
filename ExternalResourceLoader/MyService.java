package com.example.demo.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyService {
	
	@Autowired
	ResourceLoaderService resourceLoaderService; 
	
	@GetMapping("/hello")
	public String read() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		resourceLoaderService.show();
		return "success";
	}
	
}
