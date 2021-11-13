package com.wanted_server.Service;

import com.wanted_server.Class.Participant;
import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Room;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final PersonalRepository personalRepository;
    private final RoomRepository roomRepository;

    // 룸 생성
    public Room makeRoom(Long senderId, Long receiverId) {
        // 방은 하나, 사람은 둘
        Personal sender = personalRepository.findOne(senderId);
        Personal receiver = personalRepository.findOne(receiverId);

        boolean check = false;
        for (Participant senderParticipant : sender.getParticipants()) {
            Long sendRoomId = senderParticipant.getRoom().getId();
            for (Participant receiverParticipant : receiver.getParticipants()) {
                if (sendRoomId == receiverParticipant.getRoom().getId()) {
                    check = true;
                }
            }
        }

        // 이미 채팅방 있음
        if (check) {
            throw new IllegalStateException("이미 존재하는 채팅방입니다.");
        }

        // 방이 없으면 만듬
        Room room = Room.createRoom();
        Room save = roomRepository.save(room);

        // 2개의 Participant 를 만들고 사람에게 각각 매핑
        Participant senderParticipant = Participant.createParticipant(sender);
        Participant receiverParticipant = Participant.createParticipant(receiver);

        // 2개의 Participant 를 1개의 room 에 매핑
        senderParticipant.setRoom(room);
        receiverParticipant.setRoom(room);

        // 저장
        personalRepository.save(sender);
        personalRepository.save(receiver);

        return save;
    }

}
