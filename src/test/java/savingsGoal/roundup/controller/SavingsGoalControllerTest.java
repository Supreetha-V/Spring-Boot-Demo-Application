package savingsGoal.roundup.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import savingsGoal.roundup.helper.SavingsGoalTestHelper;
import savingsGoal.roundup.model.AccountTopUpResponse;
import savingsGoal.roundup.service.FetchAccountsService;
import savingsGoal.roundup.service.FetchRoundUpDifferenceService;
import savingsGoal.roundup.service.SavingsGoalService;

import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SavingsGoalControllerTest {
    @InjectMocks
    private SavingsGoalController savingsGoalController;

    @Mock
    private FetchAccountsService fetchAccountsService;

    @Mock
    private FetchRoundUpDifferenceService fetchRoundUpDifferenceService;

    @Mock
    private SavingsGoalService savingsGoalService;

    private SavingsGoalTestHelper savingsGoalTestHelper = new SavingsGoalTestHelper();

    @Test
    public void testCreateSavingsGoal(){

        when(fetchAccountsService.getAccounts(anyString())).thenReturn(List.of(savingsGoalTestHelper.getAccountV2()));
        when(fetchRoundUpDifferenceService.getTotalRoundUpAmount(any(),any(),anyString())).thenReturn(new HashMap<>());
        when(savingsGoalService.createSavingsGoal(any(), anyString())).thenReturn(List.of());
        when(savingsGoalService.addToSavingsGoal(any(), anyString())).thenReturn(List.of());

        ResponseEntity<List<AccountTopUpResponse>> response = savingsGoalController.createSavingsGoal("test",
                "mockToken");
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
