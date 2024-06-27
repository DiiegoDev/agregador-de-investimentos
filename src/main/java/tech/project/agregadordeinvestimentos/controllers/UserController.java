package tech.project.agregadordeinvestimentos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.project.agregadordeinvestimentos.dtos.CreateUserDTO;
import tech.project.agregadordeinvestimentos.entities.User;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping
  public ResponseEntity<User> create(@RequestBody CreateUserDTO user) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findOneById(@PathVariable("id") UUID id) {
    return null;
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateOneById(@PathVariable("id") UUID id) {
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOneById(@PathVariable("id") UUID id) {
    return null;
  }
}
