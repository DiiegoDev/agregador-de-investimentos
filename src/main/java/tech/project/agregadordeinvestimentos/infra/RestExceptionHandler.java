package tech.project.agregadordeinvestimentos.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.project.agregadordeinvestimentos.exceptions.user.EmailAlreadyExistsException;
import tech.project.agregadordeinvestimentos.exceptions.user.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<RestErrorMessage> handleUserNotFoundException(UserNotFoundException exception) {

    RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<RestErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {

    RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
