package tommysdk.tdd;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Tommy Tynj&auml;
 */
public class TestEmailAddress {

    @Test
    public void shouldValidateValidEmailAddress() {
        String a = "firstname.lastname@diabol.se";
        assertTrue(EmailAddress.isValid(a));
    }

    @Test
    public void shouldNotValidateInvalidEmailAddress() {
        assertFalse(EmailAddress.isValid("invalid_address"));
    }

}
