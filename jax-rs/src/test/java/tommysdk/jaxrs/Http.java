package tommysdk.jaxrs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Tommy Tynj&auml;
 */
public class Http {

    public static String get(final String url, final String requestString) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url + requestString);
            System.out.println("Executing http get request to " + url + requestString);

            HttpResponse response = null;
            response = client.execute(request);

            BufferedReader reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
            String responseString = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                responseString += line;
            }
            return responseString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
