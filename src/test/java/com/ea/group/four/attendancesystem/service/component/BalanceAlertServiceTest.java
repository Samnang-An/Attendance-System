package com.ea.group.four.attendancesystem.service.component;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BalanceAlertService.class})
@ExtendWith(SpringExtension.class)
class BalanceAlertServiceTest {

  @Autowired
  private BalanceAlertService balanceAlertService;

  @MockBean
  private JmsTemplate jmsTemplate;

  @MockBean
  private MemberAccountService memberAccountService;

  @Test
  void testAlertMemberEnsureThatMessageIsSent() throws JmsException {
    // Arrange
    doNothing().when(jmsTemplate).convertAndSend(Mockito.<String>any(), Mockito.<Object>any());

    AccountTypeResponse accountType = new AccountTypeResponse();
    accountType.setAccountTypeId(1L);
    accountType.setBalance(1);
    accountType.setName("Student");

    AccountResponse account = new AccountResponse();
    account.setAccountId(1L);
    account.setAccountType(accountType);
    account.setDescription("student account type");
    account.setName("Student");

    AccountTypeResponse accountType2 = new AccountTypeResponse();
    accountType2.setAccountTypeId(1L);
    accountType2.setBalance(1);
    accountType2.setName("Classroom");

    AccountResponse accountResponse = new AccountResponse();
    accountResponse.setAccountId(1L);
    accountResponse.setAccountType(accountType2);
    accountResponse.setDescription("Classroom account type");
    accountResponse.setName("Classroom");

    MemberResponse memberResponse = new MemberResponse();
    memberResponse.setBarcode("12233");
    memberResponse.setEmail("samnang@email.com");
    memberResponse.setFirstName("samnang");
    memberResponse.setLastName("An");
    memberResponse.setMemberId(1L);
    memberResponse.setRoles(new ArrayList<>());
    //Mock the MemberAccountResponse
    MemberAccountResponse memberAccountResponse = mock(MemberAccountResponse.class);
    when(memberAccountResponse.isEnabled()).thenReturn(true);
    when(memberAccountResponse.getMember()).thenReturn(memberResponse);
    when(memberAccountResponse.getDefaultBalance()).thenReturn(0);
    when(memberAccountResponse.getMemberAccountId()).thenReturn(1L);
    when(memberAccountResponse.getAccount()).thenReturn(accountResponse);
    doNothing().when(memberAccountResponse).setAccount(Mockito.<AccountResponse>any());

    memberAccountResponse.setAccount(account);

    ArrayList<MemberAccountResponse> memberAccountResponseList = new ArrayList<>();
    memberAccountResponseList.add(memberAccountResponse);
    when(memberAccountService.findAllValidAccount()).thenReturn(memberAccountResponseList);

    // Act
    balanceAlertService.alertMember();

    // Assert
    verify(memberAccountService).findAllValidAccount();
    verify(memberAccountResponse, atLeast(1)).getAccount();
    verify(memberAccountResponse, atLeast(1)).getDefaultBalance();
    verify(memberAccountResponse, atLeast(1)).getMember();
    verify(memberAccountResponse).getMemberAccountId();
    verify(memberAccountResponse).isEnabled();
    verify(memberAccountResponse).setAccount(Mockito.<AccountResponse>any());
    verify(jmsTemplate).convertAndSend(eq("balance.alert.queue"), Mockito.<Object>any());
  }
}
