package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Product;
import com.example.be_customer_double_shop.repository.ProductRepository;
import com.example.be_customer_double_shop.service.ProductService;
import com.example.be_customer_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Object getByCondition(ProductRequest request) {

        ListResponse<Product> listResponse = new ListResponse<>();

        StringBuilder str = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        str.append(" SELECT p.* FROM product p INNER JOIN detail_product dp ON p.id = dp.id_product ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN color c ON dp.id_color = c.id ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN category ct ON dp.id_category = ct.id ");
        str.append(" INNER JOIN collar cl ON dp.id_collar = cl.id ");
        str.append(" INNER JOIN brand b ON dp.id_brand = b.id ");
        str.append(" WHERE 1 = 1 ");

        // TODO: them orderby o day

        if (!StringUtil.stringIsNullOrEmty(request.getIdProduct())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            str.append(" AND b.id = :id ");
            params.put("id", request.getIdBrand());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            str.append(" AND ct.id = :id ");
            params.put("id", request.getIdCategory());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            str.append(" AND c.id = :id ");
            params.put("id", request.getIdColor());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getPage())) {
            str.append(" LIMIT :page, :pageSize");
            if (request.getPage() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (request.getPage() * request.getPageSize()));
            }
            params.put("pageSize", request.getPageSize());
        }

        Query query = entityManager.createNativeQuery(str.toString());
        params.forEach(query::setParameter);
        listResponse.setListData(query.getResultList());

        str.setLength(0);
        params.clear();

        str.append(" SELECT COUNT(*) FROM product p INNER JOIN detail_product dp ON p.id = dp.id_product ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN color c ON dp.id_color = c.id ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN category ct ON dp.id_category = ct.id ");
        str.append(" INNER JOIN collar cl ON dp.id_collar = cl.id ");
        str.append(" INNER JOIN brand b ON dp.id_brand = b.id ");
        str.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdProduct())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            str.append(" AND b.id = :id ");
            params.put("id", request.getIdBrand());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            str.append(" AND ct.id = :id ");
            params.put("id", request.getIdCategory());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            str.append(" AND c.id = :id ");
            params.put("id", request.getIdColor());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            str.append(" AND p.id = :id ");
            params.put("id", request.getIdProduct());
        }

        Query queryCount = entityManager.createNativeQuery(str.toString());
        params.forEach(queryCount::setParameter);

        int totalRecord = (int) queryCount.getSingleResult();

        listResponse.setTotalRecord(totalRecord);

        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }
}
