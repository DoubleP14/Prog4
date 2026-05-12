package hu.pte.mik.prog4.zh2.servlet;

import hu.pte.mik.prog4.zh2.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CarListServlet extends HttpServlet {

    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", this.carService.findAll());
        req.getRequestDispatcher("/carList.jsp")
           .forward(req, resp);
    }

}
