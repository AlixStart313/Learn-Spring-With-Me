package mx.edu.utez.firstapp.controllers.Person.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.Person.Person;
import mx.edu.utez.firstapp.models.User.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonDTO {

        private Long id;
        //Notación para especificar que el campo no puede venir vacio
        @NotBlank
        //Notación para indicar que el tamaño minimo debe ser 0
        @Min(0)
        private String name;
        private String surname;
        private String lastname;
        private String birthday;
        private String curp;
        private String genero;
        private Boolean status;
        private User user;


public Person getPerson(){
                return new Person(
                        getId(),
                        getName(),
                        getSurname(),
                        getLastname(),
                        getBirthday(),
                        getCurp(),
                        getGenero(),
                        getStatus(),
                        getUser()

                              );
}


        public Person changeStatus(){
                return new Person(
                        getId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        getStatus(),
                        null

                );
        }

}
