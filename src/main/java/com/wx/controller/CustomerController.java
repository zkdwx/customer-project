package com.wx.controller;

import com.wx.bean.Customer;
import com.wx.bean.Response;
import com.wx.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(description = "客户核心接口")
@RestController
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "查询所有客户", notes = "查询所有客户")
    @GetMapping("/findAll")
    @CrossOrigin
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @ApiOperation(value = "根据id查询客户", notes = "根据id查询客户")
    @GetMapping("/getCustomer/{id}")
    @CrossOrigin
    public Customer getCustomer(@PathVariable("id") Integer id){
        return customerService.getCustomer(id);
    }

    @ApiOperation(value = "根据id查询客户2", notes = "根据id查询客户2")
    @GetMapping("/getCustomer2/{id}")
    @CrossOrigin
    public Response<Customer> getCustomer2(@PathVariable("id") Integer id){
        Customer customer =customerService.getCustomer(id);
        Response<Customer> response = new Response<>();
        response.setData(customer);
        response.setCode("0");
        response.setMsg("成功");
        return response;
    }

    @ApiOperation(value = "新增客户", notes = "新增客户")
    @PostMapping("/insert")
    @CrossOrigin
    public int insert(@RequestBody Customer customer){
        return customerService.insert(customer);
    }
}
