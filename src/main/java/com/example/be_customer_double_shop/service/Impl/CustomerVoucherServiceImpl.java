package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.entity.CustomerVoucher;
import com.example.be_customer_double_shop.repository.CustomerVoucherRepository;
import com.example.be_customer_double_shop.service.CustomerVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerVoucherServiceImpl implements CustomerVoucherService {

    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    @Override
    public CustomerVoucher updateCustomerVoucher(CustomerVoucher customerVoucher) {
        return customerVoucherRepository.save(customerVoucher);
    }

    @Override
    public CustomerVoucher getByCustomerAndVoucher(long idCustomer, long idVoucher) {
        return customerVoucherRepository.findByCustomerAndVoucher(idCustomer, idVoucher);
    }

}
