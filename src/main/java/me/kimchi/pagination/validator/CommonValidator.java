package me.kimchi.pagination.validator;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CommonValidator {

    public boolean validatePositive(int... integers) throws Exception {
        int[] target = integers;

        Arrays.stream(target).forEach(num -> {
            if (num < 0) {
                throw new InvalidParameterException("Parameter integers are cannot under 0.");
            }
        });

        return true;
    }

}
