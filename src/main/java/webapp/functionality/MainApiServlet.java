package webapp.functionality;


import org.json.simple.parser.ParseException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/Submit")
public class MainApiServlet extends HttpServlet {
    StringFormatter stringFormatter;
    InputValidator inputValidator;

    public MainApiServlet() throws IOException, ParseException {
         stringFormatter = new StringFormatter();
         inputValidator = new InputValidator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

         String bankcode = request.getParameter("bankcode");
         String countrycode = request.getParameter("countrycode");
         boolean validationStatus = inputValidator.validateInputCodes(countrycode,bankcode);
         if(!validationStatus) response.sendError(400);
         else {
             response.setContentType("text/plain");
             String answer = stringFormatter.formatInput(bankcode, countrycode);
             response.getWriter().write(answer);
         }
    }
}

