package uk.gov.dwp.uc.pairtest.calculation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.List;

public class SeatReservationCalculator {

    public int calculateNoSeatReservations(List<TicketTypeRequest> ticketRequests) {
        int totalSeats = 0;

        for (TicketTypeRequest request: ticketRequests) {
            if (!request.getTicketType().name().equals("INFANT")) {
                totalSeats += (request.getNoOfTickets());
            }
        }

        return totalSeats;
    }
}
