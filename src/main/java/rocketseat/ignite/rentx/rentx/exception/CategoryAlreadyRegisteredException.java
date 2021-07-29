package rocketseat.ignite.rentx.rentx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyRegisteredException extends Exception{

    public CategoryAlreadyRegisteredException(String categoryName) {
        super(String.format("Category with name %s already registered in the system,", categoryName));
    }
}
