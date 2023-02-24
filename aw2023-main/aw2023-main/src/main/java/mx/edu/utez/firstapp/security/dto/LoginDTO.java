package mx.edu.utez.firstapp.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @NotEmpty
    private String usernName;

    @NotEmpty
    private String password;
}
