package tommysdk.showcase.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tommy Tynj&auml;
 */
public final class InMemoryUserStore {

    private final ConcurrentHashMap<String, User> nicknameToUserMap = new ConcurrentHashMap<>();

    public void persist(final User user) throws IllegalStateException {
        if (nicknameToUserMap.putIfAbsent(user.nickname, user) != null) {
            throw new IllegalStateException("User with nickname \"" + user.nickname + "\" does already exist. Use alternative API method to overwrite existing value");
        }
    }

    public User findByNickname(final String nickname) {
        return nicknameToUserMap.get(nickname);
    }
}
