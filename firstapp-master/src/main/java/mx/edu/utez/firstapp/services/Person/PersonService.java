package mx.edu.utez.firstapp.services.Person;


import mx.edu.utez.firstapp.models.Person.Person;
import mx.edu.utez.firstapp.models.Person.PersonRepository;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //cacha errores y si funciona alguno entonces borra ese hasta que jale
public class PersonService {
    @Autowired
    PersonRepository repository;

    @Transactional(readOnly = true)//readOnly no se hace cache y se manda a la query
    public CustomResponse<List<Person>> getAll(){
        //la persistencia de datos es como un cache para todas las consultas de datos
    return new CustomResponse<>(
            this.repository.findAll(),
            false,
            200,
            "OK"
    );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> savePerson(Person person){
        Optional<Person> exists = this.repository.findByCurp(person.getCurp());
        if(exists.isPresent()){

            return new CustomResponse<>(null,
                    true,
                    400,
                    "no jalo rei");
        }
     return new CustomResponse<>(this.repository.saveAndFlush(person),
             false,
             200,
             "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> updatePerson(Person person) {
        if (this.repository.existsById(person.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "YA EXISTE"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(person),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Person person) {
        if (this.repository.existsById(person.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "NO SE BORRO"
            );
        }
        return new CustomResponse<>(
                this.repository.updateStatusById(person.getStatus(),person.getId()),
                false,
                200,
                "OK"
        );
    }

}
