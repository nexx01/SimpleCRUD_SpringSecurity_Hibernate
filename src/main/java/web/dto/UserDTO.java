package web.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.model.Role;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private Collection<Role> roles;
    private String firstName;
    private String lastName;
    private byte age;
}
