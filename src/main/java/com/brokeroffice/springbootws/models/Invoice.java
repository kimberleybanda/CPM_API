package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    public String name;
    public String productName;
    public String invoiceNumber;
    public int quantity;
    public double amount;
    public double total;


}
