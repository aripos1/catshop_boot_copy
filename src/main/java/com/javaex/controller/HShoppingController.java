package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.ShoppingService;
import com.javaex.vo.ShoppingVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class HShoppingController {

	@Autowired
	private ShoppingService shoppingService;
	
		//장바구니 불러오기
	  @RequestMapping(value="/shopping/form", method= {RequestMethod.GET, RequestMethod.POST})
	   public String form(@ModelAttribute ShoppingVo shoppingVo,Model model) {
	      
		  int userNo = 2;
//		  UserVo loginUser = (UserVo) session.getAttribute("authUser");
//	      int userNo = loginUser.getNo();  // 로그인한 유저의 번호
//           
           // ShoppingVo에 유저 번호 설정
          shoppingVo.setUserNo(userNo);
           
	      System.out.println("form");
	      List<ShoppingVo> shoppingList= shoppingService.exeShoppingList(shoppingVo);
	     
	      model.addAttribute("shoppingList",shoppingList);
	      System.out.println(shoppingList);
	      
	      return "order/shopping";
	   }
	  
	  // 장바구니 상품 추가

	    @RequestMapping(value = "/shopping/add",method= {RequestMethod.GET, RequestMethod.POST})
	    public String addShoppingItem(@ModelAttribute ShoppingVo shoppingVo,HttpSession session) {
	    	System.out.println("add");
	    	  ShoppingVo shoppingVo1 = new ShoppingVo();
	    	    shoppingVo1.setUserNo(2);
	    	    shoppingVo1.setGoodsNo(2);  // 임시 상품 번호
	    	    shoppingVo1.setCount(3);  // 임시 수량
	    	    shoppingVo1.setOptionsNo(2); 
	    		System.out.println(shoppingVo1);
	    
	        shoppingService.addShoppingItem(shoppingVo1);
	        return "redirect:/shopping/form";  // 장바구니 목록 페이지로 리다이렉트
	    }
	  
	  
		// 장바구니 상품 삭제
	    @RequestMapping(value = "/shopping/delete", method = RequestMethod.POST)
	    public String deleteShoppingItem(@RequestParam("shoppingNo") int shoppingNo) {
	        shoppingService.deleteShoppingItem(shoppingNo);
	        return "redirect:/shopping/form";  // 장바구니 목록으로 리다이렉트
	    }
	  
}
