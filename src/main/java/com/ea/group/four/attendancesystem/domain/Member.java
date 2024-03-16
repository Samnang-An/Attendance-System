package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;

    private String firstName;
    private String lastName;
    private String barcode;
    private String email;
    @OneToMany
    @JoinTable(name = "Member_Role")
    private List<Role> roles = new ArrayList<>();


}
