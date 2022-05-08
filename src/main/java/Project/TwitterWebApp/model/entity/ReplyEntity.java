package Project.TwitterWebApp.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_REPLY")
public class ReplyEntity
 {

     @Id
     @Column(name = "ID",insertable = false ,updatable = false)
     @SequenceGenerator(name = "replySeq",sequenceName= "t_reply_id_seq",allocationSize = 1)
     @GeneratedValue(generator = "replySeq",strategy = GenerationType.SEQUENCE)
     private Integer id ;

     @JoinColumn(name = "POST_ID")
     @ManyToOne(fetch = FetchType.EAGER)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private PostEntity post;

     @JoinColumn(name = "PARENT_POST_ID")
     @ManyToOne(fetch = FetchType.EAGER)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private PostEntity parentPost;

     @Column(name = "IS_PUBLIC")
     private boolean isPublic ;

 }
