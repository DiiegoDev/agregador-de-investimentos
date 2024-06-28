package tech.project.agregadordeinvestimentos.infra;

public class RestErrorMessage {
  private String message;
  private int StatusCode;


  public RestErrorMessage(String message, int statusCode) {
    this.message = message;
    StatusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatusCode() {
    return StatusCode;
  }

  public void setStatusCode(int statusCode) {
    StatusCode = statusCode;
  }
}
