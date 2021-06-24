package com.example.spring02.controller.student;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring02.model.student.dao.StudentDAO;
import com.example.spring02.model.student.dto.StudentDTO;

@Controller
@RequestMapping("/student/*")	// 공통적인 url pattern
public class StudentController {
	
	@Inject // 의존 관계 주입
	StudentDAO dao;
	
	// http://localhost/student/list
	@RequestMapping("list.do")
	public String list(Model model) {
		// 리스트 구하기
//		StudentDAO dao = new StudentDAO();
		List<StudentDTO> list = dao.list();
		
		// 자료 저장
		model.addAttribute("list", list);
		
		return "student/list";	// list.jsp로 출력
	}
	
}
