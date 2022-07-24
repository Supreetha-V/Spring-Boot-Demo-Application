package savingsGoal.roundup.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import savingsGoal.roundup.helper.SavingsGoalTestHelper;
import savingsGoal.roundup.model.AccountSavingsGoal;
import savingsGoal.roundup.model.AccountTopUpResponse;
import savingsGoal.roundup.model.AccountV2;
import savingsGoal.roundup.util.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static savingsGoal.roundup.helper.SavingsGoalTestHelper.*;

@ExtendWith(MockitoExtension.class)
public class SavingsGoalServiceTest {
    @InjectMocks
    private SavingsGoalService savingsGoalService;

    @Mock
    private RestClientService restClientService;

    @Mock
    private CommonUtils utils;

    private SavingsGoalTestHelper savingsGoalTestHelper = new SavingsGoalTestHelper();

    @Test
    public void testCreateSavingsGoal(){
        Map<AccountV2, Integer> mockAccountRoundUpDiff = new HashMap<>();
        mockAccountRoundUpDiff.put(savingsGoalTestHelper.getAccountV2(), 114);

        when(restClientService.put(anyString(), any(), anyString())).thenReturn("mockResponse");
        when(utils.readObject(anyString(),any())).thenReturn(savingsGoalTestHelper.getSavingsGoal());

        List<AccountSavingsGoal> mockSavingsGoalList = savingsGoalService.createSavingsGoal(mockAccountRoundUpDiff,
                "mockToken");
        Assertions.assertNotNull(mockSavingsGoalList);
        Assertions.assertEquals(savingsGoalTestHelper.getAccountV2().getAccountUid(),mockSavingsGoalList.get(0).getAccountId());
        Assertions.assertEquals(114,mockSavingsGoalList.get(0).getAmount().getMinorUnits());
    }

    @Test
    public void testAddToSavingsGoal(){
        Map<AccountV2, Integer> mockAccountRoundUpDiff = new HashMap<>();
        mockAccountRoundUpDiff.put(savingsGoalTestHelper.getAccountV2(), 114);

        when(restClientService.put(anyString(), any(), anyString())).thenReturn("mockResponse");
        when(utils.readObject(anyString(),any())).thenReturn(savingsGoalTestHelper.getSvingsGoalTransferResponseV2());

        List<AccountTopUpResponse> mockaccountTopUpResponseList =
                savingsGoalService.addToSavingsGoal(List.of(savingsGoalTestHelper.getAccountSavingsGoal()),
                        "mockToken");
        Assertions.assertNotNull(mockaccountTopUpResponseList);
        Assertions.assertEquals(1, mockaccountTopUpResponseList.size());
        Assertions.assertEquals(MOCK_ACCOUNTID, mockaccountTopUpResponseList.get(0).getAccountId());
        Assertions.assertEquals(MOCK_SAVINGS_GOALID, mockaccountTopUpResponseList.get(0).getSavingsGoalUid());
    }
}
