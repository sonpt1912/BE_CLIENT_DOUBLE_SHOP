package com.example.be_customer_double_shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {


        private Long id;
        private String customerName;
        private String district;
        private String province;
        private String war;
        private String description;
        private Integer defaul;
        private String provinceName;
        private String districtName;
        private String wardName;
        private String createdBy;
        private String createdTime;
        private String updatedy;
        private String updatedTime;



}
