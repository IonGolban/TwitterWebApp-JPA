package Project.TwitterWebApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_USER")
public class UserEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "userSeq" , sequenceName = "t_user_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "userSeq",strategy = GenerationType.SEQUENCE)
    private Integer id ;

    @Column(name = "USER_NAME")
    @NotBlank(message = "Username is mandatory")
    private String userName;

    @Column(name = "FIRST_NAME")
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @Column(name = "EMAIL")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "PASSWORD")
    @NotBlank(message = "password is mandatory")
    private String password;


}
