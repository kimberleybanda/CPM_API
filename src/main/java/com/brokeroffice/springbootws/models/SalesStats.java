package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesStats {
    private int productCount;
    private String productName;

}
