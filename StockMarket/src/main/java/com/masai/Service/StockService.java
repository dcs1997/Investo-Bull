package com.masai.Service;

import java.util.List;

import com.masai.Entity.Stock;
import com.masai.Exception.StockException;

public interface StockService {
	
	//
	
	public Stock addStock(Stock st);
	
	public List<Stock> getAllStocks() throws StockException;
	
	public String timeDuration(Integer num) throws StockException;
	
	public List<Stock> mergeStocks(Integer num) throws StockException;
	
	

}
