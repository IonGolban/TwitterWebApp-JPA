package Project.TwitterWebApp.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name ="T_POST")
public class PostEntity {

    @Id
    @Column(name = "ID",insertable = false ,updatable = false)
    @SequenceGenerator(name = "postSeq",sequenceName= "t_post_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "postSeq",strategy = GenerationType.SEQUENCE)
    private Integer id ;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID",updatable = true)
    private UserEntity userEntity;

    @Column(name = "MESSAGE")
    private String message ;

    @Column(name = "TIME")
    private Timestamp time ;

    //private List<PostEntity2> replies ;

    PostEntity(UserEntity userEntity , String message, Timestamp time){
        this.userEntity = userEntity;
        this.message=message;
        this.time = time;
    }
}
