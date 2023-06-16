package com.loan.disbursementapi.exception.custom;

import com.loan.disbursementapi.exception.base.ValidationException;

import java.text.MessageFormat;

public class UserNotFoundException extends ValidationException {
    private static final String MESSAGE ="Girilen id ile ilişkili bir kullanıcı bulunamadı. Id: {0}";
    private static final String MESSAGE_WITHOUT_VARIABLE ="Herhangi bir kullanıcı bulunamadı.";


    public UserNotFoundException(Integer id) {
        super(MessageFormat.format(MESSAGE, id));
    }

    public UserNotFoundException() {
        super(MESSAGE_WITHOUT_VARIABLE);
    }

}
