package com.wf2311.jfeng.jfeng.test.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.wf2311.commons.spring.support.base.BaseController;
import com.wf2311.commons.web.support.base.BaseModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wf2311
 * @time 2016/08/05 17:46.
 */
@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController{

    @RequestMapping(value = "get")
    public BaseModel get(){
        BaseModel rtn=new BaseModel();
        rtn.setCode(1);
        rtn.setMsg("this is a get method.");
        return rtn;
    }

    @RequestMapping(value = "/post/{name}",method = RequestMethod.POST)
    public BaseModel<String> post(@PathVariable("name") String name){
        BaseModel<String> rtn=new BaseModel<String>();
        rtn.setCode(2);
        rtn.setMsg("this is a post method.");
        rtn.setData(name);
        return rtn;
    }

    @RequestMapping(value = "/put",method = RequestMethod.PUT)
    public BaseModel<JSONObject> put(){
        BaseModel<JSONObject> rtn=new BaseModel<JSONObject>();
        JSONObject jo=super.convertRequestBody();
        rtn.setCode(3);
        rtn.setMsg("this is a put method.");
        rtn.setData(jo);
        return rtn;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public BaseModel delete(int code,String msg){
        BaseModel rtn=new BaseModel();
        rtn.setCode(code);
        rtn.setMsg(msg);
        return rtn;
    }
}
