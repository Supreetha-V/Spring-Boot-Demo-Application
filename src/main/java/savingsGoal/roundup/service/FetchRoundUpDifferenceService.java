package savingsGoal.roundup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.model.FeedItem;
import savingsGoal.roundup.model.FeedItems;
import savingsGoal.roundup.util.CommonUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static savingsGoal.roundup.constants.Constants.*;

/**
 * This class gets all the transaction feed from per account from a specified start date and calculated the round-up
 * difference. The round up difference is calculated for a week since the start date.
 */
@Service
public class FetchRoundUpDifferenceService {
    private static final Logger logger = LoggerFactory.getLogger(FetchRoundUpDifferenceService.class);

    private RestClientService restClientService;
    private CommonUtils utils;

    @Autowired
    public FetchRoundUpDifferenceService(RestClientService restClientService, CommonUtils utils) {
        this.restClientService = restClientService;
        this.utils = utils;
    }

    /**
     * Method to fetch the transaction feed per account from a given start date and calculate the round up difference.
     *
     * @param accountsList list of all accounts
     * @param startDate    date from which the transaction feed are considered
     * @param token        authentication token
     * @return map of account details and the total round-up difference
     */
    public Map<AccountV2, Integer> getTotalRoundUpAmount(List<AccountV2> accountsList, String startDate, String token) {
        utils.validateDate(startDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dt = LocalDateTime.parse(startDate, formatter);
        dt = dt.plusWeeks(1);
        String endDate = ZonedDateTime.of(dt, ZoneId.of("Z")).toString();
        logger.info("Fetch transaction feed from date: " + startDate + " to date: " + endDate);

        Map<AccountV2, Integer> accountRoundUpDiff = new HashMap<>();
        for (AccountV2 accountV2 : accountsList) {
            String accountFeed = String.format(FEEDITEMS_GET, accountV2.getAccountUid(),
                    startDate, endDate);
            String response = restClientService.get(HOSTNAME + accountFeed, token);
            FeedItems feedItemList = utils.readObject(response, FeedItems.class);
            accountRoundUpDiff.put(accountV2, getRoundUpAmountPerAccount(feedItemList.getFeedItems()));
            logger.info("Total roundup amount for account: " + accountV2.getAccountUid() +
                    " amount: " + accountRoundUpDiff.get(accountV2));
        }
        return accountRoundUpDiff;
    }

    /**
     * Method to calculate the total round-up difference for a list of transaction feed.
     *
     * @param feedItemList list of transaction feed
     * @return total round-up diffeence
     */
    private Integer getRoundUpAmountPerAccount(List<FeedItem> feedItemList) {
        Integer roundDiff = 0;
        for (FeedItem feedItem : feedItemList) {
            Integer amount = feedItem.getAmount().getMinorUnits();
            roundDiff += 100 - (amount % 100);
        }
        return roundDiff;
    }

}
