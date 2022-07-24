package savingsGoal.roundup.model;

import lombok.Data;

/**
 * SavingsGoal is the response obtained on creation of a savings goal for an account.
 */
@Data
public class SavingsGoal {
    private String savingsGoalUid;
    private boolean success;
    private ErrorDetail[] errors;
}


