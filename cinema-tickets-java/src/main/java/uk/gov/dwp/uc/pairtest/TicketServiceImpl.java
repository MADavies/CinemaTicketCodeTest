package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.calculation.SeatReservationCalculator;
import uk.gov.dwp.uc.pairtest.calculation.TicketPriceCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.exception.InvalidUserException;
import uk.gov.dwp.uc.pairtest.validation.Errors;
import uk.gov.dwp.uc.pairtest.validation.TicketRequestValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
    private TicketPaymentServiceImpl ticketPaymentService = new TicketPaymentServiceImpl();
    private SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();

    private TicketRequestValidation ticketRequestValidation = new TicketRequestValidation();
    private TicketPriceCalculator ticketPriceCalculator = new TicketPriceCalculator();
    private SeatReservationCalculator seatReservationCalculator = new SeatReservationCalculator();

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException,
            InvalidUserException {

        List<TicketTypeRequest> ticketTypeRequestsArray = Arrays.asList(ticketTypeRequests);
//        try {
        purchaseTickets(accountId, ticketTypeRequestsArray);
//        } catch (InvalidPurchaseException e) {
//            System.out.println("Invalid purchase request made - Reason: " + e.getMessage());
//            throw new InvalidPurchaseException(e.getMessage(), e.getCause());
//        } catch (InvalidUserException u) {
//            var message = "Invalid purchase request made - Reason: ";
//            System.out.println(message + u.getMessage());
//            throw new InvalidUserException(message + u.getMessage(), u.getCause());
//        }
    }

    private void purchaseTickets(Long accountId, List<TicketTypeRequest> ticketRequests) throws InvalidPurchaseException,
            InvalidUserException {

        //Check if the user account is valid if not throw InvalidUserException
        if (!isUserValid(accountId)) {
            throw new InvalidUserException("Invalid purchase request made - Reason: User ID is not valid");
        } else {
            if (ticketRequestValidation.checkNoOfTickets(ticketRequests)) {
                ArrayList<Errors> errors = ticketRequestValidation.checkTicketVariations(ticketRequests);
                //If user is valid perform validation on tickets requested
                if (errors.size() > 0) {
                    for (Errors error : errors) {
                        throw new InvalidPurchaseException("Invalid purchase request made - Reason: " + error.getMessage());
                    }

                }
            } else {
                throw new InvalidPurchaseException("Invalid purchase request made - Reason: Maximum number of requested tickets exceeded" );
            }

            //If ticket variation is valid, calculate cost of tickets and call Third party services.
            //Assume all is fine from payment third party
            ticketPaymentService.makePayment(accountId, ticketPriceCalculator.CalculateTicketPrices(ticketRequests));
            //Assume All is fine from seat reservation third party
            seatReservationService.reserveSeat(accountId, seatReservationCalculator.calculateNoSeatReservations(ticketRequests));

            System.out.println("Order Completed Thank you!");
        }
    }

    private boolean isUserValid(Long accountId) {
        if(accountId > 0L) {
            return true;
        } else {
            return false;
        }
    }

}
