package webapp.functionality;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/Submit")
public class MainApiServlet extends HttpServlet {
    StringFormatter stringProcessor = new StringFormatter();
    InputValidator inputValidator = new InputValidator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

         String bankcode = request.getParameter("bankcode");
         String countrycode = request.getParameter("countrycode");
         boolean validationStatus = inputValidator.validateInputCodes(countrycode,bankcode);
         if(!validationStatus) response.sendError(400);
         else {
             response.setContentType("text/plain");
             String answer = stringProcessor.formatInput(bankcode, countrycode);
             response.getWriter().write(answer);
         }
    }
}

