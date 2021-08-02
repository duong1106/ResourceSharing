/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import fu.daos.RoomDAO;
import fu.dtos.RoomDTO;
import fu.dtos.RoomErrorDTO;

/**
 *
 * @author Dell
 */
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR="create.jsp";
    private final static String SUCCES="ViewController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try {
            RoomErrorDTO error=new RoomErrorDTO("", "", "", "", "");
            
            String roomID=request.getParameter("txtRoomID");
            String typeRoom=request.getParameter("cbxType");
            String price=request.getParameter("txtPrice");
            String description=request.getParameter("txtDescription");
            String maxPeople=request.getParameter("txtMaxPeople");
            
            boolean check=true;
            if(roomID.isEmpty()){
                check =false;
                error.setRoomIDError("Room ID is not Empty!");
            }else if(!RoomDAO.checkRoomAD(roomID)){
                check=false;
                error.setRoomIDError("Room ID is exsit!!");
            }
            if(price.isEmpty()){
                check=false;
                error.setPriceError("Price is not empty!");
            }
            if(description.isEmpty()){
                check=false;
                error.setDescriptionError("Description is not empty!");
            }
            if(maxPeople.isEmpty()){
                check=false;
                error.setMaxPeopleError("Max people is not empty!");
            }
            if(check){
                RoomDTO room= new RoomDTO(roomID, typeRoom, Float.parseFloat(price), description, Integer.parseInt(maxPeople), false);
                RoomDAO.create(room);
                url=SUCCES;
            }else{
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
            Logger.getLogger(CreateController.class).error(e.getMessage());
        }finally{
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
