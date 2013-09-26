package ca.ulaval.glo4003.model;

public class MatchBuilderException extends RuntimeException {

    private static final long serialVersionUID = 4042265108395078827L;
    private static final String MESSAGE_TEMPLATE = "The attribute \"%s\" was not set properly.";

    public MatchBuilderException(String attributeName) {
        super(String.format(MESSAGE_TEMPLATE, attributeName));
    }
}