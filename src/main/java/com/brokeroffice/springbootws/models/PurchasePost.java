package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePost {
    public String phonenumber;
    public String amount;
}
