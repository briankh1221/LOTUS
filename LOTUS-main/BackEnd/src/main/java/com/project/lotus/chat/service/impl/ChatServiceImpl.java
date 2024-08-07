package com.project.lotus.chat.service.impl;

import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.chat.dto.ChatDto;
import com.project.lotus.chat.dto.RoomDto;
import com.project.lotus.chat.entity.Room;
import com.project.lotus.chat.repository.RoomRepository;
import com.project.lotus.chat.service.ChatService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.product.entity.Product;
import com.project.lotus.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static com.project.lotus.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @Value("${file.path}")
    private String fileDir;

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ProductRepository productRepository;

    // 채팅방 생성 *24.02.07 jihyun
    public RoomDto.Response createRoom (RoomDto.Request roomDto) {

        Room room = new Room();

        try {
            // 기존 채팅방 있는지 확인 *24.02.07 jihyun
            room = roomRepository.findByBuyer_EmailAndProduct_ProductIdx(roomDto.getBuyerEmail(), roomDto.getProductIdx())
                    .orElseThrow(() -> new CustomException(ROOM_NOT_EXISTS));
            // 채팅방 없으면 채팅방 생성 *24.02.07 jihyun
        } catch (Exception e) {
            User buyer = userRepository.findByEmail(roomDto.getBuyerEmail())
                    .orElseThrow(() -> new CustomException(BUYER_NOT_EXISTS));

            Product product = productRepository.findByProductIdx(roomDto.getProductIdx())
                    .orElseThrow(() -> new CustomException(PRODUCT_NOT_EXISTS));

            room = roomRepository.save(Room.from(roomDto, buyer, product));
        }

        return RoomDto.Response.from(room);
    }

    // 채팅 목록 조회 *24.02.07 jihyun
    public List<RoomDto.Response> findRoomList(String email) {

        List<Room> roomList = roomRepository.findByBuyer_EmailOrProduct_User_Email(email, email)
                .orElseThrow(() -> new CustomException(ROOM_LIST_NOT_EXISTS));

        List<RoomDto.Response> roomDtoList = new ArrayList<>();

        for (Room room : roomList) {
            roomDtoList.add(RoomDto.Response.from(room));
        }

        return roomDtoList;
    }

    // 채팅 내용 텍스트 파일로 저장함 *24.02.07 jihyun
    public void saveFile(ChatDto chatDto) {

        // 구매자 이메일, 상품번호 합하여 고유 텍스트 파일명 저장 *24.01.26 jihyun
        String filePath = fileDir + "/" + chatDto.getBuyerEmail() + "_" + chatDto.getProductIdx() + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String writer;

            // 채팅 메시지 구매자인지 판매자인지 구분함 *24.02.07 jihyun
            if (chatDto.getIsBuyerEmail()) {
                writer = "Buyer";

            } else {
                writer = "Seller";
            }

            // 채팅 메시지 형식 *24.02.07 jihyun
            String formattedMessage = String.format("%s//%s::%s##",
                    LocalDateTime.now(), writer, chatDto.getMessage());

            bufferedWriter.write(formattedMessage);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 채팅 텍스트 파일 오픈 *24.02.07 jihyun
    public String openFile(Long roomIdx) {

        Room room = roomRepository.findByRoomIdx(roomIdx)
                .orElseThrow(() -> new CustomException(ROOM_NOT_EXISTS));

        String BuyerEmail = room.getBuyer().getEmail();
        Long productIdx = room.getProduct().getProductIdx();
        Path path = Paths.get(fileDir + "/" + BuyerEmail + "_" + productIdx+ ".txt");
        Charset charset = StandardCharsets.UTF_8;

        List<String> list = new ArrayList<>();
        String message = "";

        try {
            list = Files.readAllLines(path, charset);

        } catch(Exception e) {
            e.printStackTrace();

        } finally {
            for (String line: list) {
                message += line;
            }
        }

        return message;
    }
}
