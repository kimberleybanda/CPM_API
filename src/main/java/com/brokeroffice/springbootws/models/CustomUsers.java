package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUsers {
    public long id;
    public String name;
    public String phone;
    public String nationalIdUrl;
    public boolean approved;
}
