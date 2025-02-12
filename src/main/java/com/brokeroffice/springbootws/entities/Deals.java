package com.brokeroffice.springbootws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deals {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public boolean status;
    public double amount;
    public int qty;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id")
    private Products products;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users;

    @Transient
    boolean approve;
}
