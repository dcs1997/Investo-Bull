package com.masai.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private	Integer Sid;
	

	private LocalDateTime lastTradeTime;
	
    private	Integer quotationLot;
	
	private Long tradedQty;
	
	private Integer openInterest;
	
    private Double open;
	
	private Double high;
	
	private Double low;
	
	private Double close;

	public Stock() {
		super();
	}

	

	



	public Stock( LocalDateTime lastTradeTime, Integer quotationLot, Long tradedQty, Integer openInterest,
			Double open, Double high, Double low, Double close) {
		super();
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}







	public String getLastTradeTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return lastTradeTime.format(formatter);
		
	}







	public void setLastTradeTime(String lastTradeTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        this.lastTradeTime = LocalDateTime.parse(lastTradeTime, formatter);
	}







	public Integer getSid() {
		return Sid;
	}

	public void setSid(Integer sid) {
		Sid = sid;
	}

	public Integer getQuotationLot() {
		return quotationLot;
	}

	public void setQuotationLot(Integer quotationLot) {
		this.quotationLot = quotationLot;
	}

	

	public Long getTradedQty() {
		return tradedQty;
	}



	public void setTradedQty(Long tradedQty) {
		this.tradedQty = tradedQty;
	}



	public Integer getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}







	@Override
	public String toString() {
		return "Stock [Sid=" + Sid + ", lastTradeTime=" + lastTradeTime + ", quotationLot=" + quotationLot
				+ ", tradedQty=" + tradedQty + ", openInterest=" + openInterest + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", close=" + close + "]";
	}



	

	
	
	
	

}
