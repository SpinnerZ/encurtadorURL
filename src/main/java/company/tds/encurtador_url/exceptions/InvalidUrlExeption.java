package company.tds.encurtador_url.exceptions;

public class InvalidUrlExeption extends RuntimeException{

  public InvalidUrlExeption(String message) {
    super("The Link '" + message + "' is not a valid link");
  }
}
