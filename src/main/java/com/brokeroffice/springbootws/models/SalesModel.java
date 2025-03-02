package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesModel {

        private String uuid;

        private String shopId;

        private String productName;

        private String price;

        private int count;
        private String phone;
}
