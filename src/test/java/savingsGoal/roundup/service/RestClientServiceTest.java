package savingsGoal.roundup.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import savingsGoal.roundup.exceptions.RestException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestClientServiceTest {

    @InjectMocks
    private RestClientService restClientService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetPass() {

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), (Class<String>) any())).thenReturn(new ResponseEntity<String>("mockResponse", HttpStatus.OK));

        String response = restClientService.get("test", "mockToken");
        Assertions.assertNotNull(response);
    }

    @Test
    public void testGetFail() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), (Class<String>) any()))
                .thenThrow(new RestException("Testing RestException"));
        Exception exception = Assertions.assertThrows(RestException.class, () -> restClientService.get("mockUri",
                "mockToken"));

        Assertions.assertEquals("Exception during GET: Testing RestException", exception.getMessage());
    }

    @Test
    public void testPutPass() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), (Class<String>) any())).thenReturn(new ResponseEntity<String>("mockResponse", HttpStatus.OK));

        String response = restClientService.put("test", "mockBody", "mockToken");
        Assertions.assertNotNull(response);
    }

    @Test
    public void testPutFail() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), (Class<String>) any()))
                .thenThrow(new RestException("Testing RestException"));
        Exception exception = Assertions.assertThrows(RestException.class, () -> restClientService.put("mockUri",
                "mockBody", "mockToken"));

        Assertions.assertEquals("Exception during PUT: Testing RestException", exception.getMessage());
    }
}
