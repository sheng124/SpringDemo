package com.example.demo;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class DemoApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void TestSelectAll() {
		List<User> userlist = userMapper.selectAll();
		for(User u:userlist){
			System.out.println(u);
		}
	}

	//生成JWT的测试
	@Test
	void testJWT(){
		Map<String, Object> claims=new HashMap<>();
		claims.put("id",1);
		claims.put("username","000001");
		claims.put("password","000001");
		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "testJWT")
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
				.compact();
		System.out.println("令牌："+jwt);
	}

	//JWT解析的测试
	@Test
	void testDeJWT(){
		Claims deJWT = Jwts.parser()
				.setSigningKey("testJWT")
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiZXhwIjoxNjgyNTIwMjkyLCJob21lIjoibm9uZSJ9.7H-RbFiYU1wUQb25H5FgD0rB6up1jlseO6jVwOUpttY")
				.getBody();
		System.out.println("解析后的内容："+deJWT);
	}

}
