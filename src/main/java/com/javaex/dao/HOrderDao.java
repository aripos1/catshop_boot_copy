package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ShoppingVo;
import com.javaex.vo.UserVo;

@Repository
public class HOrderDao {
	
	@Autowired
	private SqlSession sqlSession;
    
	public List<ShoppingVo> getShoppingList(ShoppingVo userNo) {
    	List<ShoppingVo> shoppingList  = sqlSession.selectList("shopping.selectList",userNo);
    			
        return shoppingList;
    }
	
	public UserVo getUserOne(UserVo userVo) {
		UserVo userOne  = sqlSession.selectOne("user.selectOne",userVo);
    			
        return userOne;
    }
	
}
