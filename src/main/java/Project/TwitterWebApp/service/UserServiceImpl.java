package Project.TwitterWebApp.service;

import Project.TwitterWebApp.advice.exception.NotUniqueException;
import Project.TwitterWebApp.model.dto.UserDto;
import Project.TwitterWebApp.model.dto.UserRegisterDto;
import Project.TwitterWebApp.repos.UserRepository;
import Project.TwitterWebApp.repos.util.converter.UserConverter;
import Project.TwitterWebApp.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service

public class UserServiceImpl  implements UserService  {

//    @Autowired
//    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepository;

    private UserConverter userConverter;
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {

        return UserConverter.toUserDto(userRepository.findById(id).get());
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return UserConverter.toUserDto(userRepository.findByUserName(userName).get());
    }

    @Override
    public List<UserDto> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName).stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void registerUser(UserRegisterDto user){

        if(userRepository.findByUserName(user.getUserName()).isPresent())throw new NotUniqueException("Username is already used");

        UserEntity userEntity = UserEntity.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        userRepository.save(userEntity);
    }




    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


}
