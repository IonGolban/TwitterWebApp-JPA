package Project.TwitterWebApp.repos.util.converter;

import Project.TwitterWebApp.model.dto.MentionDto;
import Project.TwitterWebApp.model.entity.MentionEntity;

import java.util.Optional;

public class MentionConverter {

    public static MentionDto toMentionDto(MentionEntity mentionEntity){
        return MentionDto.builder()
                .id(mentionEntity.getId())
                .userDto(Optional.ofNullable(mentionEntity.getUserEntity())
                        .map(UserConverter::toUserDto)
                        .orElse(null))
                .postDto(Optional.ofNullable(mentionEntity.getPostEntity())
                        .map(PostConverter2::toPostDto)
                        .orElse(null))
                .build();

    }
}
