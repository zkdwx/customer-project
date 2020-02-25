package com.wx.service;

import com.wx.bean.Customer;
import com.wx.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    //注入redisTemplate
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //redis key 的序列化器，提高key的可读性
    RedisSerializer redisSerializer = new StringRedisSerializer();

    @Override
    public List<Customer> findAll() {
        redisTemplate.setKeySerializer(redisSerializer);
        //查询缓存

        //高并发下处理，防止出现缓存穿透
        List<Customer> list = (List<Customer>) redisTemplate.opsForValue().get("allCustomer");
        //双重检测锁
        if (list == null) {
            synchronized (this) {
                list = (List<Customer>) redisTemplate.opsForValue().get("allCustomer");
                if (list == null) {
                    //缓存为空，查询一遍数据库
                    list = customerMapper.findAll();
                    //把查询出来的数据，放入到redis中
                    redisTemplate.opsForValue().set("allCustomer", list);
                }
            }
        }
        return list;
    }

    @Override
    public Customer getCustomer(Integer id) {
        return customerMapper.getCustomer(id);
    }

    @Override
    public int insert(Customer customer) {
        return customerMapper.insert(customer);
    }
}
