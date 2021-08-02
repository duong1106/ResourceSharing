/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import fu.daos.RoomDAO;
import fu.dtos.RoomDTO;

/**
 *
 * @author Dell
 */
public class UpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ADMIN = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String roomID = request.getParameter("txtRoomID");
            String typeID = request.getParameter("cbxType");
            String price = request.getParameter("txtPrice");
            String description = request.getParameter("txtDescription").substring(1);
            String maxPeople = request.getParameter("txtMaxPeople");
            String txtDelete = request.getParameter("txtIsDelete");
            boolean isDelete=false;
            boolean check = true;
            if (txtDelete != null) {
                if (!RoomDAO.checkRoomsAD(roomID)) {
                    check = false;
                    request.setAttribute("ERRORDELETE", "Room " + roomID + " has been booked!!");
                }else{
                    isDelete=true;
                }
                
            }
            if (price.isEmpty()) {
                check = false;
                request.setAttribute("ERRORPRICE", "Price is not empty");
            }
            if (maxPeople.isEmpty()) {
                check = false;
                request.setAttribute("ERRORMP", "Max people is not empty");
            }
            if (description.isEmpty()) {
                check = false;
                request.setAttribute("ERRORDES","Description is not emty");
            }
            if (check) {
                RoomDTO room = new RoomDTO(roomID, typeID, Float.parseFloat(price), description, Integer.parseInt(maxPeople), isDelete);
                RoomDAO.update(room);
                List<RoomDTO> rooms = RoomDAO.getRoomsAD();
                HttpSession session = request.getSession();
                session.setAttribute("ROOMS", rooms);
                request.setAttribute("UPDATE" , "Update Success");
            }
        } catch (Exception e) {
            Logger.getLogger(UpdateController.class).error(e.getMessage());
        }finally {
            request.getRequestDispatcher(ADMIN).forward(request, response);
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
