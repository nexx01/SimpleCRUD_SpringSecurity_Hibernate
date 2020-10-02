package web.controllers;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    //@Autowired
    public AdminRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        final List<User> users = (List<User>) userService.getAllUsers();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        //Проверка на уже существующего юзера
        User userFromDb = userService.findByEmail(user.getEmail());
        if (userFromDb != null) {
            return new ResponseEntity<>("User with this given Email exists",HttpStatus.CONFLICT);
        }

        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {

        User user = userService.findUserbyId(id); //
           //     .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(user, //
                linkTo(methodOn(AdminRestController.class).one(id)).withSelfRel(),
                linkTo(methodOn(AdminRestController.class).getAllUsers()).withRel("users"));
    }



//   @GetMapping("/users/{id}")
//        public ResponseEntity<User> getOneUser(@PathVariable(name="id") long id){
//        final User user = userService.findUserbyId(id);
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
/*Spring Data за нас

Находит в шаблоне адреса параметр person
Определяет, что помеченный аннотацией @PathVariable атрибут person имеет тип Person
Ищет репозиторий, работающий с типом Person
У найденного репозитория вызывает метод findOne с запрашиваемым идентификатором
Передаёт результат в атрибут person
*/

    //using SpringData
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> get(@PathVariable User user) {
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> update( @RequestBody User user)  {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //using SpringData
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
