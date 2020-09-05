package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.
@Entity
@Table(name="authorities",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "login" }))
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id",
            unique = true, nullable = false)
    private Long userRoleId;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @ManyToOne(/*fetch = FetchType.LAZY*/fetch = FetchType.EAGER)
    @JoinColumn(name = "login", nullable = false)
    private UserAuthority userAuthority;

    public Role() {    }

    public Role(String role, UserAuthority userAuthority) {
        this.role = role;
        this.userAuthority = userAuthority;
    }

    public Role(Long userRoleId, String role, UserAuthority userAuthority) {
        this.userRoleId = userRoleId;
        this.role = role;
        this.userAuthority = userAuthority;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
