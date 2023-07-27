package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.Entity.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer>, PagingAndSortingRepository<Stock, Integer>{

}
