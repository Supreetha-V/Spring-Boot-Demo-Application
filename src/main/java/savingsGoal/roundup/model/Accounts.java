package savingsGoal.roundup.model;

import lombok.Data;

import java.util.List;

/**
 * Accounts class represent the list of all accounts fetched.
 */
@Data
public class Accounts {
    private List<AccountV2> accounts;
}
