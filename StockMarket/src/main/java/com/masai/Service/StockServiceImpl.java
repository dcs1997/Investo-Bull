package com.masai.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masai.Entity.Stock;
import com.masai.Exception.StockException;
import com.masai.Repository.StockRepo;



import java.util.ArrayList;

@Service
public class StockServiceImpl implements StockService {

	
	
	@Autowired
	private StockRepo sRepo;
	
	@Override
	public Stock addStock(Stock st) {
		
		
		return sRepo.save(st);
	}
	
	

	@Override
	public List<Stock> getAllStocks() throws StockException {
		
		List<Stock> stock = new ArrayList<>();
		
		try {
			stock = sRepo.findAll();
		
		}catch(Exception e) {
			
			 throw new StockException("No data is found in the system.");
		}
		
		
		return stock;
	}
	
	
	

	@Override
	public String timeDuration(Integer num) throws StockException {
		
		int size = num/5;
		
		double max = Double.NEGATIVE_INFINITY; 
        
		double min = Double.POSITIVE_INFINITY; 
		
        List<Stock> list =getAllStocks();
		
		List<Stock> formattedList= new ArrayList<>();
		
		
		
		//Add data to Formatted List
		for(int i=0; i < size && i<list.size(); i++) formattedList.add(list.get(i));
			
			
		
		for(int i=0; i<formattedList.size(); i++) {
			
			//max value function
			max= Math.max(max, formattedList.get(i).getHigh());
			
			
			// min value function
			min = Math.min(min, formattedList.get(i).getLow());
		}
		
		
		
		for(int i=size; i<list.size(); i++) {
			
			if(max < list.get(i).getHigh() || min > list.get(i).getLow()) 
				
				return "ORB candle generated at "+ list.get(i).getLastTradeTime(); 
			
		}
		
		return "No such ORB candle Exist";
	

		
	}

	

	@Override
	public List<Stock> mergeStocks(Integer num) throws StockException {
	   
		if (num % 5 != 0)
	        throw new StockException("Stock must be in the multiple of 5 minutes");

		
		        //Getting the all stocks object in form of list
				List<Stock> list = getAllStocks();

				
				//Generating stock with combined data with respect to  given time
			    List<Stock> mergedStock = new ArrayList<>();

			    int pageNumber = 0;
			    
			    int pageSize = (int) Math.ceil(num / 5.0); 
			    
			    

			    while (pageNumber * pageSize < list.size()) {
			       
			    	
			    	// To get the value of minimum pageSize even when data is less than pageSize
			        int n = Math.min((pageNumber + 1) * pageSize, list.size());

			        double max = Double.NEGATIVE_INFINITY; 
			        
			        double min = Double.POSITIVE_INFINITY; 
			        
			        long sum = 0; 

			        Stock stock = new Stock();

			        
			        
			        for (int i = pageNumber * pageSize; i < n; i++) {
			           
			        	// Adding the TradingQty w.r.t K number of objects
			            sum += list.get(i).getTradedQty();

			            // max value function
			            max = Math.max(max, list.get(i).getHigh());

			            // min value function
			            min = Math.min(min, list.get(i).getLow());
			        }

			        // Appending data to the stock object
			        stock.setTradedQty(sum);
			        
			        stock.setHigh(max);
			        
			        stock.setLow(min);
			        
			        stock.setQuotationLot(1);
			        
			        stock.setOpenInterest(0);
			        
			        stock.setOpen(list.get(pageNumber * pageSize).getOpen());
			        
			        stock.setClose(list.get(n - 1).getClose());
			        
			        stock.setLastTradeTime(list.get(pageNumber * pageSize).getLastTradeTime());
			        
			        stock.setSid(list.get(pageNumber).getSid());

			        mergedStock.add(stock);

			        pageNumber++;
			    }

			    return mergedStock;
	}

	
	

}
