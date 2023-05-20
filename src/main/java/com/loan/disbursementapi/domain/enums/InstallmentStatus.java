package com.loan.disbursementapi.domain.enums;

public enum InstallmentStatus {
    PAYABLE,
    PAID,
    PENDING,
    DELAYED;

    public static boolean isActive(InstallmentStatus installmentStatus) {
        return InstallmentStatus.PAYABLE.equals(installmentStatus) || InstallmentStatus.DELAYED.equals(installmentStatus) || InstallmentStatus.PENDING.equals(installmentStatus);
    }
}
