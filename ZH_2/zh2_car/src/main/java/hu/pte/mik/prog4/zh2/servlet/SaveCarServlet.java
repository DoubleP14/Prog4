package hu.pte.mik.prog4.zh2.servlet;

import hu.pte.mik.prog4.zh2.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveCarServlet extends HttpServlet {

    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        if (id != null) {
            var car = this.carService.findById(Long.valueOf(id));
            req.setAttribute("car", car);
        }
        req.getRequestDispatcher("/saveCar.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        var idParam = req.getParameter("id");
        var id = idParam == null || "".equals(idParam) ? null : Long.valueOf(idParam);
        var manufacturer = req.getParameter("manufacturer");
        var type = req.getParameter("type");
        var licensePlate = req.getParameter("licensePlate");
        this.carService.save(id, manufacturer, type, licensePlate);

        resp.sendRedirect("car-list");
    }

}
