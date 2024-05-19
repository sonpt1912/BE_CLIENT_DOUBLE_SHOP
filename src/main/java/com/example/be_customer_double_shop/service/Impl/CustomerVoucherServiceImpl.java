
package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.VoucherRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.CustomerVoucher;
import com.example.be_customer_double_shop.repository.CustomerVoucherRepository;
import com.example.be_customer_double_shop.service.CustomerVoucherService;
import com.example.be_customer_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerVoucherServiceImpl implements CustomerVoucherService {

    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CustomerVoucher updateCustomerVoucher(CustomerVoucher customerVoucher) {
        return customerVoucherRepository.save(customerVoucher);
    }

    @Override
    public CustomerVoucher getByCustomerAndVoucher(long idCustomer, long idVoucher) {
        return customerVoucherRepository.findByCustomerAndVoucher(idCustomer, idVoucher);
    }

    @Override
    public ListResponse<CustomerVoucher> getAll(VoucherRequest request, Long idCustomer) {

        ListResponse<CustomerVoucher> listResponse = new ListResponse<>();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("SELECT cv.* FROM customer_voucher cv ")
                .append("JOIN voucher v ON cv.id_voucher = v.id ")
                .append("JOIN customer c ON cv.id_customer = c.id ")
                .append("WHERE cv.id_customer = :idCustomer ");

        params.put("idCustomer", idCustomer);

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append("AND v.code LIKE CONCAT('%', :voucherCode ,'%') ");
            params.put("voucherCode", request.getCode());
        }

        sql.append("ORDER BY cv.id DESC ");

        if (request.getPage() != null && request.getPageSize() != null) {
            sql.append("LIMIT :page, :size ");
            params.put("page", request.getPage() == 0 ? 0 : (request.getPage() * request.getPageSize()));
            params.put("size", request.getPageSize());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerVoucher.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

        sql = new StringBuilder();
        params = new HashMap<>();

        sql.append("SELECT COUNT(cv.id) FROM customer_voucher cv ")
                .append("JOIN voucher v ON cv.id_voucher = v.id ")
                .append("JOIN customer c ON cv.id_customer = c.id ")
                .append("WHERE cv.id_customer = :idCustomer ");

        params.put("idCustomer", idCustomer);

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append("AND v.code LIKE CONCAT('%', :voucherCode ,'%') ");
            params.put("voucherCode", request.getCode());
        }

        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Number) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);
        return listResponse;
    }


}