package tech.project.agregadordeinvestimentos.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.project.agregadordeinvestimentos.dtos.CreateUserDTO;
import tech.project.agregadordeinvestimentos.entities.User;
import tech.project.agregadordeinvestimentos.exceptions.user.EmailAlreadyExistsException;
import tech.project.agregadordeinvestimentos.exceptions.user.UserNotFoundException;
import tech.project.agregadordeinvestimentos.repositories.UserRespository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  UserRespository userRespository;

  @InjectMocks
  UserService userService;

  @Captor
  private ArgumentCaptor<User> userArgumentCaptor;

  @Captor
  private ArgumentCaptor<UUID> userIdArgumentCaptor;


  @Nested
  class CreateUser {
    @Test
    @DisplayName("Should return a userId when a user is successfully created")
    void shouldCreateAUserAndReturnUserId() {
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


      var input = new CreateUserDTO(
              "Diego",
              "diego@dev.com",
              "password"
      );

      Mockito.doReturn(user).when(userRespository).save(userArgumentCaptor.capture());
      //Act
      var output = userService.createUser(input);
      var userCaptured = userArgumentCaptor.getValue();

      //Assert
      assertNotNull(output);
      assertEquals(id, output);
      assertEquals(input.email(), userCaptured.getEmail());
      assertEquals(input.username(), userCaptured.getUsername());
      assertEquals(input.password(), userCaptured.getPassword());
    }
  }

  @Nested
  class FoundByEmail {
    @Test
    @DisplayName("Should throw an error if email already exists")
    public void shouldThrowAnError() {
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

      Mockito.doReturn(Optional.of(user)).when(userRespository).findUserByEmail("diego@dev.com");

      var input = new CreateUserDTO(
              "Diego",
              "diego@dev.com",
              "password"
      );

      //Assert
      assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(input));

    }
  }

  @Nested
  class FindUserById {
    @Test
    @DisplayName("should return a user when userId Exists")
    void shouldReturnAUser() {
      //arrange
      var id = UUID.randomUUID();

      var user = new User(
              id,
              "Diego",
              "diego@dev.com",
              "password",
              Instant.now(),
              null
      );

      Mockito.doReturn(Optional.of(user)).when(userRespository).findById(userIdArgumentCaptor.capture());

      //act
      var output = userRespository.findById(user.getId());
      var userIdCaptured = userIdArgumentCaptor.getValue();

      //assert
      assertTrue(output.isPresent());
      assertEquals(user.getId(), userIdCaptured);
    }

    @Test
    @DisplayName("should throw an error when userId does not exist")
    void shouldThrowAnError() {
      //arrange
      var id = UUID.randomUUID();

      Mockito.doReturn(Optional.empty()).when(userRespository).findById(userIdArgumentCaptor.capture());

      //act
      var output = userRespository.findById(id);

      //assert
      assertThrows(UserNotFoundException.class, () -> userService.findOneById(id));
      assertTrue(output.isEmpty());
    }
  }
}