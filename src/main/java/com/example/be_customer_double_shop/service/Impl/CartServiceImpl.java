package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.CartRequest;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.repository.CartRepository;
import com.example.be_customer_double_shop.service.CartService;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.DetailProductService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DetailProductService detailProductService;

    @Autowired
    private CustomerService customerService;


    @Override
    public Object createCart(CartRequest request, String username) {
        DetailProduct detailProduct = detailProductService.getOneById(request.getIdDetailProduct());
        Customer customer = customerService.findUserbyUsername(username);
        Cart cart = Cart.builder()
                .quantity(request.getQuantity())
                .detailProduct(detailProduct)
                .customer(customer)
                .build();
        return cartRepository.save(cart);
    }

    @Override
    public Object updateCart(CartRequest request) {
        Cart cart = cartRepository.getCartById(request.getId());
        cart.setQuantity(request.getQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public Object delete(long idCart) {
        try {
            cartRepository.deleteById(idCart);
        } catch (Exception e) {
            return new ValidationException(Constant.API001, "");
        }
        return Constant.SUCCESS;

    }
}
