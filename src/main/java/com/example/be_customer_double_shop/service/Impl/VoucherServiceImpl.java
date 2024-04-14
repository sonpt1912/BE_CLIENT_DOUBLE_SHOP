package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.Voucher;
import com.example.be_customer_double_shop.repository.VoucherRepository;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.VoucherService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@EnableScheduling
@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public Voucher getOneId(Long id) {
        return null;
    }

    @Override
    public Object update(Voucher voucherRequest, String username) throws ParseException {
        return null;
    }

    @Override
    public Voucher getByCode(String code) {
        return null;
    }
}

