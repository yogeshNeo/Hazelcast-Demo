package com.hazelcast.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private long balance;
}