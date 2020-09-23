package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Address;
import com.wpp.greenorange.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

/**
 * (Address)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:03:28
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 查询所有地址信息
     *
     * @param
     * @return 信息
     */
    @RequestMapping("/getConditions")
    public List<Address> getConditions(String userId){
        System.out.println(userId);
        Address address=new Address();
        address.setUserId(Integer.parseInt(userId));
        List<Address> allByCondition = this.addressService.findAllByCondition(address);
//        System.out.println(allByCondition);

        return allByCondition;
    };

    /**
     *进行添加地址数据
     *
     * @param contact 单条数据
     * @param address_1 单条数据
     * @param mobile 单条数据
     * @param notes 单条数据
     * @return boolean
     */
    @ResponseBody
    @RequestMapping("/SetAddress")
    public Boolean SetAddress(String userId,String contact,String address_1,String mobile,String notes) {
//        以下是页面传入拿值操作
        Address address=new Address();
        address.setUserId(Integer.parseInt(userId));
        address.setContact(contact);
        address.setAddress(address_1);
        address.setMobile(mobile);
        address.setNotes(notes);
//
//        System.out.println(address);
//        以下是新增操作
        String address_s = "";
//
        String address1 = address.getAddress();
        String replaces = address1.replace(",", "/");
        String[] split = replaces.split("/");
        for (int i = 0; i <split.length ; i++) {
            address_s+=split[i].toString()+" ";
        }
        address.setAddress(address_s);
//
        Boolean insert = this.addressService.insert(address);
        return insert;
    }

    /**
     * 修改数据
     *
     * @param contact 单条数据
     * @param address_1 单条数据
     * @param mobile 单条数据
     * @param id 单条数据
     * @return 是否成功
     */
    @RequestMapping("/update")
    public Boolean update(String contact,String address_1,String mobile,String id){
        int ids = Integer.parseInt(id);
//        以下是页面传入拿值操作
        Address address=new Address();
        address.setContact(contact);
        address.setAddress(address_1);
        address.setMobile(mobile);
        address.setId(ids);
//
        //        System.out.println(address);
//
        String address_s = "";
//
        String address1 = address.getAddress();
        String replaces = address1.replace(",", "/");
        String[] split = replaces.split("/");
        for (int i = 0; i <split.length ; i++) {
            address_s+=split[i].toString()+" ";
        }
        address.setAddress(address_s);
//
        Boolean update = this.addressService.update(address);
        return update;

    };

    /**
     * 删除数据
     *
     * @param id 实例对象ID
     * @return 是否成功
     */
    @RequestMapping("/deleteById")
    public Boolean deleteById(Integer id){
//        System.out.println(id);
        Boolean aBoolean = this.addressService.deleteById(id);
        return aBoolean;
    };

    /**
     * 查询user值
     *
     * @param name 实例对象name
     * @return id值
     */
    @RequestMapping("/findByuUserId")
    public Integer findByuUserId(String name){
        Integer byuUserId = this.addressService.findByuUserId(name);
        return byuUserId;
    };
}