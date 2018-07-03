package next.web;

import core.db.DataBase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(value = "/user/update")
public class UpdateUserServlet extends HttpServlet {

  private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {
    final String userId = req.getParameter("userId");
    final User user = DataBase.findUserById(userId);
    User newUser = new User(req.getParameter("userId"), req.getParameter("password"),
        req.getParameter("name"),
        req.getParameter("email"));
    log.debug("user : {}", user);
    user.update(newUser);
    resp.sendRedirect("/");
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {
    final String userId = req.getParameter("userId");
    req.setAttribute("user", DataBase.findUserById(userId));
    final RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
    rd.forward(req, resp);
  }
}
