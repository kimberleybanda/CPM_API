package com.brokeroffice.springbootws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public String name;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Countries country;

}
