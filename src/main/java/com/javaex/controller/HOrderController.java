package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.ShoppingService;
import com.javaex.vo.ShoppingVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class HOrderController {

	@Autowired
	private ShoppingService shoppingService;

	// 결제 페이지로 이동
	
//	@RequestMapping(value = "/order/payform", method = {RequestMethod.GET, RequestMethod.POST})
//	public String processPayment(@RequestParam("selectedItems") List<Integer> selectedItems,
//	                             @RequestParam("userNo") Integer userNo,
//	                             @RequestParam(value = "minPrice", required = false) Integer minPrice,
//	                             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
//	                             Model model) {
	@RequestMapping(value = "/order/payform", method = {RequestMethod.GET, RequestMethod.POST})
	public String processPayment(
	                             HttpSession session, Model model) {
	    // 테스트용으로 세션에 구매자 정보 하드코딩
	    UserVo buyer = new UserVo();
	    buyer.setName("임현성");
	    buyer.setHp("010-1234-5678");
	    buyer.setAddress("서울특별시 강남구 테헤란로 123");

	    session.setAttribute("authUser", buyer);
	    // 나머지 로직은 동일
	    Map<String, Object> params = new HashMap<>();
	

	    List<ShoppingVo> selectedShoppingList = shoppingService.getShoppingItemsWithConditions(params);
	    int totalAmount = shoppingService.calculateTotalAmount(selectedShoppingList);
	    int shippingFee = 3000;
		
		
//		// 여러 파라미터를 Map으로 묶음
//	    Map<String, Object> params = new HashMap<>();
//	    params.put("userNo", userNo);
//	    params.put("selectedItems", selectedItems);
//	    if (minPrice != null) params.put("minPrice", minPrice);
//	    if (maxPrice != null) params.put("maxPrice", maxPrice);
//
//	    // 선택된 상품 정보 가져오기
//	    List<ShoppingVo> selectedShoppingList = shoppingService.getShoppingItemsWithConditions(params);
//
//	    // 총 결제 금액 계산
//	    int totalAmount = shoppingService.calculateTotalAmount(selectedShoppingList);
//	    int shippingFee = 3000;  // 고정된 배송비 설정

	    // 모델에 결제할 상품 목록과 총 금액을 전달
	    model.addAttribute("selectedShoppingList", selectedShoppingList);
	    model.addAttribute("totalAmount", totalAmount + shippingFee);
	    model.addAttribute("buyer", buyer);
	    return "order/pay";  // 결제 페이지로 이동
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/orderdetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderdetail() {

		System.out.println("orderdetail");

		return "order/orderDetail";
	}

	@RequestMapping(value = "/orderlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderlist() {

		System.out.println("orderlist");

		return "order/orderList";
	}

}