package tech.project.agregadordeinvestimentos.exceptions.user;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("Usuário não encontrado");
  }
}
