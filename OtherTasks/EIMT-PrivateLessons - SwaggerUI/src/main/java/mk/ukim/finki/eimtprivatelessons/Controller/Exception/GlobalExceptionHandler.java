package mk.ukim.finki.eimtprivatelessons.Controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleConflict(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(500);
        errorResponse.setErrorMessage("Error!");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        /*
        ErrorResponse errorResponse = new ErrorResponse();

        if (exception instanceof RuntimeException) {
            errorResponse.setStatusCode(404);
            errorResponse.setErrorMessage("Resource Not Found!");
        } else {
            errorResponse.setStatusCode(500);
            errorResponse.setErrorMessage("Internal Server Error!");
        }

        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
        */
    }
}
