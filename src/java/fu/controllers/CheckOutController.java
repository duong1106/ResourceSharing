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
import fu.daos.EmailDAO;
import fu.daos.OrderDAO;
import fu.daos.RoomDAO;
import fu.dtos.CartDTO;
import fu.dtos.OrderDetailDTO;
import fu.dtos.RoomDTO;
import fu.dtos.UserDTO;

/**
 *
 *@author Dell
 */
public class CheckOutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "viewcart.jsp";
    private final static String SUCCESS = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            RoomDAO dao = new RoomDAO();
            String checkIn = (String) session.getAttribute("CHECKIN");
            String checkOut = (String) session.getAttribute("CHECKOUT");
            List<RoomDTO> listRoom = dao.getListRoomsToCheck(checkIn, checkOut);
            List<RoomDTO> list = (List<RoomDTO>) session.getAttribute("LISTROOMS");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            boolean check = true;
            int find = -1;
            float total = 0;
            for (OrderDetailDTO value : cart.getCart().values()) {
                find = -1;
                for (int i = 0; i < listRoom.size(); i++) {
                    if (value.getRoomID().equals(listRoom.get(i).getRoomID())) {
                        find = i;
                    }
                }
                if (find == -1) {
                    check = false;
                    request.setAttribute("MESSAGE", value.getRoomID() + "da duoc dat");
                }
                total += value.getPrice();
            }
            cart.setPrice(total);
            if (check) {
                check = EmailDAO.sendMail(user.getEmail(), "Booking successful", "ID: " + cart.getOrderID() + "\nCheck In: " + cart.getCheckIn() + "\nCheck Out: " + cart.getCheckOut()+"\nPrice: "+(int)total+"VND");
                if (check) {
                    OrderDAO.createOrder(cart);
                    for (OrderDetailDTO detail : cart.getCart().values()) {
                        OrderDAO.createOrderDetail(detail);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getRoomID().equals(detail.getRoomID())) {
                                list.remove(i);
                            }
                        }
                    }
                    session.setAttribute("CART", null);
                    session.setAttribute("LISTROOMS", list);
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Check Out success!");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CheckOutController.class).error(e.getMessage());
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
