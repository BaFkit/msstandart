package ru.msstandart.enumeration;

public enum StatusOrder {
    Signing("На подписание"),
    New("Новый"),
    In_work("В работе"),
    Ready("Готов"),
    Clarification("На уточнении"),
    Issued("Отдан");

    private final String nameStatus;

    StatusOrder(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }
}
