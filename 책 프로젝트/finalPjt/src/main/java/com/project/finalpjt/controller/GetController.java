package com.project.finalpjt.controller;

import com.project.finalpjt.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // http://localhost:8085/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

    // http://localhost:8085/api/v1/get-api/name
    @RequestMapping(value = "/name")
    public String name() {
        return "My name is Hoon";
    }

    // http://localhost:8085/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable("variable") String variable) {
        return variable;
    }

    // http://localhost:8085/api/v1/get-api/request1?key=value ...
    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam("name") String name,
                                   @RequestParam("name2") String name2) {
        return name + " " + name2;
    }

    // http://localhost:8085/api/v1/get-api/request2?key=value ...
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    // http://localhost:8085/api/v1/get-api/request3?key=value ...
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {

        return memberDto.toString();
    }

}
