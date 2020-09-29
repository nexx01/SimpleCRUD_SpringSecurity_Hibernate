package web.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true,
            nullable = false, length = 45)
    private String email; // уникальное значение


    @Column(name = "password",
            nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

//    @JsonIgnore //Чтобы не было зацикливания при получении JSON
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
    private byte age;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String email, String password, boolean enabled, String firstName, String lastName, byte age) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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
        return email;
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
