package custom_exceptions;

public class NoDesktopException extends Exception {
    int id;

    public NoDesktopException() {}

    public NoDesktopException(int x) {
        id = x;
    }

    public String getMessage() {
        return "NoDesktopException: The desktop is not available.";
    }
}
