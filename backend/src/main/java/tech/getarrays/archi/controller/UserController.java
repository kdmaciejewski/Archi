package tech.getarrays.archi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.archi.exception.UserNotFoundException;
import tech.getarrays.archi.model.User;
import tech.getarrays.archi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllEmployees() {
    List<User> users = userService.findaAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws Throwable {
    try {
      User user = userService.findUserById(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<User> addUser(@RequestBody User user) {
    User newUser = userService.addUser(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
