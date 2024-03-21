package com.ea.group.four.attendancesystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.repository.MemberAccountRepository;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private MemberAccountRepository memberAccountRepository;

  @Test
  public void whenFindListValidMemberAccountReturnAllMemberAccountEnableAndValidBalance() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 100, true);
    entityManager.persistAndFlush(accMember);
    // when
    List<MemberAccount> found = memberAccountRepository.findAllValidAccount();
    // then
    assertThat(found.size()).isGreaterThan(0);
    assertThat(found.get(0).getMemberAccountId())
        .isEqualTo(accMember.getMemberAccountId());
  }

  @Test
  public void whenFindListValidMemberAccountReturnEmptyListAsNoValidBalanceAndNotEnabled() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 0, true);
    entityManager.persistAndFlush(accMember);
    // when
    List<MemberAccount> found = memberAccountRepository.findAllValidAccount();
    // then
    assertThat(found.size()).isEqualTo(0);
  }

  @Test
  public void whenCountAccountMemberReturnOneRecordOfMemberAccount() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 0, true);
    entityManager.persistAndFlush(accMember);
    // when
    int found = memberAccountRepository.countMemberAccountByAccount_AccountIdAndMember_MemberId(
        account.getAccountId(), member.getMemberId()
    );
    // then
    assertThat(found).isEqualTo(1);
  }

  @Test
  public void whenCountAccountMemberReturnZeroRecordOfMemberAccount() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 0, true);
    entityManager.persistAndFlush(accMember);
    // when
    int found = memberAccountRepository.countMemberAccountByAccount_AccountIdAndMember_MemberId(
        account.getAccountId(), 2
    );
    // then
    assertThat(found).isEqualTo(0);
  }

  @Test
  public void whenfindByMember_MemberIdReturnListMemberAccount() {
    // given
    //Account Employee
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    //Account Student
    AccountType employeeAccountType = new AccountType(0, "Employee", 100);
    entityManager.persistAndFlush(employeeAccountType);
    Account employeeAccount = new Account(0, "Employee", "Employee Account", employeeAccountType);
    entityManager.persistAndFlush(employeeAccount);
    //Role
    Role studentRole = new Role(0, "Student", List.of(account, employeeAccount));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 0, true);
    entityManager.persistAndFlush(accMember);

    MemberAccount accMember1 = new MemberAccount(0, member, employeeAccount, 0, true);
    entityManager.persistAndFlush(accMember1);
    // when
    List<MemberAccount> found = memberAccountRepository.findByMember_MemberId(member.getMemberId());
    // then
    assertThat(found.size()).isGreaterThan(0);
    assertThat(found.get(0).getAccount().getAccountId()).isEqualTo(account.getAccountId());
    assertThat(found.get(1).getAccount().getAccountId()).isEqualTo(employeeAccount.getAccountId());
  }
}
