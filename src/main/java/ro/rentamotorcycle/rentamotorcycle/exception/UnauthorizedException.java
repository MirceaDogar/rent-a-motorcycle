package ro.rentamotorcycle.rentamotorcycle.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
