package com.tabwu.health.mapper;

import com.tabwu.health.entity.Member;

public interface MemberMapper {
    Long addMember(Member member);

    Member getMemberByPhone(String phoneNumber);

    Integer getMemberByDate(String today);

    Integer getAllMember();

    Integer getBeforeThisWeekAllMember(String thisWeekMonday);

    Integer getBeforeThisMonthAllMember(String firstDay4ThisMonth);
}
