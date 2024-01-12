package com.project.finalpjt.controller;

import com.project.finalpjt.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // http://localhost:8085/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String postMember (@RequestBody Map<String, Object> putData) {

        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:8085/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public String postMember2 (@RequestBody MemberDto memberDto) {

        return memberDto.toString();
    }

    // http://localhost:8085/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public MemberDto postMember3 (@RequestBody MemberDto memberDto) {

        return memberDto;
    }

    // http://localhost:8085/api/v1/put-api/member4
    @PutMapping(value = "/member4")
    public ResponseEntity<MemberDto> postMemberDto4 (@RequestBody MemberDto memberDto) {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
