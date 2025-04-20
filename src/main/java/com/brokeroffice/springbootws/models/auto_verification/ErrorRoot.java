package com.brokeroffice.springbootws.models.auto_verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRoot {
    public boolean success;
    public int reviewScore;
    public int rejectScore;
    public String decision;
    public int quota;
    public int credit;
    public double executionTime;
}
