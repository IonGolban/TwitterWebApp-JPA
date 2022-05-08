package Project.TwitterWebApp.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class LikeDto {

    private UserDto user;

    private Timestamp time;

}
