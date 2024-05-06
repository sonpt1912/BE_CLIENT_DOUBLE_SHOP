package com.example.be_customer_double_shop.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.entity.Product;
import com.example.be_customer_double_shop.repository.ProductRepository;
import com.example.be_customer_double_shop.service.DetailProductService;
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

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private DetailProductService detailProductService;

    @PersistenceContext
    private EntityManager entityManager;

    public Object getAllByCondition(ProductRequest request) throws Exception {
        ListResponse<Product> listResponse = (ListResponse<Product>) getByCondition(request);
        for (int i = 0; i < listResponse.getListData().size(); i++) {
            listResponse.getListData().get(i).setListImages(cloudinary.search().expression("folder:double_shop/product/" + listResponse.getListData().get(i).getCode() + "/*").maxResults(500).execute());
        }
        return listResponse;
    }

    private Object getByCondition(ProductRequest request) {

        ListResponse<Product> listResponse = new ListResponse<>();

        StringBuilder str = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        str.append(" SELECT p.* FROM product p INNER JOIN detail_product dp ON p.id = dp.id_product ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN color c ON dp.id_color = c.id ");
        str.append(" INNER JOIN material m ON p.id_material = m.id ");
        str.append(" INNER JOIN category ct ON p.id_category = ct.id ");
        str.append(" INNER JOIN collar cl ON p.id_collar = cl.id ");
        str.append(" INNER JOIN brand b ON p.id_brand = b.id ");
        str.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdProduct())) {
            str.append(" AND p.id = :idProduct ");
            params.put("idProduct", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            str.append(" AND b.id = :idBrand ");
            params.put("idBrand", request.getIdBrand());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            str.append(" AND ct.id = :idCategory ");
            params.put("idCategory", request.getIdCategory());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            str.append(" AND c.id = :idColor ");
            params.put("idColor", request.getIdColor());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            str.append(" AND cl.id = :idCollar ");
            params.put("idCollar", request.getIdCollar());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            str.append(" AND s.id = :idSize ");
            params.put("idSize", request.getIdSize());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            str.append(" AND m.id = :idMaterial ");
            params.put("idMaterial", request.getIdMaterial());
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

        Query query = entityManager.createNativeQuery(str.toString(), Product.class);
        params.forEach(query::setParameter);
        listResponse.setListData(query.getResultList());

        str.setLength(0);
        params.clear();

        str.append(" SELECT COUNT(p.id) FROM product p INNER JOIN detail_product dp ON p.id = dp.id_product ");
        str.append(" INNER JOIN size s ON dp.id_size = s.id ");
        str.append(" INNER JOIN color c ON dp.id_color = c.id ");
        str.append(" INNER JOIN material m ON p.id_material = m.id ");
        str.append(" INNER JOIN category ct ON p.id_category = ct.id ");
        str.append(" INNER JOIN collar cl ON p.id_collar = cl.id ");
        str.append(" INNER JOIN brand b ON p.id_brand = b.id ");
        str.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdProduct())) {
            str.append(" AND p.id = :idProduct ");
            params.put("idProduct", request.getIdProduct());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            str.append(" AND b.id = :idBrand ");
            params.put("idBrand", request.getIdBrand());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            str.append(" AND ct.id = :idCategory ");
            params.put("idCategory", request.getIdCategory());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            str.append(" AND c.id = :idColor ");
            params.put("idColor", request.getIdColor());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            str.append(" AND cl.id = :idCollar ");
            params.put("idCollar", request.getIdCollar());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            str.append(" AND s.id = :idSize ");
            params.put("idSize", request.getIdSize());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            str.append(" AND m.id = :idMaterial ");
            params.put("idMaterial", request.getIdMaterial());
        }

        Query queryCount = entityManager.createNativeQuery(str.toString());
        params.forEach(queryCount::setParameter);

        int totalRecord = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(totalRecord);

        return listResponse;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Object getDetailProductByProduct(ProductRequest request) {
        return null;
    }

    @Override
    public Product getProduct(long id) throws Exception {
        Product product = this.getProductById(id);
        product.setListImages(cloudinary.search().expression("folder:double_shop/product/" + product.getCode() + "/*").maxResults(500).execute());
        return product;
    }

    @Override
    public DetailProduct getDetailProduct(ProductRequest request) {
        return null;
    }


}
