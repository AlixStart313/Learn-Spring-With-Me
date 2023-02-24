package mx.edu.utez.firstapp.services.User;


import mx.edu.utez.firstapp.models.user.User;
import mx.edu.utez.firstapp.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackOn = {SQLException.class})
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }


}
