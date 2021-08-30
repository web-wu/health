package com.tabwu.health.mapper;

import com.tabwu.health.entity.Member;

public interface MemberMapper {
    Long addMember(Member member);

    Member getMemberByPhone(String phoneNumber);
}
