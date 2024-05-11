package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.SizeRequest;
import com.example.be_customer_double_shop.entity.Size;
import com.example.be_customer_double_shop.repository.SizeRepository;
import com.example.be_customer_double_shop.service.SizeService;
import com.example.be_customer_double_shop.util.Constant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAllSize() {
        return sizeRepository.getAllSizes(Constant.ACTIVE);
    }

    @Override
    public List<Size> getAllByCondition(SizeRequest sizeRequest) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("SELECT s.* FROM size s INNER JOIN detail_product dp on s.id = dp.id_size ");
        sql.append("INNER JOIN product p on p.id = dp.id_product ");
        sql.append("WHERE dp.status = 0 ");
        sql.append("AND dp.id_product = :idProduct ");
        params.put("idProduct", sizeRequest.getIdProduct());
        sql.append(" GROUP BY s.id ");
        sql.append(" ORDER BY s.name DESC ");

        Query query = entityManager.createNativeQuery(sql.toString(), Size.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
