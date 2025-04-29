package com.payment.expensecontrolsystem.enums;

public enum Mouths {
    JAN("Janeiro"), FEB("fevereiro"), MAR("março"), APR("abril"),
    MAY("maio"), JUN("junho"), JUL("julho"), AUG("agosto"),
    SEP("setembro"), OCT("outubro"), NOV("novembro"), DEC("dezembro");

    private String name;

    Mouths(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
