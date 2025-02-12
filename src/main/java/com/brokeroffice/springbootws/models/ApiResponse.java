package com.brokeroffice.springbootws.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ApiResponse {
    public int status;
    public String message;
    public Object result;
}
