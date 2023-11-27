package exceptions;

import domain.Person;

public class PersonIllegalArgumentException extends IllegalArgumentException {
    public PersonIllegalArgumentException(String msg) {
        super(msg);
    }
}
