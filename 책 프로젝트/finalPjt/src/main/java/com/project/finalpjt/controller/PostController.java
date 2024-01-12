package com.project.finalpjt.controller;

import com.project.finalpjt.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    // http://localhost:8085/api/v1/post-api/member
    // post의 경우 request body에 값들이 담겨 오기 때문에 @RequestBody 사용
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String postMember (@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:8085/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    public String postMember2 (@RequestBody MemberDto memberDto) {

        return memberDto.toString();
    }
}
