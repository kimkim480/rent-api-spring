package rocketseat.ignite.rentx.rentx.exception.specification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpecificationNotFound extends Exception {

    public SpecificationNotFound(String message) {
        super(message);
    }
}
