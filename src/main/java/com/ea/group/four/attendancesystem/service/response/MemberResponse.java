package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MemberResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long memberId;

  private String firstName;
  private String lastName;
  private String barcode;
  private String email;
  private List<RoleResponse> roles = new ArrayList<>();

}
