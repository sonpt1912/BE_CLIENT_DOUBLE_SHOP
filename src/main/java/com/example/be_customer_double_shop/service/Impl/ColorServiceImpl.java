package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.ColorRequest;
import com.example.be_customer_double_shop.entity.Color;
import com.example.be_customer_double_shop.repository.ColorRespository;
import com.example.be_customer_double_shop.service.ColorService;
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
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRespository colorRespository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Color> getAllByStatus() {
        return colorRespository.findAllByStatus(Constant.ACTIVE);
    }

    @Override
    public List<Color> getAllByCondition(ColorRequest colorRequest) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("SELECT c.* FROM Color c INNER JOIN detail_product dp on c.id = dp.id_color ");
        sql.append("INNER JOIN Size s on s.id = dp.id_size ");
        sql.append("WHERE dp.status = 0 ");

        sql.append("AND dp.id_product = :idProduct ");
        params.put("idProduct", colorRequest.getIdProduct());

        sql.append("AND s.id = :idSize ");
        params.put("idSize", colorRequest.getIdSize());

        Query query = entityManager.createNativeQuery(sql.toString(), Color.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
