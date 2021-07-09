package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {

	@Inject
	CartService cartService;
	
	// 장바구니 목록 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		cartService.delete(cart_id);
		// 삭제 후 장바구니 목록 갱신
		return "redirect:/shop/cart/list.do";
	}
	
	// 장바구니 비우기
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if(userid != null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	// amount, product_id가 배열로 전달됨
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, @RequestParam int[] cart_id, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if(userid == null) {
			return "redirect:/member/login.do";
		}
		for(int i=0; i<cart_id.length; i++) {
			if(amount[i] == 0) {	// 수량이 0이면 레코드 삭제
				cartService.delete(cart_id[i]);
			} else {	// 0이 아니면 수정
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartService.modifyCart(dto);
			}
		}
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		// 세션 변수
		String userid = (String) session.getAttribute("userid");
		if(userid != null) {
			List<CartDTO> list = cartService.listCart(userid);
			// 장바구니 금액 합계
			int sumMoney = cartService.sumMoney(userid);
			// 3만원 이상이면 배송료 면제
			int fee = sumMoney >= 30000 ? 0 : 2500;
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);
			map.put("sum", sumMoney + fee);
			map.put("list", list);	// 장바구니 목록
			map.put("count", list.size());	// 레코드 개수
			mav.setViewName("shop/cart)list");	// 뷰의 이름
			mav.addObject("map", map);	// 뷰에 전달할 테이터
			return mav;	// cart_list.jsp로 포워딩
		} else {
			return new ModelAndView("member/login", "", null);
		}
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if(userid == null) {
			return "redirect:/member/login.do";
		}
		dto.setUserid(userid);
		cartService.insert(dto);	// 장바구니 테이블에 저장
		// 장바구니 목록 페이지로 이동
		return "redirect:/shop/cart/list.do";
	}
}
