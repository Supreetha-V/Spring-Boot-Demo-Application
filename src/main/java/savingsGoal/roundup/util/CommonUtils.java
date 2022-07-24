package savingsGoal.roundup.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class has all the helper methods used in the application
 */
@Component
public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    @Autowired
    private ObjectMapper mapper;

    /**
     * Method to parse the JSON string to object
     * @param value string to be parsed
     * @param clz  object the string is parsed to
     * @param <T>
     * @return
     */
    public <T> T readObject(String value, Class<T> clz) {
        try {
            return mapper.readValue(value, clz);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Method to validate if the given start date is valid.
     * @param date
     */
    public void validateDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startdate = LocalDateTime.parse(date, formatter);

            if(startdate.isAfter(now)){
                logger.error("Incorrect start date. StartDate cannot be in future");
                throw new IllegalArgumentException("Start date cannot be in future. Provide a valid date");
            }

            logger.info("StartDate: " + startdate);
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("Input String format is not correct. Provide the date in 'yyyy-MM-ddTHH:mm:ss" +
                    ".sssZ' formate");
        }
    }

}
