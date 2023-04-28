package com.example.mapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    //查询所有用户数据
    @Select("select * from user")
    public List<User> selectAll();

    //根据id删除数据
    @Delete("delete from user where id= #{id}")
    public void delete(int id);

    //插入数据
    public void insert();

}
