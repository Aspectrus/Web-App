package testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import webapp.InputValidator;
import webapp.StringFormatter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


public class InputValidationTests {

    @RunWith(Parameterized.class)
    public static class ValidInputTest {
        @Parameterized.Parameter(0)
        public String CountryCode;
        @Parameterized.Parameter(1)
        public String BankCode;

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            Object[][] data = new Object[][]{{"LT", "05556894"},
                    {"US", "15897"},
                    {"DE", "15585568977"}};
            return Arrays.asList(data);
        }

        @Test
        public void testValidFullCode() {
            InputValidator inputValidator = new InputValidator();
            assertEquals(inputValidator.ValidateInputCodes(CountryCode, BankCode), true);
        }
    }
    @RunWith(Parameterized.class)
    public static class InValidInputTest {
        @Parameterized.Parameter(0)
        public String CountryCode;
        @Parameterized.Parameter(1)
        public String BankCode;

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            Object[][] data = new Object[][]{{"L4T", "05556894"},
                    {"US", "541a488"},
                    {"DE", "1"}};
            return Arrays.asList(data);
        }

        @Test
        public void testInvalidFullCode() {
            InputValidator inputValidator = new InputValidator();
            assertEquals(inputValidator.ValidateInputCodes(CountryCode, BankCode), false);
        }
    }

}