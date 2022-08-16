package com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.springbootmicroserviceapps.springbootmicroservice2purchase.entities.Purchase;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
	//findBy + fieldName
	
	
	List<Purchase> findAllByUserId(long user_id);
}
