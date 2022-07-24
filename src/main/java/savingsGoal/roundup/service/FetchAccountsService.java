package savingsGoal.roundup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.model.Accounts;
import savingsGoal.roundup.util.CommonUtils;

import java.util.List;

import static savingsGoal.roundup.constants.Constants.*;

/**
 * This class fetches all the accounts from the system.
 */
@Service
public class FetchAccountsService {
    private static final Logger logger = LoggerFactory.getLogger(FetchAccountsService.class);

    private RestClientService restClientService;
    private CommonUtils utils;

    /**
     * Constructor for the service.
     * @param restClientService
     * @param utils
     */
    @Autowired
    public FetchAccountsService(RestClientService restClientService, CommonUtils utils) {
        this.restClientService = restClientService;
        this.utils = utils;
    }

    /**
     * Method to fetch all the account details.
     * @param token
     * @return
     */
    public List<AccountV2> getAccounts(String token) {
        String response = restClientService.get(HOSTNAME + ACCOUNTS_GET, token);
        logger.info("Fetching account details: " + response);
        Accounts accounts = utils.readObject(response, Accounts.class);
        return accounts.getAccounts();
    }

}
