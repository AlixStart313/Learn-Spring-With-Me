package mx.edu.utez.firstapp.models.Person;

import lombok.*;
import mx.edu.utez.firstapp.models.User.User;

import javax.persistence.*;
@Entity
@Table(name="people")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 15)
    private String name;
    @Column(nullable = false, length = 15)
    private String surname;
    @Column(nullable = false, length = 15)
    private String lastname;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String birthday;
    @Column(nullable = false, length = 15)
    private String curp;
    @Column(nullable = false, length = 15)
    private String genero;
    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1")
    private Boolean status;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL )
    @PrimaryKeyJoinColumn
    private User user;



}
