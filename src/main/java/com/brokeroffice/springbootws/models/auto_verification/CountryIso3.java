package com.brokeroffice.springbootws.models.auto_verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryIso3 {
    public String value;
    public int confidence;
    public String source;
    public int index;
}
