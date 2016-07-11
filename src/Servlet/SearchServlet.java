package Servlet;

import DataBase.DataBaseRunner;
import DataBase.MySqlInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    private static DataBaseRunner dbRunner = DataBaseRunner.getInstance();

    {
        System.out.println("DB Connection");
        dbRunner.connect( MySqlInfo.DATABASE_NAME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String district    = req.getParameter("textDistrict");
        String street      = req.getParameter("textStreet");
        String roomQtyFrom = req.getParameter("textRoomsFrom");
        String roomQtyTo   = req.getParameter("textRoomsTo");
        String priceFrom   = req.getParameter("textPriceFrom");
        String priceTo     = req.getParameter("textPriceTo");


        roomQtyFrom = checkIntValue(roomQtyFrom, "1");
        roomQtyTo   = checkIntValue(roomQtyTo, "100");

        priceFrom = checkFloatValue(priceFrom, "0.00");
        priceTo   = checkFloatValue(priceTo, "1000000000.00");


        resp.getWriter().print(
                dbRunner.select( district, street, roomQtyFrom, roomQtyTo, priceFrom, priceTo)
        );
    }

    private String checkIntValue(String inputValue, String defaultValue){

        try {

            if ( Integer.parseInt(inputValue)<0)
                return defaultValue;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }

        return inputValue;
    }

    private String checkFloatValue(String inputValue, String defaultValue){

        try {

            if ( Float.parseFloat( inputValue)<0)
                return defaultValue;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }

        return inputValue;
    }
}
