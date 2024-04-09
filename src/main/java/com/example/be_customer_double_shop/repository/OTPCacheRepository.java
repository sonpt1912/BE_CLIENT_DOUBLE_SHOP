package com.example.be_customer_double_shop.repository;

import com.example.be_customer_double_shop.entity.redisCache.OTPCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPCacheRepository extends CrudRepository<OTPCache, String> {
}
