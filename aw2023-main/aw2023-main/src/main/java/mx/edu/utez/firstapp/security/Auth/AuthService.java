package mx.edu.utez.firstapp.security.Auth;

import mx.edu.utez.firstapp.security.model.UserAuth;
import mx.edu.utez.firstapp.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserAuth.build(service.findByUsername(username));
    }


}
