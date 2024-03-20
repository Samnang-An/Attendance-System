package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import edu.miu.common.repository.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface MemberAccountRepository extends BaseRepository<MemberAccount, Long> {

  @Query("select m from MemberAccount m "
      + "Join fetch m.account "
      + "Join fetch m.member "
      + "Join fetch m.account.accountType "
      + "Where m.enabled=true and m.defaultBalance>0")
  List<MemberAccount> findAllValidAccount();

  int countMemberAccountByAccount_AccountIdAndMember_MemberId(long accountId, long memberId);

  List<MemberAccount> findByMember_MemberId(long memberId);

  MemberAccount findByMember_MemberIdAndAccount_AccountType_AccountTypeId(long memberId, long accountypeId);




}
