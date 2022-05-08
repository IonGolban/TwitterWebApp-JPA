package Project.TwitterWebApp.model.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowId implements Serializable {

    @Basic
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "FOLLOWING_USER_ID", nullable = false)
    private Integer followingUserId;

}
