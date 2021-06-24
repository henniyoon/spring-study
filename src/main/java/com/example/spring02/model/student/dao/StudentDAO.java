package com.example.spring02.model.student.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.student.dto.StudentDTO;

@Repository
public class StudentDAO {
	
	// root-context.xml에 정의된 sqlSession bean을 연결시킴
	@Inject	// 의존 관계 주입
	SqlSession sqlSession;	// mybatis 실행 객체

	public List<StudentDTO> list() {
		// sql 명령어 실행 => 리스트에 담아서 리턴
		return sqlSession.selectList("student.list");
	}
	
}
