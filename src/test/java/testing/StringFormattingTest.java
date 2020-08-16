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
        Object[][] data = new Object[][] { { "LT" , "05556894", "LT 0(555)6894" },
                { "US", "15897", "US 15897" },
                { "DE", "15585568977", "DE 1(55)85568977" },
                { "DE", "7777", "DE (7777)" }};
        return Arrays.asList(data);
    }

    @Test
    public void testIfFormattingIsDoneCorrectly() {
        StringFormatter stringFormatter = new StringFormatter();
        assertEquals(Result, stringFormatter.FormatInput(BankCode, CountryCode));
    }

}
