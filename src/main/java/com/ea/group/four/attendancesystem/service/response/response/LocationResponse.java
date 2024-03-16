package com.ea.group.four.attendancesystem.service.response.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class LocationResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long locationId;
  private String name;
  private String type;
}
