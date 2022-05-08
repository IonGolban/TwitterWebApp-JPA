package Project.TwitterWebApp.repos.util.converter;

import Project.TwitterWebApp.model.dto.LikeDto;
import Project.TwitterWebApp.model.entity.LikeEntity;

public class LikeConverter {
    public static LikeDto toLikeDto(LikeEntity likeEntity){
        return LikeDto.builder()
                .user(UserConverter.toUserDto(likeEntity.getUserEntity()))
                .time(likeEntity.getTime())
                .build();
    }
}
