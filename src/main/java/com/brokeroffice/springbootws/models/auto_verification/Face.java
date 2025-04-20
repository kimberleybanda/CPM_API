package com.brokeroffice.springbootws.models.auto_verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Face {
    public String value;
    public int confidence;
    public int index;
    public ArrayList<ArrayList<Integer>> inputBox;
}
