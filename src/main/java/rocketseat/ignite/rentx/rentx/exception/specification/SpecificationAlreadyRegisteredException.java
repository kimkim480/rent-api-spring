package rocketseat.ignite.rentx.rentx.exception.specification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpecificationAlreadyRegisteredException extends Exception {

    public SpecificationAlreadyRegisteredException(String message) {
        super(message);
    }
}
