package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.pojo.Address;
import com.example.pojo.NewUser;
import com.example.pojo.Result;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
public class PrintHello {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("HelloWorld");
        return "HelloWorld";
    }

    @RequestMapping("/simpleParam")
    public String simpleParam(String name,int age){
        System.out.println(name+":"+age);
        return "Really OK";
    }

    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody NewUser user){
        System.out.println(user);
        return "Really OK";
    }

    @RequestMapping("/pathParam/{id}")
    public String pathParam(@RequestBody NewUser user, @PathVariable int id){
        System.out.println(user);
        System.out.println(id);
        return "Really OK";
    }

    //响应实体对象
    @RequestMapping("/getAddr")
    public Address getAddr(){
        Address addr = new Address();//创建实体类对象
        addr.setProvince("广东");
        addr.setCity("深圳");
        return addr;
    }

    //设置Cookie
    @RequestMapping("/setCookie")
    public Result setCookie(HttpServletResponse response){
        response.addCookie(new Cookie("username","itheima"));
        return Result.success();
    }

    //获取Cookie
    @RequestMapping("/getCookie")
    public Result getCookie(HttpServletRequest request){
        List<Cookie> cookieList= Arrays.asList(request.getCookies());
        for(Cookie c:cookieList){
            if(c.getName().equals("username")){
                System.out.println("username："+c.getValue());
                return Result.success();
            }
        }
        return Result.error("没有Cookie");
    }

    //设置Session
    @RequestMapping("/setSession")
    public Result setSession(HttpSession session){
        session.setAttribute("username","Tom");
        System.out.println("sessionId："+session.hashCode());
        return Result.success();
    }

    //获取Session
    @RequestMapping("/getSession")
    public Result getSession(HttpSession session){
        Object username=session.getAttribute("username");
        System.out.println("sessionId："+session.hashCode());
        System.out.println("username："+username);
        return Result.success();
    }

    //获取数据库中的数据

    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping("/getData")
    public List<User> getData(){
        return userMapper.selectAll();
    }

}
