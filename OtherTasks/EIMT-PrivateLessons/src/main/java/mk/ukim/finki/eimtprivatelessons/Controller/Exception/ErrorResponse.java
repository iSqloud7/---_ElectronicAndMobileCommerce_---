package mk.ukim.finki.eimtprivatelessons.Controller.Exception;

public class ErrorResponse {

    private int statusCode;
    private String errorMessage;


    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
