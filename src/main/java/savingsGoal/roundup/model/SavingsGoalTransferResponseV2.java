package savingsGoal.roundup.model;

import lombok.Data;

/**
 * SavingsGoalTransferResponseV2 represents the response obtained on successful top up of money to the savings goal.
 */
@Data
public class SavingsGoalTransferResponseV2 {
    private String transferUid;
    private boolean success;
    private ErrorDetail[] errors;
}
