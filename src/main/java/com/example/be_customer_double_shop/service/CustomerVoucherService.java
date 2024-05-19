package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.VoucherRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.CustomerVoucher;

public interface CustomerVoucherService {

    CustomerVoucher updateCustomerVoucher(CustomerVoucher customerVoucher);

    CustomerVoucher getByCustomerAndVoucher(long idCustomer, long idVoucher);

    ListResponse<CustomerVoucher> getAll(VoucherRequest request, Long idCustomer);


}
