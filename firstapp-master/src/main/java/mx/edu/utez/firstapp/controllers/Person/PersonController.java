package mx.edu.utez.firstapp.controllers.Person;

import mx.edu.utez.firstapp.controllers.Category.dtos.CategoryDTO;
import mx.edu.utez.firstapp.controllers.Person.dtos.PersonDTO;
import mx.edu.utez.firstapp.models.Person.Person;
import mx.edu.utez.firstapp.models.Person.PersonRepository;
import mx.edu.utez.firstapp.services.Person.PersonService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-firstapp/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired //instanciamos de otras clases
    private PersonService service;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Person>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }


    @PostMapping("/")
    public ResponseEntity<CustomResponse<Person>> SavePerson(@RequestBody PersonDTO personas, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.savePerson(personas.getPerson()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(
            @RequestBody PersonDTO person
    ){
        return  new ResponseEntity<>(
                this.service.changeStatus(person.changeStatus()),
                HttpStatus.OK
        );
    }



}
