package next.web;

import core.db.DataBase;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(value = "/user/login")
public class LoginUserServlet extends HttpServlet {

  private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

  @Override
  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
      throws IOException {
    final String userId = req.getParameter("userId");
    final String password = req.getParameter("password");
    log.debug("userId: {}, password: {}", userId, password);
    final User user = DataBase.findUserById(userId);
    if (user != null && user.isPasswordEqual(password)) {
      final HttpSession session = req.getSession();
      session.setAttribute("user", user);
      log.debug("login success..");
      resp.sendRedirect("/");
    } else {
      log.debug("login failed..");
      resp.sendRedirect("/user/login_failed.html");
    }
  }
}
