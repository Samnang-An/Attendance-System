package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.Role;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberResponse {
    private long memberId;

    private String firstName;
    private String lastName;
    private String barcode;
    private String email;
    private List<Role> roles = new ArrayList<>();

}
