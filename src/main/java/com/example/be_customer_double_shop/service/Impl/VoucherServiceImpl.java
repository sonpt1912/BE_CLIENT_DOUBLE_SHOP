package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.VoucherRequest;
import com.example.be_customer_double_shop.entity.CustomerVoucher;
import com.example.be_customer_double_shop.entity.Voucher;
import com.example.be_customer_double_shop.repository.CustomerVoucherRepository;
import com.example.be_customer_double_shop.repository.VoucherRepository;
import com.example.be_customer_double_shop.service.VoucherService;
import com.example.be_customer_double_shop.util.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@EnableScheduling
@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository repository;

    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Voucher> getAllVoucherByUserLogin(String username) {
        return repository.findAllByUsernameLogin(username);
    }

    @Override
    public Voucher getByCode(String code) {
        return repository.getVoucherByCode(code);
    }

    @Override
    public Voucher getOneById(long id) {
        return repository.getVoucherById(id);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher, String username) {
        voucher.setUpdatedBy(username);
        voucher.setUpdatedBy(DateUtil.dateToString4(new Date()));
        return repository.save(voucher);
    }

    @Override
    public Voucher getByCodeAndCustomerId(VoucherRequest request) {
        Voucher voucher = repository.getVoucherByCode(request.getCode());

        if (voucher == null) {
            throw new EntityNotFoundException("Voucher with code " + request.getCode() + " not found.");
        }

        if (voucher.getQuantity() == 0) {
            throw new IllegalArgumentException("Đã hết số lượng cho phiếu giảm giá có mã: " + request.getCode());
        }

        if (voucher.getStatus() == 1) {
            throw new IllegalArgumentException("Phiếu giảm giá có mã " + request.getCode() + " đã hết hạn.");
        }

        if (voucher.getStatus() == 2) {
            throw new IllegalArgumentException("Phiếu giảm giá có mã " + request.getCode() + " đang tạm ngưng hoạt động.");
        }

        List<CustomerVoucher> customerVouchers = customerVoucherRepository.findByVoucherId(voucher.getId());

        if (request.getIdCustomer() == null&&request.getCode().startsWith("PGGKH")) {
            throw new IllegalArgumentException("CustomerId cannot be null.");
        }

        if (customerVouchers != null) {
            boolean isCustomerVoucherFound = false;
            for (CustomerVoucher customerVoucher : customerVouchers) {
                if (customerVoucher.getCustomer().getId().equals(request.getIdCustomer())) {
                    isCustomerVoucherFound = true;
                    if (customerVoucher.getUsageDate() != null) {
                        throw new IllegalArgumentException("Voucher với mã " + request.getCode() + " đã được sử dụng.");
                    }
                    break;
                }
            }
            if (!isCustomerVoucherFound&&request.getCode().startsWith("PGGKH")) {
                throw new EntityNotFoundException("No customer associated with CustomerId " + request.getIdCustomer());
            }
        }

        return voucher;
    }

}

