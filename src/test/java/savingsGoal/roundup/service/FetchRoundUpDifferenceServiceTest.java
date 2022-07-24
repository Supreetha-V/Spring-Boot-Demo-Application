package savingsGoal.roundup.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import savingsGoal.roundup.helper.SavingsGoalTestHelper;
import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.util.CommonUtils;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static savingsGoal.roundup.helper.SavingsGoalTestHelper.MOCK_START_DATE;

@ExtendWith(MockitoExtension.class)
public class FetchRoundUpDifferenceServiceTest {
    @InjectMocks
    private FetchRoundUpDifferenceService fetchRoundUpDifferenceService;

    @Mock
    private RestClientService restClientService;

    @Mock
    private CommonUtils utils;

    private SavingsGoalTestHelper savingsGoalTestHelper = new SavingsGoalTestHelper();

    @Test
    public void testGetTotalRoundUpAmount() {
        when(restClientService.get(anyString(),anyString())).thenReturn("mockResponse");
        when(utils.readObject(anyString(), any())).thenReturn(savingsGoalTestHelper.getFeedItems());

        Map<AccountV2, Integer> mockAccountRoundUpDiff =
                fetchRoundUpDifferenceService.getTotalRoundUpAmount(List.of(savingsGoalTestHelper.getAccountV2()),
                        MOCK_START_DATE,"mockToken");
        Assertions.assertNotNull(mockAccountRoundUpDiff);
        Assertions.assertEquals(47, mockAccountRoundUpDiff.get(savingsGoalTestHelper.getAccountV2()));

    }

}
