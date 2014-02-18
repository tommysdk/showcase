package tommysdk.showcase.togglz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Tommy Tynj&auml;
 */
@WebServlet(urlPatterns = {"/index.html", "/"})
public class ShowcaseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShowcaseServlet.class.getName());

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        LOG.info("Showcase servlet received request from " + req.getRemoteAddr());
        writeHtml(res);
        setHtmlContentTypeAndClose(res);
    }

    private void writeHtml(final HttpServletResponse res) throws IOException {
        write("<html><body>", res);
        write("<h1>Showcase servlet</h1>", res);

        if (FeatureDefinition.APPLICATION_DESCRIPTION.isActive()) {
            write("<p>Example application to demonstrate the use of feature flags using the togglz framework</p>", res);
        }

        write("<p>Access the togglz admin console by adding /togglz to the application context path in the URL. Security has been disabled.</p>", res);

        if (FeatureDefinition.AUTHOR_NAME_IS_VISIBLE.isActive()) {
            write("<p>by Tommy Tynj&auml;</p>", res);
        }

        write("</body></html>", res);
    }

    private void write(final String html, final HttpServletResponse response) throws IOException {
        response.getWriter().println(html);
    }

    private void setHtmlContentTypeAndClose(final HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().close();
    }
}
