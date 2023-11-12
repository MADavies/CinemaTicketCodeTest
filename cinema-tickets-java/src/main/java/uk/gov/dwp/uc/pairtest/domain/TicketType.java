package uk.gov.dwp.uc.pairtest.domain;

import java.util.Currency;

public enum TicketType {
    ADULT(20),
    CHILD(10),
    INFANT(0);

    public final int price;

    private TicketType(int price) {
        this.price = price;
    }
}
