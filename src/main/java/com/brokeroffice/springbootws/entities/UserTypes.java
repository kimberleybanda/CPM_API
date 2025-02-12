package com.brokeroffice.springbootws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public String name;
}
