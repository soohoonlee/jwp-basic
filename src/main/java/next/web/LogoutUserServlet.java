package next.web;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(value = "/user/logout")
public class LogoutUserServlet extends HttpServlet {

  private static final Logger log = LoggerFactory.getLogger(LogoutUserServlet.class);

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws IOException {
    final HttpSession session = req.getSession();
    log.debug("logout...");
    session.invalidate();
    resp.sendRedirect("/");
  }
}
