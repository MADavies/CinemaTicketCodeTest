package uk.gov.dwp.uc.pairtest.domain;

import java.util.Currency;

/**
 * Immutable Object
 */

public class TicketTypeRequest {

    private int noOfTickets;
    private TicketType type;

    public TicketTypeRequest(final TicketType type, int noOfTickets) {
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public TicketType getTicketType() {
        return type;
    }

}
