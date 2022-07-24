package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * SavingsGoalRequestV2 represents the request body used to create the savings goal.
 */
@Builder
@Data
public class SavingsGoalRequestV2 {
    private String name;
    private String currency;
}
