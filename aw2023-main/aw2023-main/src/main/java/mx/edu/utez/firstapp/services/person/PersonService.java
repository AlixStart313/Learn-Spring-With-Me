package mx.edu.utez.firstapp.services.person;

import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.person.PersonRepository;
import mx.edu.utez.firstapp.models.user.User;
import mx.edu.utez.firstapp.models.user.UserRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public CustomResponse<List<Person>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> insert(Person person) {
        Optional<Person> exists = this.repository.findByCurp(person.getCurp());
        if (exists.isPresent()) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "La persona ya se encuentra registrada"
            );
        }
        User UserExists = this.userRepository.findOneByUsername(person.getUser().getUsername());
        if (UserExists!= null){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El usuario ya se encuentra registrado"
            );
        }
        return new
                CustomResponse<>
                (this.repository.saveAndFlush(person),
                        false,
                        200,
                        "Person registered correctly!"); // saveAndFlush() is used to save the entity and flush changes instantly
    }
}







