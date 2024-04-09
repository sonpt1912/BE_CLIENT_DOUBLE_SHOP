package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.SizeRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Size;
import com.example.be_customer_double_shop.service.SizeService;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    @Override
    public ListResponse<Size> getAllByConditon(SizeRequest request) {
        return null;
    }

    @Override
    public String save(Size size) {
        return null;
    }

    @Override
    public Object update(Size size) {
        return null;
    }
}
