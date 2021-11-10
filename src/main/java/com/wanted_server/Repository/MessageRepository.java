package com.wanted_server.Repository;

import com.wanted_server.Class.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
