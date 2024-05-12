package com.example.be_customer_double_shop.dto.request;

import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.entity.DetailProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {

    private Long id;

    private Long idCustomer;

    private Long idVoucher;

    private Long idEmployee;

    private String code;

    private Long idDetailBill;

    private BigDecimal totalAmout;

    private String productName;

    private BigDecimal discoutAmout;

    private String description;

    private String note;

    private Integer status;

    private String phone;

    private Integer type;

    private BigDecimal moneyShip;

    private String address;

    private Long payment;

    private List<Cart> listCart;

    private int page;

    private int pageSize;

//    private String


}
