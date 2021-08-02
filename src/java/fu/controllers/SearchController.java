/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import fu.daos.RoomDAO;
import fu.dtos.RoomDTO;
import fu.dtos.SearchErrorDTO;

/**
 *
 * @author Dell
 */
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "search.jsp";
    private static final String SUCCESS = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String checkIn = request.getParameter("txtCheckIn");
            String checkOut = request.getParameter("txtCheckOut");
            String priceMax = request.getParameter("txtPriceMax");
            String priceMin = request.getParameter("txtPriceMin");
            String maxPeople = request.getParameter("txtMaxPeople");
            HttpSession session = request.getSession();
            SearchErrorDTO objErrorDTO = new SearchErrorDTO("", "", "", "", "");

            boolean check = true;
            if (checkIn.isEmpty()) {
                check = false;
                objErrorDTO.setCheckInError("Input check in!");
            }
            if (checkOut.isEmpty()) {
                check = false;
                objErrorDTO.setCheckOutError("Input check out!");
            }
            if (!checkIn.isEmpty() && !checkOut.isEmpty()) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date checkInDate = df.parse(checkIn);
                Date checkOutDate = df.parse(checkOut);
                long totalDate = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
                if (totalDate <= 0) {
                    check = false;
                    objErrorDTO.setCheckOutError("CheckOut date > checkIn date!!!");
                }
            }
            if (priceMax.isEmpty()) {
                check = false;
                objErrorDTO.setPriceMaxError("Not empty!");
            }
            if (priceMin.isEmpty()) {
                check = false;
                objErrorDTO.setPriceMinError("Not empty!");
            }
            if (!priceMax.isEmpty() && !priceMin.isEmpty()) {
                if (Float.parseFloat(priceMin) > Float.parseFloat(priceMax)) {
                    check = false;
                    objErrorDTO.setPriceMaxError("Price Max > price min");
                }
            }
            if (maxPeople.isEmpty()) {
                check = false;
                objErrorDTO.setMaxPeopleError("Not empty!");
            }
            if (check) {
                session.setAttribute("CHECKIN", checkIn);
                session.setAttribute("CHECKOUT", checkOut);
                session.setAttribute("PRICEMAX", priceMax);
                session.setAttribute("PRICEMIN", priceMin);
                session.setAttribute("MAXPEOPLE", maxPeople);
                RoomDAO dao = new RoomDAO();
                List<RoomDTO> list = dao.getListRooms(checkIn, checkOut, priceMax, priceMin, maxPeople);
                if (list != null) {
                    session.setAttribute("LISTROOMS", list);
                } else {
                    session.setAttribute("LISTROOMS", list);
                    request.setAttribute("MESSAGE", "The room you are looking for is currently unavailable!");
                }
                url = SUCCESS;
            } else {
                session.setAttribute("LISTROOMS", null);
                request.setAttribute("SEARCHERROR", objErrorDTO);
            }

        } catch (Exception e) {
            Logger.getLogger(SearchController.class).error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
