package Project.TwitterWebApp.service;


import Project.TwitterWebApp.model.dto.UserDto;
import Project.TwitterWebApp.model.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    UserDto getUserByUserName(String userName);

    List<UserDto> getUserByFirstName(String firstName);

    List<UserDto> getUserByLastName(String lastName);

    void registerUser(UserRegisterDto user) ;

    void deleteUser(Integer userId);
}
