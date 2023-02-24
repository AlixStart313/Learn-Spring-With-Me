package mx.edu.utez.firstapp.security.controller;

import mx.edu.utez.firstapp.security.jwt.JwtProvider;
import mx.edu.utez.firstapp.security.model.UserDetail;
import mx.edu.utez.firstapp.security.dto.LoginDTO;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-market/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtProvider provider;

    //un mapa es como un directorio o diccionario en Json
    @PostMapping("/login")
    public ResponseEntity<CustomResponse<Map<String,Object>>> login(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authentication =manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernName(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=provider.generateToken(authentication);
        UserDetail userDetail=(UserDetail) authentication.getPrincipal();

        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("user",userDetail);
        return new ResponseEntity<>(
                new CustomResponse<>(
                        data,false,200,"OK"
                ), HttpStatus.OK
        );
    }

}