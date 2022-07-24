package savingsGoal.roundup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import savingsGoal.roundup.exceptions.RestException;

/**
 * This class makes Http GET and PUT requests to fetch and create data as needed.
 */
@Service
public class RestClientService {
    private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Method for Http GET requests to fetch required data.
     * @param uri resource uri
     * @param token authentication token
     * @return
     */
    public String get(String uri, String token) {
        try {
            HttpEntity<Void> requestEntity = new HttpEntity<>(setHeader(token));
            ResponseEntity<String> response = restTemplate.exchange(
                    uri, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("Exception during GET: " + e.getMessage());
            throw new RestException("Exception during GET: " + e.getMessage());
        }
    }

    /**
     * Method makes Http PUT requests to create and update data.
     * @param uri  resource uri
     * @param body  request body
     * @param token  authentication token
     * @return
     */
    public String put(String uri, Object body, String token) {
        try {
            HttpEntity<Object> requestEntity = new HttpEntity<>(body, setHeader(token));
            ResponseEntity<String> response = restTemplate.exchange(
                    uri, HttpMethod.PUT, requestEntity, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
            throw new RestException("Exception during PUT- HTTPStatus: " + response.getStatusCode() + " HTTPMessage: " + response.getBody());

        } catch (Exception e) {
            logger.error("Exception during PUT: " + e.getMessage());
            throw new RestException("Exception during PUT: " + e.getMessage());
        }
    }

    /**
     * Method to set header details
     * @param token
     * @return
     */
    private HttpHeaders setHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + token);
        headers.set("User-Agent", "Supreetha");
        return headers;
    }
}
