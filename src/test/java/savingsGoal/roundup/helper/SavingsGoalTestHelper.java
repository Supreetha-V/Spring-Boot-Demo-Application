package savingsGoal.roundup.helper;

import lombok.Data;
import savingsGoal.roundup.model.*;

import java.util.List;

@Data
public class SavingsGoalTestHelper {
    public static final String MOCK_ACCOUNTID = "mock-ce7a-4707-a050-96a690b336dd";
    public static final String MOCK_DEFAULT_CATEGORY = "mock-9dbb-4bcd-95c9-748601cdf05c";
    public static final String MOCK_ACCOUNT_TYPE = "PRIMARY";
    public static final String MOCK_START_DATE = "2022-07-17T17:19:40.070Z";
    public static final String MOCK_SAVINGS_GOALID = "mock-75b7-4043-9fad-f5f71729b575";
    public static final String MOCK_TRANSFER_UID = "mock-0506-4eb4-9b7f-ecff8bafcac0";

    public AccountV2 getAccountV2() {
        AccountV2 account = new AccountV2();
        account.setAccountUid(MOCK_ACCOUNTID);
        account.setAccountType(MOCK_ACCOUNT_TYPE);
        account.setDefaultCategory(MOCK_DEFAULT_CATEGORY);
        account.setCurrency("GBP");
        account.setCreatedAt(MOCK_START_DATE);
        account.setName("Personal");
        return account;
    }

    public Accounts getAccounts() {
        Accounts accounts = new Accounts();
        accounts.setAccounts(List.of(getAccountV2()));
        return accounts;
    }

    public FeedItem getFeedItem() {
        FeedItem feedItem = new FeedItem();
        feedItem.setFeedItemUid("12345");
        feedItem.setCategoryUid(MOCK_DEFAULT_CATEGORY);
        feedItem.setAmount(CurrencyAndAmount.builder().currency("GBP").minorUnits(253).build());
        return feedItem;
    }

    public FeedItems getFeedItems() {
        FeedItems feedItems = new FeedItems();
        feedItems.setFeedItems(List.of(getFeedItem()));
        return feedItems;
    }

    public SavingsGoal getSavingsGoal() {
        SavingsGoal savingsGoal = new SavingsGoal();
        savingsGoal.setSavingsGoalUid(MOCK_SAVINGS_GOALID);
        savingsGoal.setSuccess(true);
        return savingsGoal;
    }

    public SavingsGoalTransferResponseV2 getSvingsGoalTransferResponseV2() {
        SavingsGoalTransferResponseV2 savingsGoalTransferResponseV2 = new SavingsGoalTransferResponseV2();
        savingsGoalTransferResponseV2.setTransferUid(MOCK_TRANSFER_UID);
        savingsGoalTransferResponseV2.setSuccess(true);
        return savingsGoalTransferResponseV2;
    }

    public AccountSavingsGoal getAccountSavingsGoal() {
        return AccountSavingsGoal.builder().accountId(MOCK_ACCOUNTID).savingGoalUid(MOCK_SAVINGS_GOALID)
                .amount(CurrencyAndAmount.builder().currency("GBP").minorUnits(114).build()).build();
    }

}
