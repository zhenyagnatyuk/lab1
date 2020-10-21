package Pizzas.exceptions;

public class MissingBaseTypeException extends IllegalArgumentException  {
    public MissingBaseTypeException(String message) {
        super(message);
    }
}
