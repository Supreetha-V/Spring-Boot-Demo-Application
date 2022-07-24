package savingsGoal.roundup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * AccountV2 class is the data transfer object which represents a single account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AccountV2 {
    private String accountUid;
    private String accountType;
    private  String defaultCategory;
    private String currency;
    private String createdAt;
    private String name;
}
