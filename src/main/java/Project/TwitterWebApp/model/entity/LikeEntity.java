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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_LIKE")
public class LikeEntity {


    @Id
    @Column(name = "ID",insertable = false ,updatable = false)
    @SequenceGenerator(name = "likeSeq",sequenceName= "t_like_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "likeSeq",strategy = GenerationType.SEQUENCE)
    private Integer id ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID",updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POST_ID",updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostEntity postEntity;

    @Column(name = "TIME")
    private Timestamp time;

}
