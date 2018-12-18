package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

    @Autowired
    private testService t;

    @RequestMapping("/a")
    @ResponseBody
    public String a(){

        return t.count();
    }
}
