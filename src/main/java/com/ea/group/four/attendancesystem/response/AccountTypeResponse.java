package com.ea.group.four.attendancesystem.response;

import lombok.Data;

@Data
public class AccountTypeResponse {
    private long accountTypeId;

    private String name;
    private  int balance;
}
