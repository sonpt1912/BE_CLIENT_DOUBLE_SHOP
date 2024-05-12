package com.example.be_customer_double_shop.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.be_customer_double_shop.dao.DetailProductDao;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DetailProductService detailProductService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ExecutorService executorService;

    @Override
    public Object createCart(CartRequest request, String username) {
        DetailProduct detailProduct = detailProductService.getOneById(request.getIdDetailProduct());
        Customer customer = customerService.findUserbyUsername(username);
        Cart cartedExist = cartRepository.cartExistByCustomerAndProduct(customer.getId(), detailProduct.getId());
        if (cartedExist == null) {
            Cart cart = Cart.builder()
                    .quantity(request.getQuantity())
                    .detailProduct(detailProduct)
                    .customer(customer)
                    .build();
            return cartRepository.save(cart);
        } else {
            cartedExist.setQuantity(cartedExist.getQuantity() + request.getQuantity());
            return cartRepository.save(cartedExist);
        }
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

    @Override
    public Object getAllProductFromCart(String username) throws InterruptedException, ExecutionException {
        List<DetailProductDao> daoList = cartRepository.getAllDetailProductFromCart(username);
        List<Callable<Map<String, Object>>> callableList = new ArrayList<>();

        for (DetailProductDao dt : daoList) {
            Callable callable = () -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", dt.getId());
                map.put("color", dt.getColor());
                map.put("product", dt.getProduct());
                map.put("size", dt.getSize());
                map.put("quantity", dt.getQuantity());
                map.put("price", dt.getPrice());
                map.put("discountAmount", dt.getDiscountAmount());
                map.put("listImages", cloudinary.search().expression("folder:double_shop/product/" + dt.getProduct().getCode() + "/*").maxResults(500).execute());
                return map;
            };
            callableList.add(callable);
        }
        List<Future<Map<String, Object>>> futureList = executorService.invokeAll(callableList);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Future future : futureList) {
            list.add((Map<String, Object>) future.get());
        }
        return list;
    }
}
