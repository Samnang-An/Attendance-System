package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;
    private String name;
    private String description;

    @OneToOne
    private AccountType accountType;

    @OneToMany
    @JoinTable(name = "Account_Role")
    private List<Role> roles = new ArrayList<>();
}
