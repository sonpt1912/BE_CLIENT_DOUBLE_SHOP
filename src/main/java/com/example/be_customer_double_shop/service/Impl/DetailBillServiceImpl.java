package com.example.be_customer_double_shop.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.be_customer_double_shop.dao.DetailBillDao;
import com.example.be_customer_double_shop.dto.request.BillRequest;
import com.example.be_customer_double_shop.entity.Bill;
import com.example.be_customer_double_shop.entity.DetailBill;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.repository.DetailBillRepository;
import com.example.be_customer_double_shop.service.DetailBillService;
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
public class DetailBillServiceImpl implements DetailBillService {

    @Autowired
    private DetailBillRepository detailBillRepository;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<DetailBill> createDetailBill(Bill bill, List<DetailProduct> listProduct) {
        List<DetailBill> listDetailBill = new ArrayList<>();
        for (DetailProduct detailProduct : listProduct) {
            DetailBill detailBill = DetailBill.builder()
                    .bill(bill)
                    .quantity(detailProduct.getQuantity())
                    .price(detailProduct.getPrice())
                    .detailProduct(DetailProduct.builder().id(detailProduct.getId()).build())
                    .discountAmout(detailProduct.getDiscountAmout())
                    .status(Constant.ACTIVE.intValue())
                    .build();
            listDetailBill.add(detailBill);
        }
        return detailBillRepository.saveAll(listDetailBill);
    }

    @Override
    public Object getDetailBill(BillRequest billRequest) throws InterruptedException, ExecutionException {
        List<DetailBillDao> detailBillDaoList = detailBillRepository.getDetailBillByBill(billRequest.getId());

        List<Callable<Map<String, Object>>> callableList = new ArrayList<>();

        for (DetailBillDao detailBillDao : detailBillDaoList) {
            Callable callable = () -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", detailBillDao.getId());
                map.put("name", detailBillDao.getName());
                map.put("color", detailBillDao.getColor());
                map.put("size", detailBillDao.getSize());
                map.put("code", detailBillDao.getCode());
                map.put("quantity", detailBillDao.getQuantity());
                map.put("price", detailBillDao.getPrice());
                map.put("disscountAmout", detailBillDao.getDiscountAmout());
                map.put("listImages", cloudinary.search().expression("folder:double_shop/product/" + detailBillDao.getCode() + "/*").maxResults(500).execute());
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

    @Override
    public Object deleteDetailBill(long idDetailBill) {
        try {
            detailBillRepository.deleteById(idDetailBill);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constant.SUCCESS;
    }

    @Override
    public DetailBill getDetailBillById(long id) {
        return detailBillRepository.findById(id).get();
    }
}
