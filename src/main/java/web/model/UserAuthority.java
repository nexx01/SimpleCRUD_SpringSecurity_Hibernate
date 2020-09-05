package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Для того, чтобы в дальнейшим использовать класс User в Spring Security, он должен реализовывать интерфейс UserDetails.
// UserDetails можно представить, как адаптер между БД пользователей и тем что требуется Spring Security внутри SecurityContextHolder


@Entity
@Table
public class UserAuthority implements UserDetails {

    @Id
    @Column(name = "login", unique = true,
            nullable = false, length = 45)
    private String login; // уникальное значение


    @Column(name = "password",
            nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(/*fetch = FetchType.LAZY,*/fetch = FetchType.EAGER, mappedBy = "userAuthority")
    private Set<Role> role;

    @OneToOne(mappedBy = "userAuthority")
    private User user;

    public UserAuthority() { }

    public UserAuthority(String login, String password, boolean enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public UserAuthority(String login, String password, boolean enabled, Set<Role> role, User user) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
