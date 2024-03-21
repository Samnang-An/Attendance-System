package com.ea.group.four.attendancesystem.service.component;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.integration.email.EmailSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BalanceAlertRecieverService.class})
@ExtendWith(SpringExtension.class)
class BalanceAlertRecieverServiceTest {

  @Autowired
  private BalanceAlertRecieverService balanceAlertRecieverService;

  @MockBean
  private EmailSender emailSender;

  @Test
  void testReceiveMessage() throws JsonProcessingException {
    // Arrange
    doNothing().when(emailSender)
        .sendSimpleMessage(Mockito.any(), Mockito.any(), Mockito.any());

    AccountType accountType = new AccountType();
    accountType.setAccountTypeId(1L);
    accountType.setBalance(1);
    accountType.setName("Student");

    Account account = new Account();
    account.setAccountId(1L);
    account.setAccountType(accountType);
    account.setDescription("Student Account");
    account.setName("Student");

    Member member = new Member();
    member.setBarcode("12345");
    member.setEmail("samnang@email.com");
    member.setEvents(new ArrayList<>());
    member.setFirstName("Samnang");
    member.setLastName("An");
    member.setMemberId(1L);
    member.setRoles(new ArrayList<>());

    MemberAccount memberAccount = new MemberAccount();
    memberAccount.setAccount(account);
    memberAccount.setDefaultBalance(1);
    memberAccount.setEnabled(true);
    memberAccount.setMember(member);
    memberAccount.setMemberAccountId(1L);

    // Act
    balanceAlertRecieverService.receiveMessage(
        (new ObjectMapper()).writeValueAsString(memberAccount));

    // Assert
    verify(emailSender).sendSimpleMessage(eq("samnang@email.com"), eq("Balance Alert"),
        Mockito.any());
  }
}
