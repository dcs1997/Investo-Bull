package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Entity.Stock;
import com.masai.Exception.StockException;
import com.masai.Service.StockService;


import jakarta.validation.Valid;

@RestController
public class MyController {

	
	@Autowired
	private StockService serv;
	
	
	@PostMapping("/Stocks")
	public ResponseEntity<Stock> createStocks(@RequestBody @Valid Stock st ){
		
		Stock save= serv.addStock(st);
		
		return new ResponseEntity<>(save, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/AllStocks")
	public ResponseEntity <List<Stock>> ListOfStock() throws StockException{
		
		List<Stock> st = serv.getAllStocks();
		return new ResponseEntity<>(st, HttpStatus.OK);
		
	}
	
	
	
	//Merge Stocks data
	
	@GetMapping("/mergeStocks/{num}")
	public ResponseEntity <List<Stock>> mergingStocks (@PathVariable Integer num) throws StockException{
		
		List<Stock> st = serv.mergeStocks(num);
		return new ResponseEntity<>(st, HttpStatus.OK);
		
	}
	
	
	
	
	
//	@GetMapping("/mergeStocks/{num}")
//	public ResponseEntity <List<Double>> mergingStocks (@PathVariable Integer num) throws StockException{
//		
//		List<Double> st = serv.mergeStocks(num);
//		return new ResponseEntity<>(st, HttpStatus.OK);
//		
//	}
	
	
	
	//ORB 
	
	
	@GetMapping("/ORB/{num}")
	public ResponseEntity <String> OpeningRange(@PathVariable Integer num) throws StockException{
		
		String st = serv.timeDuration(num);
		return new ResponseEntity<>(st, HttpStatus.OK);
		
	}
}
