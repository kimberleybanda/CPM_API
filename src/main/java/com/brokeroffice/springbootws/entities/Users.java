package com.brokeroffice.springbootws.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String name;
    @Column(name = "phone",unique = true)
    public String phone;
    public String password;

    public String nationalIdUrl;
    public String bankStatementUrl;
    public String proofOfResidencyUrl;

    public String photoUrl;
    public boolean approved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id")
    private UserTypes userType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Countries countries;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City cities;

}
