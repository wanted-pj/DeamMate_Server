package com.wanted_server.Service;

import com.wanted_server.Repository.ConnectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConnectService {

    private final ConnectRepository connectRepository;

    // 커넥트 만들기
    public Long make()
}
