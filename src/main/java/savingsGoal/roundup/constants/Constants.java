package savingsGoal.roundup.constants;

/**
 * Lists all the constants used in the application
 */
public class Constants {
    public static final String HOSTNAME = "https://api-sandbox.starlingbank.com";
    public static final String ACCOUNTS_GET = "/api/v2/accounts";
    public static final String FEEDITEMS_GET = "/api/v2/feed/account/%s/settled-transactions-between" +
            "?minTransactionTimestamp=%s&maxTransactionTimestamp=%s";
    public static final String CREATE_SAVINGS_GOAL_POST = "/savings-goal/create/{start-date}";
    public static final String CREATE_SAVINGS_GOAL_PUT = "/api/v2/account/%s/savings-goals";
    public static final String ADD_MONEY_PUT = "/api/v2/account/%s/savings-goals/%s/add-money/%s";
}
