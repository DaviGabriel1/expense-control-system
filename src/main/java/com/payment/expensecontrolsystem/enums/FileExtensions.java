package com.payment.expensecontrolsystem.enums;

public enum FileExtensions {
    XLSX(".xlsx");

    private String extension;

    FileExtensions(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
