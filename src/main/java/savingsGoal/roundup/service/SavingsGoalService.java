package savingsGoal.roundup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savingsGoal.roundup.model.AccountSavingsGoal;
import savingsGoal.roundup.model.AccountTopUpResponse;
import savingsGoal.roundup.model.*;
import savingsGoal.roundup.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static savingsGoal.roundup.constants.Constants.*;

/**
 * This class creates the savings goal per account and adds the calculated round-up difference amount to the created
 * savings goal.
 */
@Service
public class SavingsGoalService {
    private static final Logger logger = LoggerFactory.getLogger(SavingsGoalService.class);

    private RestClientService restClientService;
    private CommonUtils utils;

    @Autowired
    public SavingsGoalService(RestClientService restClientService, CommonUtils utils) {
        this.restClientService = restClientService;
        this.utils = utils;
    }

    /**
     * Method to create savings goal per account
     * @param accountRoundUpDiff map of accounts and total round-up difference
     * @param token authentication tokens
     * @return
     */
    public List<AccountSavingsGoal> createSavingsGoal(Map<AccountV2, Integer> accountRoundUpDiff, String token) {
        logger.info("creating savings goal for all accounts");
        List<AccountSavingsGoal> savingsGoalList = new ArrayList<>();
        for (Map.Entry<AccountV2, Integer> entry : accountRoundUpDiff.entrySet()) {
            String accountSavingsGoalUri = String.format(CREATE_SAVINGS_GOAL_PUT, entry.getKey().getAccountUid());
            SavingsGoalRequestV2 savingsGoalRequest =
                    SavingsGoalRequestV2.builder().name("Test-SavingGoal").currency(entry.getKey().getCurrency()).build();

            String response = restClientService.put(HOSTNAME + accountSavingsGoalUri, savingsGoalRequest, token);
            SavingsGoal savingsGoal = utils.readObject(response, SavingsGoal.class);

            savingsGoalList.add(AccountSavingsGoal.builder()
                    .accountId(entry.getKey().getAccountUid())
                    .savingGoalUid(savingsGoal.getSavingsGoalUid())
                    .amount(CurrencyAndAmount.builder()
                            .minorUnits(entry.getValue())
                            .currency(entry.getKey().getCurrency()).build())
                    .build());
        }
        return savingsGoalList;
    }

    /**
     * Method to add the calculated round-up difference to the savings goal per account.
     * @param SavingsGoalList  list of all savings goal per account
     * @param token authentication token
     * @return
     */
    public List<AccountTopUpResponse> addToSavingsGoal(List<AccountSavingsGoal> SavingsGoalList, String token) {
        logger.info("top up savings account");
        String uuid = "";
        List<AccountTopUpResponse> accountTopUpResponseList = new ArrayList<>();
        for (AccountSavingsGoal goal : SavingsGoalList) {
            uuid = UUID.randomUUID().toString();
            String topUpRequestUri = String.format(ADD_MONEY_PUT, goal.getAccountId(), goal.getSavingGoalUid(), uuid);
            TopUpRequestV2 topUpRequest =
                    TopUpRequestV2.builder().amount(goal.getAmount()).build();

            String response = restClientService.put(HOSTNAME + topUpRequestUri, topUpRequest, token);
            SavingsGoalTransferResponseV2 savingsGoalTransferResponse = utils.readObject(response,
                    SavingsGoalTransferResponseV2.class);

            accountTopUpResponseList.add(AccountTopUpResponse.builder().accountId(goal.getAccountId())
                    .savingsGoalUid(goal.getSavingGoalUid())
                    .transferUid(savingsGoalTransferResponse.getTransferUid())
                    .amount(goal.getAmount())
                    .build());
        }
        return accountTopUpResponseList;
    }
}
