package controller;

import db.BikeRepository;
import domain.Bike;
import domain.Category;
import domain.DomainException;
import jdk.jshell.spi.ExecutionControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private String destination;

    BikeRepository bikes = new BikeRepository();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie newsletter = findCookie(cookies, "news");
        if(newsletter != null){
            request.setAttribute(newsletter.getName(), newsletter.getValue());
        }

        destination = "index.jsp";
        String command = "";
        command = request.getParameter("command");

        switch (command){
            case "overview":
                destination = overview(request, response);
                break;
            case "detail":
                destination = detail(request, response);
                break;
            case "add":
                destination = "bikeAdd.jsp";
                break;
            case "addBike":
                destination = addBike(request, response);
                break;
            case "news":
                destination = "nieuwsbrief.jsp";
                break;
            case "newsChange":
                destination = newsChange(request, response);
                break;
            case "history":
                destination = history(request, response);
                break;
            default:
                destination = "index.jsp";
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String history(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("bikes", request.getSession().getAttribute("history"));
        return "overview.jsp";
    }

    private String newsChange(HttpServletRequest request, HttpServletResponse response) {
        Cookie newsletter = new Cookie("news", request.getParameter("nieuwsbrief"));
        response.addCookie(newsletter);
        request.setAttribute("news", request.getParameter("nieuwsbrief"));
        return "index.jsp";
    }

    private String detail(HttpServletRequest request, HttpServletResponse response) {
        String BikeId = request.getParameter("id");
        request.setAttribute("bike", bikes.get(BikeId));

        ArrayList<Bike> bikeHistory = (ArrayList<Bike>) request.getSession().getAttribute("history");
        if(bikeHistory == null){
            bikeHistory = new ArrayList<>();
        }
        bikeHistory.add(bikes.get(request.getParameter("id")));
        request.getSession().setAttribute("history", bikeHistory);

        return "bikeDetail.jsp";
    }

    private String addBike(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        Bike bike = new Bike();
        setId(bike,request,errors);
        setCat(bike,request,errors);
        setBrand(bike,request,errors);
        setDesc(bike,request,errors);
        setPrice(bike,request,errors);

        if(errors.size() == 0){
            bikes.add(bike);
            return overview(request, response);
        }else{
            request.setAttribute("errors", errors);
            return "bikeAdd.jsp";
        }
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("bikes", bikes.getAll());

        return "bikeOverview.jsp";

    }

    protected void setId(Bike bike, HttpServletRequest request, ArrayList<String> errors){
        try{
            if(bikes.get(request.getParameter("itemId")) != null){
                throw new DomainException("Dit id is niet meer beschikbaar.");
            }
            bike.setItemId(request.getParameter("itemId"));
            request.setAttribute("previd", request.getParameter("itemId"));
        }catch (DomainException e){
            errors.add(e.getMessage());
        }
    }

    protected void setCat(Bike bike, HttpServletRequest request, ArrayList<String> errors){
        try{
            bike.setCategory(Category.valueOf(request.getParameter("category")));
            request.setAttribute("prevcat", request.getParameter("category"));
        }catch (DomainException e){
            errors.add(e.getMessage());
        }catch (IllegalArgumentException eg){
            errors.add(eg.getMessage() + "ROAD / CITY");
        }
    }

    protected void setBrand(Bike bike, HttpServletRequest request, ArrayList<String> errors){
        try{
            bike.setBrand(request.getParameter("brand"));
            request.setAttribute("prevbrand", request.getParameter("brand"));
        }catch (DomainException e){
            errors.add(e.getMessage());
        }
    }

    protected void setDesc(Bike bike, HttpServletRequest request, ArrayList<String> errors){
        try{
            bike.setDescription(request.getParameter("description"));
            request.setAttribute("prevdesc", request.getParameter("description"));
        }catch (DomainException e){
            errors.add(e.getMessage());
        }
    }

    protected void setPrice(Bike bike, HttpServletRequest request, ArrayList<String> errors){
        try{
            bike.setPrice(Double.parseDouble(request.getParameter("price")));
            request.setAttribute("prevprice", request.getParameter("price"));
        }catch (DomainException e){
            errors.add(e.getMessage());
        }catch (NumberFormatException eg){
            errors.add(eg.getMessage());
        }
    }

    protected Cookie findCookie(Cookie[] c, String key){
        if(c == null || c.length == 0){return null;}
        for(Cookie co : c){
            if(co.getName().equals(key)){
                return co;
            }
        }
        return null;
    }
}
