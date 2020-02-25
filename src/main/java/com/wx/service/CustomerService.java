package com.wx.service;

import com.wx.bean.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer getCustomer(Integer id);

    public int insert(Customer customer);
}
