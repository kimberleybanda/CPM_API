package com.brokeroffice.springbootws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        public Long id;
        public String imageUrl;
        public String price;
        public String about;
        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product_id")
        private Product product;
}
