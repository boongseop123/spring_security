package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    @Transactional
    public void subscribe(int fromUserId, int toUserId){
        subscribeRepository.mSubscribe(fromUserId,toUserId);
    }
    @Transactional
    public void unSubscribe(int fromUserId, int toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
