import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    // Method to fetch exchange rate without JSON library
    public static double getExchangeRate(String base, String target) {
        double rate = 0;
        try {
            String apiKey = "d13f3ef2a8d5774ab1627a95"; // Apna free API key yaha daalo
            String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;

            URL url = new URL(url_str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String data = response.toString();

            // Simple string search to get the target currency rate
            String key = "\"" + target + "\":";
            int index = data.indexOf(key);
            if (index != -1) {
                int start = index + key.length();
                int end = data.indexOf(",", start);
                if (end == -1) { // last currency in JSON
                    end = data.indexOf("}", start);
                }
                String rateStr = data.substring(start, end).trim();
                rate = Double.parseDouble(rateStr);
            }

        } catch (Exception e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }
        return rate;
    }

    // Method to get currency symbol
    public static String getCurrencySymbol(String currencyCode) {
        switch(currencyCode) {
            case "USD": return "$";
            case "INR": return "₹";
            case "EUR": return "€";
            case "GBP": return "£";
            case "JPY": return "¥";
            default: return currencyCode; // Agar unknown ho
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Currency Selection
        System.out.println("Enter base currency (USD, INR, EUR etc): ");
        String baseCurrency = sc.nextLine().toUpperCase();

        System.out.println("Enter target currency (USD, INR, EUR etc): ");
        String targetCurrency = sc.nextLine().toUpperCase();

        // 2. Amount Input
        System.out.println("Enter amount to convert: ");
        double amount = sc.nextDouble();

        // 3. Conversion
        double rate = getExchangeRate(baseCurrency, targetCurrency);
        double convertedAmount = amount * rate;

        // 4. Display Result with symbol
        String symbol = getCurrencySymbol(targetCurrency);
        System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, symbol);

        sc.close();
    }
} 