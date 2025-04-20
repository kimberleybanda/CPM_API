package com.brokeroffice.springbootws.models.auto_verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dob {
    public String value;
    public double confidence;
    public String source;
    public int index;
    public ArrayList<ArrayList<Integer>> inputBox;
    public ArrayList<ArrayList<Integer>> outputBox;
}
