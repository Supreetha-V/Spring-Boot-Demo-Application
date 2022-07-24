package savingsGoal.roundup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import savingsGoal.roundup.model.ErrorResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * This is a global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(Exception exception, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse =
                ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                        .errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(exception.getMessage()).build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception,
                                                             HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse =
                ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(exception.getMessage()).build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
