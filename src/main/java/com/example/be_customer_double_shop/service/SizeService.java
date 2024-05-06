package com.example.be_customer_double_shop.service;


import com.example.be_customer_double_shop.dto.request.SizeRequest;
import com.example.be_customer_double_shop.entity.Size;

import java.util.List;

public interface SizeService {


    Object getAllSize();

    List<Size> getAllByCondition(SizeRequest sizeRequest);

}
