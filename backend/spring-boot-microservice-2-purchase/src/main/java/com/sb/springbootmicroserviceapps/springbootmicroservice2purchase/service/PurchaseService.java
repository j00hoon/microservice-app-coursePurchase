package com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.entities.Purchase;
import com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.repository.PurchaseRepository;

@Service
public class PurchaseService 
{
	@Autowired
	private PurchaseRepository purchaseRepo;
	
	public List<Purchase> findAll()
	{
		return purchaseRepo.findAll();
	}
	
	
	public Purchase save(Purchase purchase)
	{
		purchaseRepo.save(purchase);
		
		return purchase;
	}
	
	public List<Purchase> findAllPurchaseOfUser(long id)
	{
		return purchaseRepo.findAllByUserId(id);
	}
	
	
	
	
	
}
