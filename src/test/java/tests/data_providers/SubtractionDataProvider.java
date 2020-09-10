package tests.data_providers;

import org.testng.annotations.DataProvider;

public class SubtractionDataProvider {
    private static final long MAX_INT_VALUE = ((Number) Integer.MAX_VALUE).longValue();
    private static final long MIN_INT_VALUE = ((Number) Integer.MIN_VALUE).longValue();


    @DataProvider(name = "integer-data-provider")
    public static Object[][] integerDataProvider() {
        return new Object[][]{{MAX_INT_VALUE, ((Number) 1).longValue()},
                {((Number) 100).longValue(), ((Number) 100).longValue()},
                {((Number) (-100)).longValue(), ((Number) 100).longValue()},
                {((Number) (-100)).longValue(), ((Number) (-100)).longValue()},
                {((Number) 100).longValue(), ((Number) 1).longValue()},
                {((Number) 100).longValue(), ((Number) 0).longValue()},
                {MAX_INT_VALUE, MAX_INT_VALUE},
                {MIN_INT_VALUE, MIN_INT_VALUE}
        };
    }

    @DataProvider(name = "noInt-data-provider")
    public static Object[][] noIntDataProvider() {
        return new Object[][]{
                {100, 10.5},
                {100, "100"},
                {100, 'A'}
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
