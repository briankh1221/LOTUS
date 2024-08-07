package com.project.lotus.python.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Python-Controller", description = "시세, 사기 조회 관련 API")
@RequestMapping("/python")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PythonController {

    // 키워드 검색 *24.02.23 kohoon
    @Operation(summary = "시세 검색", description = "검색 한 물건의 시세를 조회합니다.")
    @GetMapping("/price-list")
    public List<String> priceList  (
            @RequestParam(required = false, defaultValue = "") String keyword) throws IOException, InterruptedException {

        List<String> command = new ArrayList<>();

        command.add("python");
        command.add("src/main/resources/templates/python/comparingPrice.py");
        command.add(keyword);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PYTHONIOENCODING", "utf-8");
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        List<String> list = new ArrayList<>();

        while((line = reader.readLine()) != null) {
            list.add(line);
        }

        InputStream errorStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        String errorLine;

        while ((errorLine = errorReader.readLine()) != null) {
            log.info(errorLine);
        }

        // 종료 코드 확인 *24.02.23 kohoon
        int exitCode = process.exitValue();

        return list;
    }
}
