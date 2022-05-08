package Project.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_MENTION")
public class MentionEntity {


    @Id
    @Column(name = "ID",insertable = false ,updatable = false)
    @SequenceGenerator(name = "mentionSeq",sequenceName= "t_mention_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "mentionSeq",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "USER_ID",updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @JoinColumn(name = "POST_ID",updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostEntity postEntity;

}
