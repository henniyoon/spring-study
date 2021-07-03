package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/product/*")	// 공통된 url pattern
public class ProductController {
	// 스프링 => ProductController => ProductService => ProductDAO => SqlSession
	
	@Inject	// 서비스 객체 주입
	ProductService productService;
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id, HttpServletRequest request) {
		// 상품코드에 해당하는 첨부파일 이름 조회
		String filename = productService.fileInfo(product_id);
		if(filename != null && !filename.equals("-")) {	// 첨부파일이 존재하면
			ServletContext application = request.getSession().getServletContext();
			String path = application.getRealPath("/WEB-INF/views/images/");
			File f = new File(path + filename);
			if(f.exists()) {	// 파일이 존재하면
				f.delete();	// 파일 삭제
			}
		}
		productService.deleteProduct(product_id);
		return "redirect:/shop/product/list.do";
	}
	
	// 상품 등록 페이지로 이동
	@RequestMapping("writer.do")
	public String write() {
		return "shop/product_write";
	}
	
	// edit/상품코드 : 상품코드가 PathVariable로 전달됨
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		// 뷰의 이름
		mav.setViewName("/shop/product_edit");
		// 뷰에 전달할 데이터
		mav.addObject("dto", productService.detailProduct(product_id));
		// product_edit.jsp로 포워딩
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {	// 첨부 파일 존재
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/WEB-INF/views/images/");
				// 디렉토리 생성
				new File(path).mkdir();
				// 업로드된 임시파일을 원하는 디렉토리로 복사
				dto.getFile1().transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		// 상품 정보를 레코드에 저장
		productService.insertProduct(dto);
		// 상품 목록 페이지로 이동
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute ProductDTO dto, HttpServletRequest request) {
		String filename = "-";
		if(!dto.getFile1().isEmpty()) {	// 첨부파일이 존재
			filename = dto.getFile1().getOriginalFilename();
			try {
				ServletContext application = request.getSession().getServletContext();
				String path = application.getRealPath("/WEB-INF/views/images/");
				// 디렉토리 생성
				new File(path).mkdir();
				// 업로드 된 임시 파일을 원하는 디렉토리로 복사
				dto.getFile1().transferTo(new File(path + filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		} else {	// 새로운 파일 첨부가 없을 때
			// 기존의 첨부파일 정보가 지워지지 않도록 처리
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		// 상품 정보 수정
		productService.updateProduct(dto);
		// 상품 목록 페이지로 이동
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		// 뷰의 이름
		mav.setViewName("/shop/product_list");
		// 뷰에 전달할 데이터 저장
		mav.addObject("list", productService.listProduct());
		return mav;
	}
	
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
}

