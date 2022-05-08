package Project.TwitterWebApp.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentionDto {

    private Integer id;

    private UserDto userDto;

    private PostDto postDto;

}
