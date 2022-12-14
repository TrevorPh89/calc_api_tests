package tests.data_providers;

import org.testng.annotations.DataProvider;

public class DivisionDataProvider {
    private static final long MAX_INT_VALUE = ((Number) Integer.MAX_VALUE).longValue();
    private static final long MIN_INT_VALUE = ((Number) Integer.MIN_VALUE).longValue();

    @DataProvider(name = "integer-data-provider")
    public static Object[][] integerDataProvider() {
        return new Object[][]{{MAX_INT_VALUE, ((Number) 1).longValue()},
                {MIN_INT_VALUE, ((Number) 1).longValue()},
                {((Number) 100).longValue(), ((Number) 20).longValue()},
                {((Number) 100).longValue(), ((Number) 3).longValue()},
                {((Number) 100).longValue(), ((Number) 200).longValue()},
                {((Number) 100).longValue(), ((Number) 0).longValue()},
                {((Number) (-100)).longValue(), ((Number) 20).longValue()},
                {((Number) (-100)).longValue(), ((Number) (-20)).longValue()},
                {MAX_INT_VALUE, MAX_INT_VALUE},
                {MIN_INT_VALUE, MIN_INT_VALUE}
        };
    }

    @DataProvider(name = "noInt-data-provider")
    public static Object[][] noIntDataProvider() {
        return new Object[][]{
                {100.5, 2},
                {"string", 2},
                {'A', 2}
        };
    }

    @DataProvider(name = "excessInt-data-provider")
    public static Object[][] excessIntDataProvider() {
        return new Object[][]{
                {MAX_INT_VALUE + 1, 100},
                {MIN_INT_VALUE - 1, 100},
        };
    }
}
