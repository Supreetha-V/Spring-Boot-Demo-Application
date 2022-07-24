package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * This class represents the generic error response which is used in the GlobalExceptionHandler.
 */
@Builder
@Data
public class ErrorResponse {
    private String errorCode;
    private int status;
    private String message;
}
