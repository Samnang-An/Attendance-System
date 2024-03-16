package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountResponse {
    private long accountId;
    private String name;
    private String description;
    private AccountType accountType;
    private List<Role> roles = new ArrayList<>();

}
