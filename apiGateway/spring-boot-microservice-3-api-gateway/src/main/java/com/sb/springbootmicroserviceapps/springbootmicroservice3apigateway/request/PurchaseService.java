package com.sb.springbootmicroserviceapps.springbootmicroservice3apigateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "purchase-service", path="/api/purchase", url="${purchase.service.url}",
			configuration = FeignConfiguration.class)
public interface PurchaseService 
{
	@GetMapping
	List<Object> getAll();
	
	@PostMapping
	Object savePurchase(@RequestBody Object purchase);
	
	@PostMapping("/{purchaseId}")
	List<Object> findAllPurchaseOfUser(@PathVariable long purchaseId);
}
