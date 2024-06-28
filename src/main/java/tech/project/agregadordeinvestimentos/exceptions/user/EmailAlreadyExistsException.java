package tech.project.agregadordeinvestimentos.exceptions.user;

public class EmailAlreadyExistsException extends RuntimeException {
  public EmailAlreadyExistsException() {
    super("E-mail já cadastrado");
  }
}
