package com.github.hsoj48.validation;

import java.util.Date;
import java.util.List;

public class DateFieldValidator extends AbstractFieldValidator<Date, DateFieldValidator> {

    DateFieldValidator(ValidationHandler handler, List<String> fieldNames, Date value) {
        super(handler, fieldNames, value);
    }

    public DateFieldValidator isBeforeNow() {
        return isBefore(new Date());
    }

    public DateFieldValidator isAfterNow() {
        return isAfter(new Date());
    }

    public DateFieldValidator isBefore(Date other) {
        return isTrue(o -> other != null && o.before(other));
    }

    public DateFieldValidator isAfter(Date other) {
        return isTrue(o -> other != null && o.after(other));
    }

    @Override
    protected DateFieldValidator getThis() {
        return this;
    }

}
