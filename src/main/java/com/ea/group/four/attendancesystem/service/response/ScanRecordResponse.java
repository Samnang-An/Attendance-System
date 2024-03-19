package com.ea.group.four.attendancesystem.service.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScanRecordResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private long recordId;
    private ScannerResponse scanner;
    private MemberResponse member;

}

