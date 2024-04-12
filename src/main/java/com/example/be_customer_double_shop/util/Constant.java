package com.example.be_customer_double_shop.util;

public class Constant {

    public static final String SUCCESS = "SUCCESS";


    public static final String FAIL = "FAIL";

    public static final String ROOT_FOLDER = "double_shop";

    public static final String PRODUCT_FOLDER = "product";

    public static final Integer ACTIVE = 0;


    public static final String UNAUTHORIZED = "401";

    public static final String USERNAME_OR_PASSWORD = "Tài khoản hoặc mật khẩu không chính xác";

    public static final Integer IN_ACTIVE = 1;

    public static final Integer IS_ADMIN = 0;

    public static final Integer IS_EMPLOYEE = 1;

    public static final String API001 = "API001";

    public static final String DEFAULT_PASSWORD = "hello";

    public interface DETAIL_PRODUCT {

        String CATEGORY = "CATEGORY";
        String SIZE = "SIZE";
        String COLLAR = "COLLAR";

        String BRAND = "BRAND";

    }

    public interface PRODUCT {

        String PRODUCT = "PRODUCT";

        Long ACTIVE = 0L;

        Long INACTIVE = 1L;

        Long PAUSE = 2L;
    }

    public interface BILL {

        interface PAYMENT {
            Long BANK = 0L;

            Long CASH = 1L;
        }

        interface TYPE {

            Integer IN_STORE = 0;

            Integer DEVERILY = 1;
        }

        interface STATUS {
            // cho xac nhan
            Integer WAIT_CONFIRM = 0;

            // xac nhan
            Integer CONFIRM = 1;

            // cho giao hang
            Integer WAIT_DEVERILY = 2;

            // giao hang
            Integer DEVERILY = 3;

            // hoan thanh
            Integer DONE = 4;

            // huy
            Integer CANCELLATION = 5;

            // hoan tra
            Integer RETURN = 6;

            // chinh sua
            Integer EDIT = 7;
        }

    }

}
