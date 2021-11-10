package com.wanted_server.Service;

import com.wanted_server.Class.Message;
import com.wanted_server.Class.Room;
import com.wanted_server.Dto.MessageCreateDto;
import com.wanted_server.Repository.MessageRepository;
import com.wanted_server.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    // 메세지 생성
    public Message makeMessage(MessageCreateDto messageCreateDto) {
        // 메세지 룸 가져오기
        Room room = roomRepository.findById(messageCreateDto.getRoomId()).get();

        // 메세지 생성
        Message message = new Message(messageCreateDto);
        message.setRoom(room); // 양방향

        // 저장
        Message save = messageRepository.save(message);
        return save;
    }
}
