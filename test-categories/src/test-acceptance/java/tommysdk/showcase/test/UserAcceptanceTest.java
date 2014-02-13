package tommysdk.showcase.test;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * @author Tommy Tynj&auml;
 */
public class UserAcceptanceTest {

    @Test
    public void stringRepresentationShouldIncludeFullNameAndNickname() {
        final String expectedNickname = "tt";
        final String expectedName = "Tommy";
        final User user = new User(expectedNickname, expectedName);

        assertThat(user.toString(), containsString(expectedNickname));
        assertThat(user.toString(), containsString(expectedName));
    }

}
