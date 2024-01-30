package com.ewallet.service;

import com.ewallet.commoncode.UserCreatedPayLoad;
import com.ewallet.dto.UserDto;

import com.ewallet.entity.User;
import com.ewallet.repo.IUserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.Future;


@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static String USER_CREATED_TOPIC = "USER-CREATED";
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Transactional
    public long create(final UserDto userDto){
        final User user = new User();
        mapToEntity(userDto, user);
        userRepo.save(user);
        UserCreatedPayLoad userCreatedPayLoad = new UserCreatedPayLoad(user.getId(),user.getName(),user.getEmail());
        Future<SendResult<String, Object>> future = kafkaTemplate.send(USER_CREATED_TOPIC, String.valueOf(user.getId()), userCreatedPayLoad);
        LOGGER.info("Pushed userCreatedPayLoad in kafka:{}",future);
        return user.getId();

    }

    private User mapToEntity(final UserDto userDto,final User user){
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setKycNumber(userDto.getKycNumber());
        return user;
    }

}