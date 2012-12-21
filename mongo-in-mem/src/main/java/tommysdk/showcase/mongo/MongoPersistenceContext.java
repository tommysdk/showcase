package tommysdk.showcase.mongo;

import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

import javax.ejb.Singleton;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Tommy Tynj&auml;
 */
@Singleton
public class MongoPersistenceContext {

    public static final String DEFAULT_MONGO_DB = "my_database";
    public static final String DEFAULT_MONGO_COLLECTION = "my_collection";

    private ConcurrentHashMap<String, Mongo> connections = new ConcurrentHashMap<>();

    public Mongo getConnection(final String connectionUrl) {
        if (connectionUrl == null) {
            throw new IllegalArgumentException("Mongo connection url must be specified");
        }
        Mongo connection = connections.get(connectionUrl);
        if (connection == null) {
            try {
                Mongo newConnection = new Mongo(connectionUrl);
                connection = connections.putIfAbsent(connectionUrl, newConnection);
                if (connection == null) {
                    connection = newConnection;
                } else {
                    newConnection.close();
                }
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException("Unknown host specified as Mongo connection url", e);
            }
        }
        return connection;
    }

}
