package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.repository.MemberAccountRepository;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAccountServiceImpl extends
    BaseReadWriteServiceImpl<MemberAccountResponse, MemberAccount, Long> implements
    MemberAccountService {

  @Autowired
  private MemberAccountRepository memberAccountRepository;

  @Override
  public List<MemberAccountResponse> findAllValidAccount() {
    return convert(memberAccountRepository.findAllValidAccount());
  }

  @Override
  public void assignMemberAccount(MemberResponse member, List<AccountResponse> accounts) {
    accounts.parallelStream().forEach(
        account -> {
          if (memberAccountRepository.countMemberAccountByAccount_AccountIdAndMember_MemberId(
              account.getAccountId(), member.getMemberId()) == 0) {
            MemberAccountResponse memberAccountResponse = new MemberAccountResponse();
            memberAccountResponse.setAccount(account);
            memberAccountResponse.setMember(member);
            memberAccountResponse.setDefaultBalance(account.getAccountType().getBalance());
            memberAccountResponse.setEnabled(true);
            create(memberAccountResponse);
          }
        }
    );
  }

  @Override
  public void refreshMemberAccount(MemberResponse member) {
    Set<Long> validAccounts = member.getRoles().stream().map(role -> role.getAccounts())
        .flatMap(List::stream).map(AccountResponse::getAccountId).collect(Collectors.toSet());
    memberAccountRepository.findByMember_MemberId(member.getMemberId()).forEach(
        memberAccount -> {
          if (!validAccounts.contains(memberAccount.getAccount().getAccountId())) {
            delete(memberAccount.getMemberAccountId());
          }
        }
    );
  }
}
