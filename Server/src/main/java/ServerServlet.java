import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ServerServlet", value = "/swipe/*")
public class ServerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json");
        Gson gson = new Gson();


        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }

        Request req = (Request) gson.fromJson(sb.toString(), Request.class);


        if (!isUrlValid(req)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            // do any sophisticated processing with urlParts which contains all the url params
            // TODO: process url params in `urlParts`
            response.getWriter().write("It works!");
        }
    }


    private boolean isUrlValid(Request req) {




        if (Integer.valueOf(req.getSwiper()) < 1 || Integer.valueOf(req.getSwiper()) > 5000) {
            return false;
        }

        if (Integer.valueOf(req.getSwipee()) < 1 || Integer.valueOf(req.getSwipee()) > 1000000) {
            return false;
        }

        if (req.getComment().length() > 256) {
            return false;
        }

        return true;
    }


}
