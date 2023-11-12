package uk.gov.dwp.uc.pairtest.calculation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.List;

public class TicketPriceCalculator {

    public int CalculateTicketPrices(List<TicketTypeRequest> ticketRequests) {
        int totalCost = 0;

        for (TicketTypeRequest request: ticketRequests) {
            if (!request.getTicketType().name().equals("INFANT")) {
                totalCost += (request.getNoOfTickets()*request.getTicketType().price);
            }
        }

        return totalCost;
    }
}
