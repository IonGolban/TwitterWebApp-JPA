package Project.TwitterWebApp.controller;

import Project.TwitterWebApp.model.dto.UserRegisterDto;
import Project.TwitterWebApp.service.UserService;
import Project.TwitterWebApp.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "",  produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/search/username/{userName}", produces = "application/json")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    @GetMapping(value = "/search/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUserByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userService.getUserByLastName(lastName),HttpStatus.OK);
    }

    @GetMapping(value = "/search/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUserByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.getUserByFirstName(firstName),HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public void addUser(@RequestBody UserRegisterDto user ) {
         userService.registerUser(user);
    }

    @DeleteMapping(value = "/{userId}/delete",produces = "application/json")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }

}
