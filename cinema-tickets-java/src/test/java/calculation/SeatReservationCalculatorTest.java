package calculation;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.calculation.SeatReservationCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import utils.TestUtils;

import java.util.ArrayList;

public class SeatReservationCalculatorTest {

    TestUtils testUtils;

    @Before
    public void setup() {
        testUtils = new TestUtils();
    }

    @Test
    public void calculateSeatReservationsAllAdults() {
        SeatReservationCalculator seatReservationCalculator = new SeatReservationCalculator();
        int expectedResult = 20;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 20);
        requestArray.add(request);
        int actualResult = seatReservationCalculator.calculateNoSeatReservations(requestArray);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSeatReservationsAdultsChilds() {
        SeatReservationCalculator seatReservationCalculator = new SeatReservationCalculator();
        int expectedResult = 20;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 10);
        requestArray.add(request);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketType.CHILD, 10);
        requestArray.add(request2);
        int actualResult = seatReservationCalculator.calculateNoSeatReservations(requestArray);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSeatReservationsAdultsInfants() {
        SeatReservationCalculator seatReservationCalculator = new SeatReservationCalculator();
        int expectedResult = 10;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 10);
        requestArray.add(request);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketType.INFANT, 10);
        requestArray.add(request2);
        int actualResult = seatReservationCalculator.calculateNoSeatReservations(requestArray);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSeatReservationAllTickets() {
        SeatReservationCalculator seatReservationCalculator = new SeatReservationCalculator();
        int expectedResult = 15;
        ArrayList<TicketTypeRequest> requestArray = new ArrayList<>();
        TicketTypeRequest request = new TicketTypeRequest(TicketType.ADULT, 10);
        requestArray.add(request);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketType.INFANT, 5);
        requestArray.add(request2);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketType.CHILD, 5);
        requestArray.add(request3);
        int actualResult = seatReservationCalculator.calculateNoSeatReservations(requestArray);

        assertEquals(expectedResult, actualResult);
    }


}
