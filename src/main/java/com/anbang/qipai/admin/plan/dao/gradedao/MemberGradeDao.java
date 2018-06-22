package com.anbang.qipai.admin.plan.dao.gradedao;

import com.anbang.qipai.admin.plan.domain.Grade.MemberGrade;

public interface MemberGradeDao {

	void insert_grade(MemberGrade memberGrade);
	
	MemberGrade find_grade(String id) ;
}
