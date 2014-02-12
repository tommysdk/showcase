package tommysdk.showcase.test;

/**
 * @author Tommy Tynj&auml;
 */
public class User {

    public final String nickname;
    public final String name;

    public User(final String nickname, final String realname) {
        this.nickname = nickname;
        this.name = realname;
    }

    @Override
    public String toString() {
        return name + " (" + nickname + ')';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!nickname.equals(user.nickname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
