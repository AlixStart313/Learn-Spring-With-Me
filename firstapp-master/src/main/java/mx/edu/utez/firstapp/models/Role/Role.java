package mx.edu.utez.firstapp.models.Role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.User.User;

import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name= "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 15)
    private String name;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1" )
    private Boolean status;


    @ManyToMany
    @JoinTable(
            name ="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @JsonIgnore()

    private Set<User> users;
}
