package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.controller;

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

import com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.request.PurchaseService;

@RestController
@RequestMapping("/gateway/purchase")
public class PurchaseController 
{
	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Object> getAll()
	{
		return purchaseService.getAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Object savePurchase(@RequestBody Object purchase)
	{
		return purchaseService.savePurchase(purchase);
	}
	
	@PostMapping("/{purchaseId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object> findAllPurchaseOfUser(@PathVariable long purchaseId)
	{
		return purchaseService.findAllPurchaseOfUser(purchaseId);
	}
	
	
}
