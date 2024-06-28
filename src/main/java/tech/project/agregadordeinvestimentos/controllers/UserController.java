package tech.project.agregadordeinvestimentos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.project.agregadordeinvestimentos.dtos.CreateUserDTO;
import tech.project.agregadordeinvestimentos.dtos.UpdateUserDTO;
import tech.project.agregadordeinvestimentos.entities.User;
import tech.project.agregadordeinvestimentos.services.UserService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CreateUserDTO user) {

    var userId = userService.createUser(user);

    return ResponseEntity.created(URI.create("users/" + userId)).build();
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findOneById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(userService.findOneById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateOneById(@PathVariable("id") UUID id,
                                            @RequestBody UpdateUserDTO data) {

    userService.updateOneById(id, data);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOneById(@PathVariable("id") UUID id) {
    userService.deleteOneById(id);
    return ResponseEntity.noContent().build();
  }
}
