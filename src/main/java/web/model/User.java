package web.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "select s from User s"),
        @NamedQuery(name = User.FIND_USER_BY_ID, query =
                "select distinct s from User s where s.id = :id"),
        @NamedQuery(name = User.FIND_USER_BY_LOGIN, query =
                "select distinct s from User s where s.login=:login")
})

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_USER_BY_ID = "User.findByid";
    public static final String FIND_USER_BY_LOGIN = "User.findByLogin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true,
            nullable = false, length = 45)
    private String login; // уникальное значение


    @Column(name = "password",
            nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    //@ManyToMany(/*fetch = FetchType.LAZY,*/fetch = FetchType.EAGER, mappedBy = "userAuthority")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, boolean enabled, String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

/*    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }*/


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

}
