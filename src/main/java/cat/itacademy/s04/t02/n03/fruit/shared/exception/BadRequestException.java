package cat.itacademy.s04.t02.n03.fruit.shared.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
