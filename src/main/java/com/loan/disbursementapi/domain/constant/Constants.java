package com.loan.disbursementapi.domain.constant;

import java.math.BigDecimal;

public class Constants {
    public static final String OVERDUE_CHECK_CRON = "0 * * * * *";
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.01);
    public static final Integer DAY_ON_ONE_YEAR = 360;
}
