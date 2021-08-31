package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tabwu.health.entity.Member;
import com.tabwu.health.mapper.MemberMapper;
import com.tabwu.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;


    @Override
    public Long addMember(Member member) {
        return memberMapper.addMember(member);
    }

    @Override
    public Member getMemberByPhone(String phoneNumber) {
        return memberMapper.getMemberByPhone(phoneNumber);
    }
}
