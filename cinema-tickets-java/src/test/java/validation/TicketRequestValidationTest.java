package validation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.validation.Errors;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidation;
import utils.TestUtils;

import java.util.ArrayList;

public class TicketRequestValidationTest {

    TestUtils testUtils;

    @Before
    public void setup() {
        testUtils = new TestUtils();
    }


    //Ticket Range Tests
    @Test
    public void noOfTicketsWithinRangeTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createTicketRequests(10);
        TicketRequestValidation validation = new TicketRequestValidation();
        boolean result = validation.checkNoOfTickets(ticketRequests);
        assertTrue(result);
    }

    @Test
    public void noOfTicketsExceedsRangeTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createTicketRequests(300);
        TicketRequestValidation validation = new TicketRequestValidation();
        boolean result = validation.checkNoOfTickets(ticketRequests);
        assertFalse(result);
    }

    @Test
    public void zeroTicketsTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createTicketRequests(0);
        TicketRequestValidation validation = new TicketRequestValidation();
        boolean result = validation.checkNoOfTickets(ticketRequests);
        assertFalse(result);
    }

    //Ticket combination Tests
    @Test
    public void validAdultInfantTicketRequestTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createTicketRequests(6);
        TicketRequestValidation validation = new TicketRequestValidation();
        ArrayList<Errors> result = validation.checkTicketVariations(ticketRequests);
        assertEquals(0, result.size());
    }

    @Test
    public void invalidAdultInfantTicketRequestTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createInvalidAdultAndInfantTicketRequest();
        TicketRequestValidation validation = new TicketRequestValidation();
        ArrayList<Errors> result = validation.checkTicketVariations(ticketRequests);
        assertEquals(1, result.size());
        assertEquals("Not enough adult tickets requested", result.get(0).getMessage());
    }

    @Test
    public void validAdultChildTicketRequestTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createAdultAndChildTicketRequest();
        TicketRequestValidation validation = new TicketRequestValidation();
        ArrayList<Errors> result = validation.checkTicketVariations(ticketRequests);
        assertEquals(0, result.size());
    }

    @Test
    public void invalidNoAdultTicketRequestTest() {
        ArrayList<TicketTypeRequest> ticketRequests = testUtils.createNoAdultTicketRequest();
        TicketRequestValidation validation = new TicketRequestValidation();
        ArrayList<Errors> result = validation.checkTicketVariations(ticketRequests);
        assertEquals(1, result.size());
        assertEquals("No Adult Tickets Requested", result.get(0).getMessage());
    }

}
