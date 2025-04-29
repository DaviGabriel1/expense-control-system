package com.payment.expensecontrolsystem.data.dto.insights;

import com.payment.expensecontrolsystem.enums.Mouths;

import java.util.List;
import java.util.Map;

public class HighSpendMouthsDTO {
    private final List<Map<Mouths, Double>> highSpendMouths;

    public HighSpendMouthsDTO(List<Map<Mouths, Double>> highSpendMouths) {
        this.highSpendMouths = highSpendMouths;
    }

    public List<Map<Mouths, Double>> getHighSpendMouths() {
        return highSpendMouths;
    }
}
