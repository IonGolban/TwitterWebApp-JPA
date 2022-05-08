package Project.TwitterWebApp.repos.util.converter;

import Project.TwitterWebApp.model.dto.PostDto;
import Project.TwitterWebApp.model.entity.PostEntity;

import java.util.Optional;

public class PostConverter2 {

        public static PostDto toPostDto(PostEntity postEntity){
            return PostDto.builder()
                    .id(postEntity.getId())
                    .user(Optional.ofNullable(postEntity.getUserEntity())
                        .map(UserConverter::toUserDto)
                        .orElse(null))
                    .message(postEntity.getMessage())
                    .time(postEntity.getTime())
                    .build();
        }
}
