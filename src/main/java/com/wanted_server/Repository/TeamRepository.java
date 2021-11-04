package com.wanted_server.Repository;

import com.wanted_server.Class.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
