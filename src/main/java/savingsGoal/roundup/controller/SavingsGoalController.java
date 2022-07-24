package savingsGoal.roundup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import savingsGoal.roundup.model.AccountSavingsGoal;
import savingsGoal.roundup.model.AccountTopUpResponse;

import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.service.FetchAccountsService;
import savingsGoal.roundup.service.FetchRoundUpDifferenceService;
import savingsGoal.roundup.service.SavingsGoalService;

import java.util.List;
import java.util.Map;

import static savingsGoal.roundup.constants.Constants.*;

/**
 *
 * This is the main class which calls the relevant functionalites/classes to fetch the account, get the transaction feed
 * for the accounts, create savings goal and topup the savings goal. This class provides endpoint to initiate roundup
 * feature.
 * @author Supreetha
 *
 */
@RestController
public class SavingsGoalController {
    private FetchAccountsService fetchAccountsService;
    private FetchRoundUpDifferenceService fetchRoundUpDifferenceService;
    private SavingsGoalService savingsGoalService;

    /**
     * Constructor to initialize the controller
     * @param fetchAccountsService: fetches all the accounts
     * @param fetchRoundUpDifferenceService : gives the round-up difference per account
     * @param savingsGoalService : creates the savings goal and adds round-up difference to the savings goal
     */
    @Autowired
    public SavingsGoalController(FetchAccountsService fetchAccountsService,
                                 FetchRoundUpDifferenceService fetchRoundUpDifferenceService,
                                 SavingsGoalService savingsGoalService) {
        this.fetchAccountsService = fetchAccountsService;
        this.fetchRoundUpDifferenceService = fetchRoundUpDifferenceService;
        this.savingsGoalService = savingsGoalService;
    }

    /**
     * This method does the following
     * a. fetches all the accounts
     * b. fetched transaction feed from the start date up until a week after the start date and calculates  the
     * round-up difference per account
     * c. creates a savings goal
     * d. adds the round-up difference to the savings goal
     *
     * @param startDate date since when the top-up difference is to be considered
     * @param token
     * @return
     */
    @PostMapping(value = CREATE_SAVINGS_GOAL_POST, produces = "application/json")
    public ResponseEntity<List<AccountTopUpResponse>> createSavingsGoal(
            @PathVariable("start-date") String startDate,
            @RequestHeader("token") String token
    ) {
        List<AccountV2> accountV2s = fetchAccountsService.getAccounts(token);
        Map<AccountV2, Integer> totalRoundUpDiff = fetchRoundUpDifferenceService.getTotalRoundUpAmount(accountV2s,
                startDate, token);
        List<AccountSavingsGoal> accountSavingsGoal = savingsGoalService.createSavingsGoal(totalRoundUpDiff, token);
        List<AccountTopUpResponse> accountTopUpResponseList = savingsGoalService.addToSavingsGoal(accountSavingsGoal,
                token);
        return new ResponseEntity<List<AccountTopUpResponse>>(accountTopUpResponseList, HttpStatus.OK);
    }
}
