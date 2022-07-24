package savingsGoal.roundup.model;

import lombok.Data;

import java.util.List;

/**
 * FeedItems class represent the list of all transaction feeds that are fetched.
 */
@Data
public class FeedItems {
        private List<FeedItem> feedItems;
}
