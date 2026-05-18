package hu.pte.mik.prog4.zh2_2026.servlet;

import hu.pte.mik.prog4.zh2_2026.entity.CarEntity;
import hu.pte.mik.prog4.zh2_2026.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CarSaveServlet extends HttpServlet {

    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            CarEntity car = this.carService.getById(Long.valueOf(id));
            req.setAttribute("car", car);
        }
        req.getRequestDispatcher("/carSave.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idParam = req.getParameter("id");
        Long id = idParam == null || "".equals(idParam) ? null : Long.valueOf(idParam);
        String type = req.getParameter("type");
        String model = req.getParameter("model");
        String productionYear = req.getParameter("productionYear");
        String listPrice = req.getParameter("listPrice");
        this.carService.save(new CarEntity(id, type, model, productionYear, listPrice));

        resp.sendRedirect("car-list");
    }

}
