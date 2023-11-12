package utils;

import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketType;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class TestUtils {

    public Long createUserLoginId() {
        return Math.abs(new Random().nextLong());
    }

    public Long createInvalidUserLoginId() {
        return 0L;
    }

    public ArrayList<TicketTypeRequest> createTicketRequests(int noOfTickets) {
        ArrayList<TicketTypeRequest> ticketRequests = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, noOfTickets);
        ticketRequests.add(request);
        return ticketRequests;
    }

    public ArrayList<TicketTypeRequest> createAdultAndInfantTicketRequest(int noOfTickets) {
        ArrayList<TicketTypeRequest> ticketRequests = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, noOfTickets/2);
        ticketRequests.add(request);
        request = new TicketTypeRequest(TicketType.INFANT, noOfTickets/2);
        ticketRequests.add(request);
        return ticketRequests;
    }

    public ArrayList<TicketTypeRequest> createAdultAndChildTicketRequest() {
        ArrayList<TicketTypeRequest> ticketRequests = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 5);
        ticketRequests.add(request);
        request = new TicketTypeRequest(TicketType.CHILD, 5);
        ticketRequests.add(request);
        return ticketRequests;
    }

    public ArrayList<TicketTypeRequest> createInvalidAdultAndInfantTicketRequest() {
        ArrayList<TicketTypeRequest> ticketRequests = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 5);
        ticketRequests.add(request);
        request = new TicketTypeRequest(TicketType.INFANT, 6);
        ticketRequests.add(request);
        return ticketRequests;
    }

    public ArrayList<TicketTypeRequest> createNoAdultTicketRequest() {
        ArrayList<TicketTypeRequest> ticketRequests = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.CHILD, 5);
        ticketRequests.add(request);
        request = new TicketTypeRequest(TicketType.INFANT, 5);
        ticketRequests.add(request);
        return ticketRequests;
    }

}
