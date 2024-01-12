package com.project.finalpjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PythonController {

    @GetMapping("/python")
    @ResponseBody
    public List<String> home() throws IOException, InterruptedException {



        // 번개장터 시세 확인하기
        
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("D:/kdigital2307/SpringBoot/projects/final/finalPjt/src/main/resources/templates/python/test.py");
        command.add("보스 스피커");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PYTHONIOENCODING", "utf-8"); // 인코딩 설정
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String line;
        List<String> list = new ArrayList<>();
        while((line = reader.readLine()) != null) {
            list.add(line);
        }
        System.out.println(list);
        // 1. 오류 메시지 출력
        InputStream errorStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            System.out.println(errorLine);
        }

//        // 2. 프로세스 종료 대기
//        int exitCode = process.waitFor();
//        System.out.println("종료 코드: " + exitCode);
//
//        // 3. 프로세스 강제 종료
//        process.destroy();

        // 4. 종료 코드 확인
        int exitCode = process.exitValue();
        System.out.println("외부 프로그램 종료 코드: " + exitCode);

        return list;
    }
}
