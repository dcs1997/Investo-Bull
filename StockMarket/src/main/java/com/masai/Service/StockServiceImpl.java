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

	private static final int INT_MAX =  -2147483648;    
	private static final int INT_MIN = 2147483647;
	
	@Autowired
	private StockRepo sRepo;
	
	@Override
	public Stock addStock(Stock st) {
		// read the json file here
		//all data will be in form of array or list of candle class
		
		return sRepo.save(st);
	}
	
	

	@Override
	public List<Stock> getAllStocks() throws StockException {
		
		List<Stock> stock = new ArrayList<>();
		
		try {
			stock = sRepo.findAll();
		}
		catch(Exception e){
			
			 throw new StockException("No data is found in the system.");
			
		}
		
		return stock;
	}
	
	
	

	@Override
	public String timeDuration(Integer num) throws StockException {
		
		 
		/*
		  
		use Pagination to find out first n candles
		
		where n= num/5
		
		comparator to find highest and lowest price
		
		index number n to 74 (last part where to find ORB)
		 
		 
		   
		   
		   */
		
		List<Stock> list =getAllStocks();
		
		
		
		
		List<Stock> formattedList= new ArrayList<>();
		
		int size = num/5;
		
//		if(size>74)  throw new StockException("Please enter a valid time from 5 to 370");
		

		
		
		for(int i=0; i < size && i<list.size(); i++)    formattedList.add(list.get(i));
			
			
		
        double max= INT_MAX;
		
		double min = INT_MIN;
		
		for(int i=0; i<formattedList.size(); i++) {
			
			max= Math.max(max, formattedList.get(i).getHigh());
			
			// min value function
			
			min = Math.min(min, formattedList.get(i).getLow());
		}
		
		
		for(int i=size; i<list.size(); i++) {
			
			if(max < list.get(i).getHigh() || min > list.get(i).getLow()) {
				
				return "ORB candle generated at "+ list.get(i).getLastTradeTime(); 
			}
		}
		
		return "No such ORB candle Exist";
	
//		return formattedList;
		
	}

	

	@Override
	public List<Stock> mergeStocks(Integer num) throws StockException {
	
		//Getting the all stocks object in form of list
		List<Stock> list =getAllStocks();
		
		//Generating stock with combined data with respect to  given time
		List<Stock> mergedStock = new ArrayList<>();
		
		Stock stock = new Stock();
		
		//use of stream api and use reduce method
		//use pagination and sorting 
		
		int pageNumber=1;
		
		int pageSize=(int) Math.ceil(num/5);
		
		
		
		double max= INT_MAX;
		
		double min = INT_MIN;
		
		Long sum= (long) 0;
		
		Pageable p = PageRequest.of(pageNumber,pageSize);
		
		Page<Stock> pgpost = sRepo.findAll(p);
		
		List<Stock> pageList =pgpost.getContent();
		
		int n= pageList.size();
		
		
		for(int x=0; x<5; x++) {
			
		
		  int i=0;
		 
		for( i=pageNumber; i<n; i++) {
			
			
			//Adding the Trading quatity w.r.t K number of objects 
			
			sum+=list.get(i).getTradedQty();
			
			// max value function
			
			max= Math.max(max, list.get(i).getHigh());
			
			// min value function
			
			min = Math.min(min, list.get(i).getLow());
			
			stock.setTradedQty(sum);
			stock.setHigh(max);
			stock.setLow(min);
			
			
			
			stock.setOpen(list.get(pageNumber).getOpen());
			stock.setClose(list.get(pageList.size()-1).getClose());
			stock.setLastTradeTime(list.get(pageNumber).getLastTradeTime());
			
			stock.setOpenInterest(list.get(i).getOpenInterest());
			stock.setQuotationLot(list.get(i).getQuotationLot());
			stock.setSid(list.get(pageNumber).getSid());
			
			
		}
		
		  max=0;
		  min=0;
		 i+=pageSize;
		 n+=pageSize;
		
		
		

		mergedStock.add(stock);
		
		}
		
		return pageList;
		
		
//		}
		
		
		/*
		  merging kiya haiii from 1 to 4 vasie hi age ka list bhi osrt karna haii
		  
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		
//		List<Double> li = pageList.stream().map(n -> n.getHigh()).collect(Collectors.toList());
		
		
//		for(int i=0; i<list.size(); i++) {
//			
//			if(count==1) stock.setOpen(list.get(i).getOpen());
//			
//			if(count==pageSize) {
//				stock.setClose(list.get(i).getClose());
//				stock.setLastTradeTime(list.get(i).getLastTradeTime());
//				mergedStock.add(stock);
//				count=1;
//				sum=(long) 0;
//				sum=list.get(i).getTradedQty();
//				max=list.get(i).getHigh();
//				min=list.get(i).getLow();
//			}
//			else {
//				
//				//Adding the Trading quatity w.r.t K number of objects
//				
//				sum+=list.get(i).getTradedQty();
//				
//				// max value function
//				
//				max= Math.max(max, list.get(i).getHigh());
//				
//				// min value function
//				
//				min = Math.min(min, list.get(i).getLow());
//				
//				count++;
//				
//			
//				stock.setTradedQty(sum);
//				stock.setHigh(max);
//				stock.setLow(min);
//				
//				
//				
//				stock.setOpenInterest(list.get(i).getOpenInterest());
//				stock.setQuotationLot(list.get(i).getQuotationLot());
//				stock.setSid(list.get(i).getSid());
//				
//			}
//			
//		}
		
		
		
		
	}
	
	
	

}
