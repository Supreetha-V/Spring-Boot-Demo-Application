package savingsGoal.roundup.model;

import lombok.Builder;
import lombok.Data;

/**
 * CurrencyAndAmount is the data transfer object which represnts the amount and the corresponding currency.
 */
@Builder
@Data
public class CurrencyAndAmount {
    private String currency;
    private Integer minorUnits;
}
