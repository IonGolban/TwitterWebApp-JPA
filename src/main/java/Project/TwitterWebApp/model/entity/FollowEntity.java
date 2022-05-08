package Project.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@Table(name = "T_FOLLOW")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FollowEntity {

    @EmbeddedId
    private FollowId id ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID",updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FOLLOWING_USER_ID",updatable = false,insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity followingUser;

    @Column(name = "TIME")
    private Timestamp time ;

}
