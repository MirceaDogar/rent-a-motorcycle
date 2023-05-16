package ro.rentamotorcycle.rentamotorcycle.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
