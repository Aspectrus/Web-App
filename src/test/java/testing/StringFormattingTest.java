import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import webapp.StringFormatter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StringFormattingTest {

    @Parameterized.Parameter(0)
    public String CountryCode;
    @Parameterized.Parameter(1)
    public String BankCode;
    @Parameterized.Parameter(2)
    public String Result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { "LT" , "055568994444", "LT 05556899(4444)" },
                { "US", "15897", "US 15897" },
                { "DE", "1558555689777", "DE 1558(555)689(777)" },
                { "DE", "7777", "DE (7777)" }};
        return Arrays.asList(data);
    }

    @Test
    public void testIfFormattingIsDoneCorrectly() {
        StringFormatter stringFormatter = new StringFormatter();
        assertEquals(Result, stringFormatter.FormatInput(BankCode, CountryCode));
    }

}
