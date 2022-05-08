package Project.TwitterWebApp.repos.util.converter;

import Project.TwitterWebApp.model.dto.UserDto;
import Project.TwitterWebApp.model.entity.UserEntity;

public class UserConverter {
    public static UserDto toUserDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
