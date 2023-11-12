package calculation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.gov.dwp.uc.pairtest.calculation.TicketPriceCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import utils.TestUtils;

import java.util.ArrayList;

public class TicketPriceCalculatorTest {

    TestUtils testUtils;

    @Before
    public void setup() {
        testUtils = new TestUtils();
    }

    @Test
    public void calculateTotalCostAllAdultTickets() {
        TicketPriceCalculator calculator = new TicketPriceCalculator();
        int expectedTotal = 400;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 20);
        requestArray.add(request);
        var actualTotal = calculator.CalculateTicketPrices(requestArray);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void calculateTotalCostAdultAndChildTickets() {
        TicketPriceCalculator calculator = new TicketPriceCalculator();
        int expectedTotal = 300;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 10);
        requestArray.add(request);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketType.CHILD, 10);
        requestArray.add(request2);
        var actualTotal = calculator.CalculateTicketPrices(requestArray);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void calculateTotalCostAdultChildAndInfantTickets() {
        TicketPriceCalculator calculator = new TicketPriceCalculator();
        int expectedTotal = 250;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 10);
        requestArray.add(request);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketType.CHILD, 5);
        requestArray.add(request2);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketType.INFANT, 5);
        requestArray.add(request3);
        var actualTotal = calculator.CalculateTicketPrices(requestArray);

        assertEquals(expectedTotal, actualTotal);
    }
}
