package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRequestValidation {

    public boolean checkNoOfTickets(final List<TicketTypeRequest> ticketRequests) {
        int noOfTickets = 0;

        for (TicketTypeRequest ticketRequest : ticketRequests) {
            noOfTickets += ticketRequest.getNoOfTickets();
        }

        if (noOfTickets > 0 && noOfTickets <= 20)  {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Errors> checkTicketVariations(final List<TicketTypeRequest> ticketTypeRequests) {
        //Check Request has adult tickets
        ArrayList<Errors> errorList = new ArrayList<>();

        boolean containsAdultTicketRequest = containsTickets(ticketTypeRequests, "ADULT");
        if (!containsAdultTicketRequest) {
            //Stop here and return false as at least one adult ticket is needed.
            errorList.add(new Errors("No Adult Tickets Requested"));
        } else {
            //Requests contain at least one adult ticket check other requests
            if (containsTickets(ticketTypeRequests, "INFANT")) {
                //Check the number of infant tickets requested is not more than the number of Adult tickets requests
                if (getNoOfTicketsFromRequest(ticketTypeRequests, "INFANT") >
                        getNoOfTicketsFromRequest(ticketTypeRequests, "ADULT"))   {
                    //Return false as infants cannot take up more seats than adults due to the sharing.
                    errorList.add(new Errors("Not enough adult tickets requested"));
                }
            }
        }
        //Don't need to worry about Children tickets as that is covered by checking for number of seats and at least one
        //Adult ticket
        return errorList;
    }

    private boolean containsTickets(final List<TicketTypeRequest> ticketTypeRequests, final String type) {
        return ticketTypeRequests.stream().map(TicketTypeRequest::getTicketType).anyMatch(ticketType -> ticketType.name().equals(type));
    }

    private Optional<TicketTypeRequest> getTicketRequest(final List<TicketTypeRequest> ticketTypeRequests, final String type) {
        Optional<TicketTypeRequest> request = ticketTypeRequests.stream().filter(t -> t.getTicketType().name().equals(type)).findFirst();
        return request;
    }

    private int getNoOfTicketsFromRequest(final List<TicketTypeRequest> ticketTypeRequests, final String type) {
        if (getTicketRequest(ticketTypeRequests, type).isPresent()) {
            TicketTypeRequest ticketTypeRequest = getTicketRequest(ticketTypeRequests, type).get();
            return ticketTypeRequest.getNoOfTickets();
        } else {
            return 0;
        }
    }



}
