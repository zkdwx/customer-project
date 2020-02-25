package com.wx.mapper;

import com.wx.bean.Customer;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerMapper {

    @Select("select * from customer")
    public List<Customer> findAll();

    @Select("select * from customer where id=#{id}")
    public Customer getCustomer(Integer id);

    @Insert({"insert into customer(name,sex,tell,addr) values(#{name},#{sex},#{tell},#{addr})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Customer customer);

}
