package mk.ukim.finki.fooddeliverybackend.web.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.fooddeliverybackend.dto.security.JwtExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class JwtExceptionHandler {

    private ResponseEntity<JwtExceptionResponseDto> buildJwtExceptionResponse(
            HttpStatus status,
            String message,
            String path
    ) {
        return new ResponseEntity<>(
                new JwtExceptionResponseDto(
                        status.value(),
                        status.getReasonPhrase(),
                        message,
                        path
                ),
                status
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleExpiredJwtException(
            ExpiredJwtException expiredJwtException,
            HttpServletRequest request
    ) {
        return buildJwtExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "The token has expired.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleSignatureException(
            SignatureException exception,
            HttpServletRequest request
    ) {
        return buildJwtExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "The token's signature is invalid.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<JwtExceptionResponseDto> handleJwtException(
            JwtException exception,
            HttpServletRequest request
    ) {
        return buildJwtExceptionResponse(
                HttpStatus.UNAUTHORIZED,
                "The token is invalid.",
                request.getRequestURI()
        );
    }

}
