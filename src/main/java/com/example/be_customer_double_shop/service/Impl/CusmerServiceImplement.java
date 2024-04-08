package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.CustomerRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.repository.CustomerRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CusmerServiceImplement implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository userRepository;


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ListResponse<Customer> getAll(CustomerRequest request) {
        return null;
    }

    @Override
    public Customer getOneId(Long id) {
        return null;
    }

    @Override
    public Customer delete(Long id) {
        return null;
    }

    @Override
    public Page getAllByPage(int page, int pageSize) {
        return null;
    }

    @Override
    public List<Customer> findByPhone(String phone) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return userRepository.save(customer);
    }

    @Override
    public Customer update(Customer color, Long id) {
        return null;
    }

    @Override
    public Customer findUserbyUsername(String username) {
        return userRepository.findCustomerByUsername(username);
    }

    @Override
    public void signUp(Customer customer) {
        if (userRepository.findCustomerByUsername(customer.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setCreatedBy("");
        customer.setCreatedTime("");
        userRepository.save(customer);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        Customer user = userRepository.findCustomerByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public Customer findUserbyEmail(String email) {
        return userRepository.findCustomerByEmail(email);
    }

    @Override
    public Object getAllCustomerByCondition(CustomerRequest customerRequest) {
        return null;
    }

    @Override
    public Object createCustomer(Customer customer, String creator) {
        return null;
    }

    @Override
    public Object updateCustomer(CustomerRequest customerRequest, String creator) {
        return null;
    }
}
