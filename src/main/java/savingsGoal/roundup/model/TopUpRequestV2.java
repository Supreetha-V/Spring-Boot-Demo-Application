package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * TopUpRequestV2 represents the amount which is to be added to the savings goal. It represents the request body of
 * the add money to savings goal endpoint.
 */
@Builder
@Data
public class TopUpRequestV2 {
    private CurrencyAndAmount amount;
}
