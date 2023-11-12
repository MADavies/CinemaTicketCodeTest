package tickettyperequest;

import org.junit.Assert;
import org.junit.Test;

import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class TicketTypeRequestTest {

    @Test
    public void adultTicketTypeRequestTest() {
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 2);
        Assert.assertEquals(request.getTicketType(), TicketType.ADULT);
        Assert.assertEquals(request.getTicketType().price, 20);
        Assert.assertEquals(request.getNoOfTickets(), 2);
    }

    @Test
    public void childTicketTypeRequestTest() {
        TicketTypeRequest request = new TicketTypeRequest(TicketType.CHILD, 2);
        Assert.assertEquals(request.getTicketType(), TicketType.CHILD);
        Assert.assertEquals(request.getTicketType().price, 10);
        Assert.assertEquals(request.getNoOfTickets(), 2);
    }

    @Test
    public void infantTicketTypeRequestTest() {
        TicketTypeRequest request = new TicketTypeRequest(TicketType.INFANT, 2);
        Assert.assertEquals(request.getTicketType(), TicketType.INFANT);
        Assert.assertEquals(request.getTicketType().price, 0);
        Assert.assertEquals(request.getNoOfTickets(), 2);
    }
}
