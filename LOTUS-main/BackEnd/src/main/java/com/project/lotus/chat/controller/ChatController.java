package com.project.lotus.chat.controller;

import com.project.lotus.chat.dto.ChatDto;
import com.project.lotus.chat.dto.RoomDto;
import com.project.lotus.chat.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

    private final ChatServiceImpl chatService;

    @MessageMapping("/{roomIdx}")
    @SendTo("/room/{roomIdx}")
    public ChatDto messageSend(
            @DestinationVariable Long roomIdx,
            ChatDto chatDto) {

        chatService.saveFile(chatDto);

        return chatDto;
    }

    // 채팅방 생성 *24.02.07 jihyun
    @PostMapping("/room")
    public ResponseEntity<RoomDto.Response> roomCreate(
             RoomDto.Request roomDto) {

        RoomDto.Response resoponse = chatService.createRoom(roomDto);

        return ResponseEntity.status(OK).body(resoponse);
    }

    // 채팅 목록 조회 *24.02.07 jihyun
    @GetMapping("/room-list")
    public ResponseEntity<List<RoomDto.Response>> roomList(
            @RequestParam String email) {

        List<RoomDto.Response> roomDtoList = chatService.findRoomList(email);

        return ResponseEntity.status(OK).body(roomDtoList);
    }

    // 채팅 메시지 내용 조회 *24.02.07 jihyun
    @GetMapping("/room/{roomIdx}")
    public ResponseEntity<String> chatShow(
            @PathVariable Long roomIdx) {

        String message = chatService.openFile(roomIdx);

        return ResponseEntity.status(OK).body(message);
    }
}
