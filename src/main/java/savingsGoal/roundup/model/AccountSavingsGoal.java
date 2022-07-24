package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * AccountSavingsGoal is the data transfer object which represents the savings goal created for an account.
 */
@Builder
@Data
public class AccountSavingsGoal {
    private String accountId;
    private String savingGoalUid;
    private CurrencyAndAmount amount;
}
