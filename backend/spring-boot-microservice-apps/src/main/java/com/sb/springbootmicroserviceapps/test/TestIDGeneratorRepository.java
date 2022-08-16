package com.sb.springbootmicroserviceapps.test;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestIDGeneratorRepository extends MongoRepository<TestIDGenerator, Long> 
{

}
