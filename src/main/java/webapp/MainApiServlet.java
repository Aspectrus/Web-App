package webapp;


import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns="/Submit")
public class MainApiServlet extends HttpServlet {
    JSONParser parser = new JSONParser();
    StringFormatter stringProcessor = new StringFormatter();
    InputValidator inputValidator = new InputValidator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

         String bankcode = request.getParameter("bankcode");
         String countrycode = request.getParameter("countrycode");
         boolean ValidationStatus = inputValidator.ValidateInputCodes(countrycode,bankcode);
         if(!ValidationStatus) response.sendError(404);
         else {
             response.setContentType("text/plain");
             String answer = stringProcessor.FormatInput(bankcode, countrycode);
             response.getWriter().write(answer);
         }
    }
}

