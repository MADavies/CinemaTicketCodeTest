package uk.gov.dwp.uc.pairtest.validation;

public class Errors {
    private String message;

    public Errors(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
