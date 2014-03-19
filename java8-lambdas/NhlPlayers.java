import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class NhlPlayers {

    public static void main(final String args[]) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected path to player file as argument");
        }
        Stream<String> lines = Files.lines(FileSystems.getDefault().getPath(args[0]));
        lines.map(NhlPlayers::createPlayer)
                .sorted((p1, p2) -> new Integer(p1.number).compareTo(p2.number))
                .forEach(System.out::println);
    }

    public static Player createPlayer(final String row) {
        StringTokenizer tok = new StringTokenizer(row, " \t\r\n");
        int no = Integer.parseInt(tok.nextToken().trim());
        String name = tok.nextToken() + " " + tok.nextToken();
        String token = tok.nextToken();
        String captain = null;
        String position;
        if (token.matches("\\([AC]\\)")) {
            captain = token;
            token = null;
        }
        position = token != null ? token : tok.nextToken();
        String shoots = tok.nextToken();
        int age = Integer.parseInt(tok.nextToken().trim());
        String aquired = tok.nextToken();
        String birthPlace = "";
        while (tok.hasMoreTokens()) {
            birthPlace += tok.nextToken();
            if (tok.hasMoreTokens()) {
                birthPlace += " ";
            }
        }
        return new Player(no, name, position, captain, shoots, age, aquired, birthPlace);
    }

    static class Player {

        public final int number;
        public final String name;
        public final String position;
        public final String captain;
        public final String shoots;
        public final int age;
        public final String aquired;
        public final String birthPlace;

        Player(final int number, final String name, final String position, final String captain, final String shoots, final int age, final String aquired, final String birthPlace) {
            this.number = number;
            this.name = name;
            this.position = position;
            this.captain = captain;
            this.shoots = shoots;
            this.age = age;
            this.aquired = aquired;
            this.birthPlace = birthPlace;
        }

        @Override
        public String toString() {
            return position + " #" + number + " " + name;
        }
    }
}