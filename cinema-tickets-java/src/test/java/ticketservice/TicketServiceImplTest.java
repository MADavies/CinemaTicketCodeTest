package ticketservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.exception.InvalidUserException;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceImplTest {

    /**
     * # Business Rules

     - There are 3 types of tickets i.e. Infant, Child, and Adult.

     - The ticket prices are based on the type of ticket (see table below).

     - The ticket purchaser declares how many and what type of tickets they want to buy.

     - Multiple tickets can be purchased at any given time.

     - Only a maximum of 20 tickets that can be purchased at a time.

     - Infants do not pay for a ticket and are not allocated a seat. They will be sitting on an Adult's lap.

     - Child and Infant tickets cannot be purchased without purchasing an Adult ticket.

     |   Ticket Type    |     Price   |

     | ---------------- | ----------- |

     |    INFANT        |    £0       |

     |    CHILD         |    £10      |

     |    ADULT         |    £20      |

     - There is an existing `TicketPaymentService` responsible for taking payments.

     - There is an existing `SeatReservationService` responsible for reserving seats.

     ## Assumptions

     You can assume:

     - All accounts with an id greater than zero are valid. They also have sufficient funds to pay for any no of tickets.

     - The `TicketPaymentService` implementation is an external provider with no defects. You do not need to worry about how the actual payment happens.

     - The payment will always go through once a payment request has been made to the `TicketPaymentService`.

     - The `SeatReservationService` implementation is an external provider with no defects. You do not need to worry about how the seat reservation algorithm works.

     - The seat will always be reserved once a reservation request has been made to the `SeatReservationService`.

     ## Your Task

     Provide a working implementation of a `TicketService` that:

     - Considers the above objective, business rules, constraints & assumptions.

     - Calculates the correct amount for the requested tickets and makes a payment request to the `TicketPaymentService`.

     - Calculates the correct no of seats to reserve and makes a seat reservation request to the `SeatReservationService`.

     - Rejects any invalid ticket purchase requests. It is up to you to identify what should be deemed as an invalid purchase request.”

     */

    @Mock
    TicketPaymentServiceImpl ticketPaymentService;

    @Mock
    SeatReservationServiceImpl seatReservationService;

    TestUtils testUtils;

    @Before
    public void setup() {
        testUtils = new TestUtils();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void ticketServiceSaleSuccess() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        assertDoesNotThrow(() ->
                ticketService.purchaseTickets(testUtils.createUserLoginId(), new TicketTypeRequest(TicketType.ADULT, 2)));

    }

    @Test
    public void ticketServiceSaleAdultAndInfantSuccess() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        assertDoesNotThrow(() ->
                ticketService.purchaseTickets(testUtils.createUserLoginId(),
                        new TicketTypeRequest(TicketType.ADULT, 1),
                        new TicketTypeRequest(TicketType.INFANT, 1)));
    }

    @Test
    public void ticketServiceSaleAdultAndChildSuccess() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        assertDoesNotThrow(() ->
                ticketService.purchaseTickets(testUtils.createUserLoginId(),
                        new TicketTypeRequest(TicketType.ADULT, 2),
                        new TicketTypeRequest(TicketType.ADULT, 1)));

    }

    @Test
    public void ticketServiceInvalidSaleNoAdultTicketTest() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        String expectedMessage = "Invalid purchase request made - Reason: No Adult Tickets Requested";

        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(testUtils.createUserLoginId(),
                new TicketTypeRequest(TicketType.CHILD, 2)));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void ticketServiceInvalidSaleMoreThan20ticketsSelected() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        String expectedMessage = "Invalid purchase request made - Reason: Maximum number of requested tickets exceeded";

        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(testUtils.createUserLoginId(),
                        new TicketTypeRequest(TicketType.ADULT, 21)));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void ticketServiceInvalidSaleMoreInfantsThanAdults() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        String expectedMessage = "Invalid purchase request made - Reason: Not enough adult tickets requested";

        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(testUtils.createUserLoginId(),
                        new TicketTypeRequest(TicketType.INFANT, 2),
                        new TicketTypeRequest(TicketType.ADULT, 1)));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void ticketServiceInvalidAccountNoTest() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        String expectedMessage = "Invalid purchase request made - Reason: User ID is not valid";

        InvalidUserException exception = assertThrows(InvalidUserException.class,
                () -> ticketService.purchaseTickets(testUtils.createInvalidUserLoginId(),
                        new TicketTypeRequest(TicketType.CHILD, 2)));

        assertEquals(expectedMessage, exception.getMessage());
    }

}
