package ru.performancelab.enums;

public enum TitleNaming {
    PRODUCTS("Products"),
    CARTS("Your Cart"),
    CHECKOUT_YOUR_INFORMATION("Checkout: Your Information"),
    CHECKOUT_OVERVIEW("Checkout: Overview"),
    CHECKOUT_COMPLETE("Checkout: Complete!");

    private final String displayTitle;

    TitleNaming(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }
}
