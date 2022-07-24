package savingsGoal.roundup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * FeedItem is the data transfer object which represents the transaction feed per account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeedItem {
    private String feedItemUid;
    private String categoryUid;
    private CurrencyAndAmount amount;
    private CurrencyAndAmount sourceAmount;
    private String updatedAt;
    private String transactionTime;
    private String status;

}
