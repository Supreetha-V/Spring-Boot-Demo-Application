package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * AccountTopUpResponse represents the response body of the top-up savings goal endpoint.
 */
@Builder
@Data
public class AccountTopUpResponse {
    private String accountId;
    private String savingsGoalUid;
    private String transferUid;
    private CurrencyAndAmount amount;
}
