package com.payment.expensecontrolsystem.data.dto.insights;

import com.payment.expensecontrolsystem.enums.Mouths;
import com.payment.expensecontrolsystem.interfaces.IDto;

import java.util.List;
import java.util.Map;

public class SpendByMouthDTO implements IDto {
    private final List<Map<Mouths, Double>> spendByMouth;

    public SpendByMouthDTO(List<Map<Mouths, Double>> spendByMouth) {
        this.spendByMouth = spendByMouth;
    }

    public List<Map<Mouths, Double>> getSpendByMouth() {
        return spendByMouth;
    }
}
