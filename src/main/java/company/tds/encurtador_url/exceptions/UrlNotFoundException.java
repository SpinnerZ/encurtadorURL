package company.tds.encurtador_url.exceptions;

public class UrlNotFoundException extends RuntimeException {

  public UrlNotFoundException(String message) {
    super("Não foi encontrada uma URL equivalente à URL encurtada '" + message + "'.");
  }
}
