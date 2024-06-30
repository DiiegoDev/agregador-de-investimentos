package tech.project.agregadordeinvestimentos.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.project.agregadordeinvestimentos.dtos.CreateUserDTO;
import tech.project.agregadordeinvestimentos.entities.User;
import tech.project.agregadordeinvestimentos.repositories.UserRespository;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  UserRespository userRespository;

  @InjectMocks
  UserService userService;

  @Test
  @DisplayName("Should return a user id when a user is successfully created")
  void shouldReturnUserId() {
    //Arrange
    var id = UUID.randomUUID();

    var user = new User(
      id,
      "Diego",
      "diego@dev.com",
      "password",
      Instant.now(),
      null
    );

    Mockito.doReturn(user).when(userRespository).save(any());

    var input = new CreateUserDTO(
      "Diego",
      "diego@dev.com",
      "password"
    );

    //Act
    var output = userService.createUser(input);

    //Assert
    assertNotNull(output);
    assertEquals(id, output);
  }

}