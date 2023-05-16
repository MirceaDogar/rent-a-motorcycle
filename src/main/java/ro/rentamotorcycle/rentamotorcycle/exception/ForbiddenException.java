package ro.rentamotorcycle.rentamotorcycle.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
