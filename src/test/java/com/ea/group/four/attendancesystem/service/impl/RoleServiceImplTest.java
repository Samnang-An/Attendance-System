package com.ea.group.four.attendancesystem.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.mapper.BaseMapper;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RoleServiceImplTest {

  @MockBean
  private BaseMapper<Role, RoleResponse> baseMapper;

  @MockBean
  private BaseMapper<RoleResponse, Role> reverseBaseMapper;

  @MockBean
  private BaseRepository<Role, Long> baseRepository;

  @Autowired
  private RoleServiceImpl roleServiceImpl;

  @Test
  void testAssignAccountToRoleAssignTwoAccountsToRole() {
    // Arrange
    Role role = new Role();
    role.setAccounts(new ArrayList<>());
    role.setName("Student");
    role.setRoleId(1L);
    Optional<Role> ofResult = Optional.of(role);

    Role role2 = new Role();
    role2.setAccounts(new ArrayList<>());
    role2.setName("Student");
    role2.setRoleId(1L);
    when(baseRepository.save(Mockito.any())).thenReturn(role2);
    when(baseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    RoleResponse roleResponse = new RoleResponse();
    roleResponse.setAccounts(new ArrayList<>());
    roleResponse.setName("Student");
    roleResponse.setRoleId(1L);
    when(baseMapper.map(Mockito.any())).thenReturn(roleResponse);

    Role role3 = new Role();
    role3.setAccounts(new ArrayList<>());
    role3.setName("Student");
    role3.setRoleId(1L);
    when(reverseBaseMapper.map(Mockito.any())).thenReturn(role3);

    AccountTypeResponse accountType = new AccountTypeResponse();
    accountType.setAccountTypeId(1L);
    accountType.setBalance(1);
    accountType.setName("Dining");

    AccountResponse accountResponse = new AccountResponse();
    accountResponse.setAccountId(1L);
    accountResponse.setAccountType(accountType);
    accountResponse.setDescription("Dining Account");
    accountResponse.setName("Dining");

    AccountResponse accountResponse1 = new AccountResponse();
    accountResponse.setAccountId(1L);
    accountResponse.setAccountType(accountType);
    accountResponse.setDescription("Classroom Account");
    accountResponse.setName("Classroom");

    ArrayList<AccountResponse> accounts = new ArrayList<>();
    accounts.add(accountResponse);
    accounts.add(accountResponse1);

    // Act
    RoleResponse actualAssignAccountToRoleResult = roleServiceImpl.assignAccountToRole(1L,
        accounts);

    // Assert
    verify(reverseBaseMapper).map(Mockito.any());
    verify(baseMapper, atLeast(1)).map(Mockito.any());
    verify(baseRepository).findById(Mockito.<Long>any());
    verify(baseRepository).save(Mockito.any());
    assertEquals(2, actualAssignAccountToRoleResult.getAccounts().size());
    assertSame(roleResponse, actualAssignAccountToRoleResult);
  }
}
