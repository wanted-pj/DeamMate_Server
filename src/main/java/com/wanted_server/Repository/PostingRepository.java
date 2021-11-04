package com.wanted_server.Repository;

import com.wanted_server.Class.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
