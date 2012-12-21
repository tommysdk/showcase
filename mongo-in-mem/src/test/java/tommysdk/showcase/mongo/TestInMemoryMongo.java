package tommysdk.showcase.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static tommysdk.showcase.mongo.MongoPersistenceContext.DEFAULT_MONGO_COLLECTION;
import static tommysdk.showcase.mongo.MongoPersistenceContext.DEFAULT_MONGO_DB;

/**
 * @author Tommy Tynj&auml;
 */
public class TestInMemoryMongo {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27777;
    private static final String IN_MEM_CONNECTION_URL = MONGO_HOST + ":" + MONGO_PORT;

    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    private Mongo mongo;

    /**
     * Start in-memory Mongo DB process
     */
    @Before
    public void setup() throws Exception {
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        mongodExe = runtime.prepare(new MongodConfig(Version.V2_0_5, MONGO_PORT, Network.localhostIsIPv6()));
        mongod = mongodExe.start();
        mongo = new Mongo(MONGO_HOST, MONGO_PORT);
    }

    /**
     * Shutdown in-memory Mongo DB process
     */
    @After
    public void teardown() throws Exception {
        if (mongod != null) {
            mongod.stop();
            mongodExe.stop();
        }
    }

    @Test
    public void shouldAssertSave() {
        Mongo m = getMongoPersistenceContext().getConnection(IN_MEM_CONNECTION_URL);
        DBCollection c = m.getDB(DEFAULT_MONGO_DB).getCollection(DEFAULT_MONGO_COLLECTION);
        long documentBefore = c.count();

        DBObject dbo = toDocument(new Tuple("key", "value"));
        c.insert(dbo);

        assertEquals(documentBefore + 1, c.count());
        assertNotNull(dbo.get("_id"));
        System.out.println("id of saved document: " + dbo.get("_id"));
    }

    protected MongoPersistenceContext getMongoPersistenceContext() {
        return new MongoPersistenceContext();
    }

    public DBObject toDocument(final Tuple... tuples) {
        DBObject dbo = new BasicDBObject();
        if (tuples != null)
            for (Tuple t : tuples)
                dbo.put(t.key, t.value);
        return dbo;
    }

    final class Tuple {
        public final String key;
        public final Object value;

        public Tuple(final String key, final String value) {
            this.key = key;
            this.value = value;
        }
    }
}
