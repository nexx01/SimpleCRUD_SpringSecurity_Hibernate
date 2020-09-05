package web.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "select s from User s"),
        @NamedQuery(name = User.FIND_USER_BY_ID, query =
                "select distinct s from User s where s.id = :id"),
        @NamedQuery(name=User.FIND_USER_BY_LOGIN, query =
                "select distinct s.userAuthority from User s where s.userAuthority.login=:login")
})

public class User {

    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_USER_BY_ID = "User.findByid";
    public static final String FIND_USER_BY_LOGIN = "User.findByLogin";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "user_id")
    private UserAuthority userAuthority;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, UserAuthority userAuthority) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userAuthority = userAuthority;
    }

    public User(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
