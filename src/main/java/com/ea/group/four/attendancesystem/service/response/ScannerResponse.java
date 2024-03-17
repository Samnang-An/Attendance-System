package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import java.io.Serializable;
import lombok.Data;

@Data
public class ScannerResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long scannerId;
  private AccountType accountType;
  private Location location;
  private Event event;
}
