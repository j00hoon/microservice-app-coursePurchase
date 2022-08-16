package com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.entities.Purchase;
import com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.service.PurchaseService;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseApiController 
{
	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping
	public List<Purchase> getAll()
	{
		return purchaseService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Purchase savePurchase(@RequestBody Purchase purchase)
	{
		purchase.setPurchaseTime(LocalDateTime.now());
		
		return purchaseService.save(purchase);
	}
	
	@GetMapping("/{purchaseId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Purchase> findAllPurchaseOfUser(@PathVariable long purchaseId)
	{
		return purchaseService.findAllPurchaseOfUser(purchaseId);
	}
	
	
	
	
	
}
