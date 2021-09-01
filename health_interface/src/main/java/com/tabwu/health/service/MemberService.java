package com.tabwu.health.service;

import com.tabwu.health.entity.Member;

public interface MemberService {
    Long addMember(Member member);

    Member getMemberByPhone(String phoneNumber);

    Integer getMemberByDate(String today);

    Integer getAllMember();

    Integer getBeforeThisWeekAllMember(String thisWeekMonday);

    Integer getBeforeThisMonthAllMember(String firstDay4ThisMonth);
}
