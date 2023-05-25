package ra.model.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Config {
    public static Locale locale = Locale.US;
    public static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
}
