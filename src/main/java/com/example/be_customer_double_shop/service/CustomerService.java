package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    ListResponse<Customer> getAll(CustomerRequest request);

    Customer getOneId(Long id);
    Customer delete(Long id );
    Page getAllByPage(int page, int pageSize);
    List<Customer> findByPhone(String phone);
    Customer save(Customer customer);
    Customer update(Customer color, Long id);

    Customer findUserbyUsername(String username);

    void signUp(Customer customer);

    void changePassword(String username, String oldPassword, String newPassword);

    Customer findUserbyEmail(String username);

    Object getAllCustomerByCondition(CustomerRequest customerRequest);

    Object createCustomer(Customer customer, String creator);

    Object updateCustomer(CustomerRequest customerRequest, String creator);
}
