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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static savingsGoal.roundup.helper.SavingsGoalTestHelper.MOCK_ACCOUNTID;

@ExtendWith(MockitoExtension.class)
public class FetchAccountsServiceTest {
    @InjectMocks
    private FetchAccountsService fetchAccountsService;

    @Mock
    private RestClientService restClientService;

    @Mock
    private CommonUtils utils;

    private SavingsGoalTestHelper savingsGoalTestHelper = new SavingsGoalTestHelper();

    @Test
    public void testGetAccounts(){
        when(restClientService.get(anyString(),anyString())).thenReturn("mockUri");
        when(utils.readObject(anyString(), any())).thenReturn(savingsGoalTestHelper.getAccounts());

        List<AccountV2> accounts = fetchAccountsService.getAccounts("mockToken");
        Assertions.assertNotNull(accounts);
        Assertions.assertEquals(1,accounts.size());
        Assertions.assertEquals(MOCK_ACCOUNTID,accounts.get(0).getAccountUid());
    }
}
