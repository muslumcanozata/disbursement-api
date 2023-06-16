package com.loan.disbursementapi.exception.custom;

import com.loan.disbursementapi.exception.base.ValidationException;


import java.text.MessageFormat;

public class CreditNotFoundException extends ValidationException {
    private static final String MESSAGE ="Girilen user id ile ilişkili bir kredi bulunamadı. User Id: {0}";
    private static final String MESSAGE_WITHOUT_VARIABLE = "Kredi bulunamadı.";

    public CreditNotFoundException(Integer userId) {
        super(MessageFormat.format(MESSAGE, userId));
    }

    public CreditNotFoundException() {
        super(MESSAGE_WITHOUT_VARIABLE);
    }

}
