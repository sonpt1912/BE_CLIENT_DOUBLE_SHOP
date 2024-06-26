package com.example.be_customer_double_shop.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.FavoriteRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.Favorite;
import com.example.be_customer_double_shop.entity.Product;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.repository.FavoriteRepository;
import com.example.be_customer_double_shop.repository.ProductRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.FavoriteService;
import com.example.be_customer_double_shop.service.ProductService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Favorite addFavorite(FavoriteRequest request, String username) {
        Customer customer = customerService.findUserbyUsername(username);
        Product product = productService.getProductById(request.getIdProduct());
        Favorite favorite = Favorite.builder()
                .customer(customer)
                .product(product)
                .build();
        return favoriteRepository.save(favorite);
    }

    @Override
    public Object deleteFavortite(Favorite favorite) {
        try {
            favoriteRepository.deleteById(favorite.getId());
        } catch (Exception e) {
            return new ValidationException(Constant.API001, e.getMessage());
        }
        return Constant.SUCCESS;
    }

    @Override
    public List<Favorite> getByCustomerId(String username) throws Exception {
        try {
            Customer customer = customerRepository.findCustomerByUsername(username);

            List<Favorite> favorites = favoriteRepository.findByCustomerId(customer.getId());

            for (Favorite favorite : favorites) {
                Optional<Product> productOptional = productRepository.findById(favorite.getProduct().getId());
                productOptional.ifPresent(product -> {
                    favorite.setProduct(product);
                    try {
                        Map<String, Object> searchResult = cloudinary.search()
                                .expression("folder:double_shop/product/" + product.getCode() + "/*")
                                .maxResults(500)
                                .execute();

                        product.setListImages(searchResult);
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error fetching images", e);
                    }
                });
            }
            return favorites;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error occurred", e);
            throw e;
        }
    }
}
