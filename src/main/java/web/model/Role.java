package web.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.
@Component
@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    public static final String FIND_BY_NAME = "Role.findByName";
    public static final String FIND_BY_ID = "Role.findByID";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", unique = true,
            nullable = false, length = 45)
    private Long id;

    @Column(name = "role_name", unique = true, nullable = false, length = 45)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet();

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
