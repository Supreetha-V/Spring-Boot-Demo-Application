package savingsGoal.roundup.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import savingsGoal.roundup.model.AccountV2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommonUtilsTest {
    @InjectMocks
    private CommonUtils commonUtils;

    @Mock
    private ObjectMapper mapper;

    @Test
    public void testReadObjectPass() throws JsonProcessingException {
        AccountV2 mockAccount = new AccountV2();
        when(mapper.readValue(anyString(), (Class<AccountV2>) any())).thenReturn(mockAccount);

        Assertions.assertEquals(mockAccount, commonUtils.readObject("mockValue", AccountV2.class));
    }

    @Test
    public void testReadObjectFail() throws JsonProcessingException {
        AccountV2 mockAccount = new AccountV2();
        when(mapper.readValue(anyString(), (Class<AccountV2>) any())).thenThrow(JsonProcessingException.class);

        Assertions.assertThrows(RuntimeException.class, () -> commonUtils.readObject("mockValue", AccountV2.class));
    }

    @Test
    public void testValidateDate() {
        commonUtils.validateDate("2022-07-15T17:20:36.123Z");
        Assertions.assertTrue(true);
    }

    @Test
    public void testValidateDateFailFutureDate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> commonUtils.validateDate("2075-07-15T17:20:36.123Z"));
    }

    @Test
    public void testValidateDateFailParseError() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> commonUtils.validateDate("2075-07-15T17:20:36"));
    }

}
