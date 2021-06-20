package com.example.student;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	@Inject
	StudentDAO dao;
	//   http://localhost/student/list
	@RequestMapping("list.do")
	public String list(Model model) {
		//리스트 구하기
		List<StudentDTO> list=dao.list();
		
		//자료 저장
		model.addAttribute("list", list);
		
		return "list"; //   list.jsp로 출력
	}
	
}
