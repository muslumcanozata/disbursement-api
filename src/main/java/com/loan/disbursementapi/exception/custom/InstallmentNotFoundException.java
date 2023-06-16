package com.loan.disbursementapi.exception.custom;

import com.loan.disbursementapi.exception.base.ValidationException;

import java.text.MessageFormat;

public class InstallmentNotFoundException extends ValidationException {

    private static final String MESSAGE = "Girilen {0} ile ilişkili bir kredi bulunamadı. Id: {1}";
    private static final String MESSAGE_WITHOUT_VARIABLE = "Herhangi bir taksit bulunamadı.";


    public InstallmentNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, "id", id));
    }
    public InstallmentNotFoundException() {
        super(MESSAGE_WITHOUT_VARIABLE);
    }
}
