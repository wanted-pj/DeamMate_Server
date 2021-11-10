package com.wanted_server.Repository;

import com.wanted_server.Class.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
