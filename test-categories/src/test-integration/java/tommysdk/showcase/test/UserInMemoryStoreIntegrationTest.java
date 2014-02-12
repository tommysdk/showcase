package tommysdk.showcase.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Tommy Tynj&auml;
 */
public class UserInMemoryStoreIntegrationTest {

    private final String expectedNickname = "tt";
    private final String expectedName = "Tommy";

    private InMemoryUserStore userStore;

    @Before
    public void setupResources() {
        userStore = new InMemoryUserStore();
    }

    @Test
    public void shouldSuccessfullyPersistUser() {
        User user = new User(expectedNickname, expectedName);
        userStore.persist(user);
    }

    @Test
    public void shouldThrowExceptionIfTryingToPersistUserThatAlreadyExists() {
        shouldSuccessfullyPersistUser();
        try {
            shouldSuccessfullyPersistUser();
            fail("Expected exception to be raised when trying to persist user that already exists");
        } catch (IllegalStateException ise) {
            // Expected exception
        } catch (Exception e) {
            fail("Unexpected exception type raised when trying to persist user that already exists");
        }
    }

    @Test
    public void shouldFindPersistedUserByNickname() {
        shouldSuccessfullyPersistUser();
        User foundUser = userStore.findByNickname(expectedNickname);
        assertNotNull(foundUser);
        assertEquals(expectedNickname, foundUser.nickname);
        assertEquals(expectedName, foundUser.name);
    }
}
