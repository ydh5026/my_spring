package com.example.spring02.model.member.dao;

import com.example.spring02.model.member.dto.MemberDTO;

public interface MemberDAO {

	public String loginCheck(MemberDTO dto);
	
}
