package com.example.shopping.service;

import com.example.shopping.dto.UserDTO;
import com.example.shopping.mapper.UserMapper;
import com.example.shopping.model.UserEntity;
import com.example.shopping.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        Sort sort = Sort.by(Sort.Order.asc("username"));
        List<UserEntity> users = userRepository.findAll(sort);
        log.info("invoked UserService.getAllUsers");
        return users.stream().map(this.userMapper::toUserDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Integer id) {
        log.info("invoked UserService.getUserById for id: " + id);
        Optional<UserEntity> userEntity = userRepository.findById(id);
        log.info("userEntity found: " + userEntity.orElse(null));
        return userEntity.map(this.userMapper::toUserDTO);
    }

    @Transactional
    public UserDTO saveUser(UserDTO user) {
        log.info("invoked UserService.saveUser for user: " + user);
        UserEntity userEntity = this.userMapper.toUserEntity(user);
        log.info("userEntity to save: " + userEntity);
        userEntity = userRepository.save(userEntity);
        log.info("userEntity saved: " + userEntity);
        return this.userMapper.toUserDTO(userEntity);
    }

    @Transactional
    public boolean deleteUser(Integer id) {
        log.info("invoked UserService.deleteUser for id: " + id);
        try {
            userRepository.deleteById(id);
            log.info("userEntity deleted for id: " + id);
            return true;
        } catch (IllegalArgumentException e) {
            log.info("userEntity not found for id: " + id);
            return false;
        }
    }

}
