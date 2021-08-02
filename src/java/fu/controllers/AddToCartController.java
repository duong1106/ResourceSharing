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
import fu.daos.OrderDAO;
import fu.daos.RoomDAO;
import fu.dtos.CartDTO;
import fu.dtos.OrderDetailDTO;
import fu.dtos.RoomDTO;
import fu.dtos.UserDTO;

/**
 *
 * @author Dell
 */
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "invalid.html";
    private final static String SUCCESS = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String roomID = request.getParameter("txtRoomID");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            String checkIn = (String) session.getAttribute("CHECKIN");
            String checkOut = (String) session.getAttribute("CHECKOUT");
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = df.parse(checkIn);
            Date checkOutDate = df.parse(checkOut);
            long totalDate = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
            
            String orderID = OrderDAO.getOderID();           
            int detailID = OrderDAO.getDetailID()+1;
            
            if (cart == null) {
                cart = new CartDTO(orderID, user.getUserID(), 0, "", checkIn, checkOut, null);
            } else {
                if(!checkIn.equals(cart.getCheckIn()) || !checkOut.equals(cart.getCheckOut())){
                    List<RoomDTO> listRooms= RoomDAO.getRooms();
                    for (OrderDetailDTO value : cart.getCart().values()) {
                        for (RoomDTO room : listRooms) {
                            if(room.getRoomID().equals(value.getRoomID())){
                                value.setPrice(room.getPrice()*totalDate);
                            }
                        }
                    }
                    cart.setCheckIn(checkIn);
                    cart.setCheckOut(checkOut);
                }
                if (!cart.getCart().containsKey(roomID)) {
                    detailID = cart.getCart().size() + detailID;
                }
            }
            cart.add(new OrderDetailDTO(orderID, roomID, totalDate * price, "DT" + detailID));
            session.setAttribute("CART", cart);
            request.setAttribute("MESSAGE", "YOU Have booking: " + roomID + " success!");
            url = SUCCESS;
        } catch (Exception e) {
            Logger.getLogger(AddToCartController.class).error(e.getMessage());
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
