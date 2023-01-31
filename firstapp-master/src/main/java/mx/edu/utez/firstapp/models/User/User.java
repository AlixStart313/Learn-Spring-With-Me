package mx.edu.utez.firstapp.models.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.Person.Person;
import mx.edu.utez.firstapp.models.Role.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String username;
    @Column(nullable = false, length = 20)
        private String password;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private String lastAccess;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean blocked;
    @Column(nullable = false, columnDefinition = "TEXT")

    private Boolean token;
@OneToOne
@MapsId
@JoinColumn(name="user_id",
        referencedColumnName = "id", unique = true)
@JsonIgnore
private Person person;
@ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
