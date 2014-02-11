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
}
