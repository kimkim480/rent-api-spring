package rocketseat.ignite.rentx.rentx.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyRegisteredException extends Exception{

    public CategoryAlreadyRegisteredException(String message) {
        super(message);
    }
}
