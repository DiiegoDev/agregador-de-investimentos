package tech.project.agregadordeinvestimentos.services;

import org.springframework.stereotype.Service;
import tech.project.agregadordeinvestimentos.dtos.CreateUserDTO;
import tech.project.agregadordeinvestimentos.dtos.UpdateUserDTO;
import tech.project.agregadordeinvestimentos.entities.User;
import tech.project.agregadordeinvestimentos.exceptions.user.EmailAlreadyExistsException;
import tech.project.agregadordeinvestimentos.exceptions.user.UserNotFoundException;
import tech.project.agregadordeinvestimentos.repositories.UserRespository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  private final UserRespository userRespository;

  public UserService(UserRespository userRespository) {
    this.userRespository = userRespository;
  }

  public UUID createUser(CreateUserDTO data) {

    Optional<User> userEmail = userRespository.findUserByEmail(data.email());

    if(userEmail.isPresent()) {
      throw new EmailAlreadyExistsException();
    }

    var user = new User(
      UUID.randomUUID(),
      data.username(),
            data.email(),
      data.password(),
      Instant.now(),
      null
    );

    var createdUser = userRespository.save(user);

    return createdUser.getId();
  }

  public List<User> findAll() {
    return userRespository.findAll();
  }

  public User findOneById(UUID id) {
    return userRespository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  public void updateOneById(UUID id, UpdateUserDTO data) {
    User user = userRespository.findById(id).orElseThrow(UserNotFoundException::new);

      if(data.username() != null) {
        user.setUsername(data.username());
      }

      if(data.password() != null) {
        user.setPassword(data.password());
      }

      userRespository.save(user);

  }

  public void deleteOneById(UUID id) {
    userRespository.findById(id).orElseThrow(UserNotFoundException::new);

    userRespository.deleteById(id);
  }
}
