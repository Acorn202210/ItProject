package com.acorn.project.springswagger.controller;



import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acorn.project.lecture.dto.LectureDto;
import com.acorn.project.lecture.service.LectureService;
import com.acorn.project.letcure.dao.LectureDao;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Api("LectureController")
@RequestMapping("/api/products")
public class HelloController {
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		List<ProductResponse> products = List.of(
				new ProductResponse(1L, "고구마", 1500), 
				new ProductResponse(2L, "감자", 1000), 
				new ProductResponse(3L, "양배추", 2000));
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId){
		ProductResponse dummyProduct = new ProductResponse(productId, "고구마", 1500);
		return ResponseEntity.ok(dummyProduct);
	}
	
	@PostMapping
	public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/"+4)
				.build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deletePRoduct(@PathVariable Long productId){
		return ResponseEntity.noContent().build();
	}
	
//	@GetMapping
//	public ResponseEntity<Object> authHeaderChecker(HttpServletRequest request){
//		String authorizationHeaderValue = request.getHeader("Authorization");
//		Map<String, String> response = new HashMap(){{
//			put("Authorization", authorizationHeaderValue);
//		}};
//		return ResponseEntity.ok(response);
//	}
	
}