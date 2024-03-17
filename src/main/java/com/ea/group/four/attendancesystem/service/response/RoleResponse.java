package com.ea.group.four.attendancesystem.service.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class RoleResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long roleId;
  private String name;
}
