package com.project.lotus.chat.service;

import com.project.lotus.chat.dto.ChatDto;
import com.project.lotus.chat.dto.RoomDto;
import java.util.List;

public interface ChatService {

    public RoomDto.Response createRoom (RoomDto.Request roomDto);

    public void saveFile(ChatDto chatDto);

    public List<RoomDto.Response> findRoomList(String email);
}
