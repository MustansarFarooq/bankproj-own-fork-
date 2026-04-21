import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ServicesModule {
    // Store currencies and their conversion rates
    private Map<String, BigDecimal> currencyRates;

    public ServicesModule() {
        currencyRates = new HashMap<>();
        // Example conversion rates
        currencyRates.put("USD", BigDecimal.valueOf(1)); // base currency
        currencyRates.put("EUR", BigDecimal.valueOf(0.85));
        currencyRates.put("GBP", BigDecimal.valueOf(0.75));
        currencyRates.put("INR", BigDecimal.valueOf(74.15));
    }

    // Method for Custodial Account
    public void createCustodialAccount(String accountHolder) {
        // Implementation for creating a custodial account
        System.out.println("Custodial account created for " + accountHolder);
    }

    // Method for Debit Card
    public void issueDebitCard(String accountHolder) {
        // Implementation for issuing a debit card
        System.out.println("Debit card issued to " + accountHolder);
    }

    // Method for Currency Converter
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        if (!currencyRates.containsKey(fromCurrency) || !currencyRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency specified");
        }
        // Convert amount to the target currency
        BigDecimal fromRate = currencyRates.get(fromCurrency);
        BigDecimal toRate = currencyRates.get(toCurrency);
        return amount.multiply(toRate).divide(fromRate, 2, BigDecimal.ROUND_HALF_UP);
    }
}