/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet("/admin/data-provider")
public class DataProviderServletControllerJsp extends HttpServlet {

    @Autowired
    private TripService tripService;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.tripService = context.getBean(TripService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String yearParam = request.getParameter("year");
//        int selectedYear = Integer.parseInt(yearParam);
//        System.out.println("------------------------------demo year ne");
//                System.out.println(selectedYear);

        List<TripChartResponse> routeList = this.tripService.getListRouteCountsInTrip();

        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(routeList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }
}
