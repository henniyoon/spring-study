package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartService {
	public List<CartDTO> cartMoney();
	public void insert(CartDTO dto);
	public List<CartDTO> listCart(String userid);
	public void delete(int cart_id);
	public void deleteAll(String userid);
	public int sumMoney(String userid);
	// 장바구니 수량 변경
	public void modifyCart(CartDTO dto);
}
