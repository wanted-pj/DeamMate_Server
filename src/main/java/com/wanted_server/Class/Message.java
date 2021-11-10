package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wanted_server.Dto.MessageCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private String content;

    private boolean readCheck;

    public void setRoom(Room room) {
        this.room = room;
        room.getMessages().add(this);
    }

    public Message(MessageCreateDto messageCreateDto){
        this.content = messageCreateDto.getContent();
        this.senderId = messageCreateDto.getSenderId();
        // 읽었는지는 나중에 설정(처음은 무조건 아직 안읽음)
        this.readCheck = false;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", room=" + room.getId() +
                ", senderId=" + senderId +
                ", content='" + content + '\'' +
                ", readCheck=" + readCheck +
                '}';
    }
}
