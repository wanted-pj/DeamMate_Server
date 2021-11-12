package com.wanted_server.Service;

import com.wanted_server.Class.Participant;
import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Room;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
