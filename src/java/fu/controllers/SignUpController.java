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
import fu.daos.UserDAO;
import fu.dtos.UserDTO;
import fu.dtos.UserErrorDTO;

/**
 *
 * @author Admin
 */
public class SignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String SUCCESS = "login.jsp";
    private final static String ERROR = "signup.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserErrorDTO objError = new UserErrorDTO("", "", "", "", "", "", "", "");
        try {
            String userID = request.getParameter("txtUserID");
            String fullName = request.getParameter("txtFullName");
            String identityCard = request.getParameter("txtIdentityCard");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (userID.isEmpty()) {
                check = false;
                objError.setUserIDError("User ID is not empty!");
            } else if (userID.length() > 30) {
                check = false;
                objError.setUserIDError("User ID max lenght is 10!");
            } else if (!dao.checkUserID(userID)) {
                check = false;
                objError.setUserIDError("User ID is exist!");
            }

            if (fullName.isEmpty()) {
                check = false;
                objError.setFullNameError("Full Name is not empty!");
            } else if (fullName.length() > 50) {
                check = false;
                objError.setFullNameError("Full Name max lenght is 50!");
            }

            if (identityCard.isEmpty()) {
                check = false;
                objError.setIdentityCardError("Identity Card is not empty!");
            } else if (identityCard.length() != 9 && identityCard.length() != 12) {
                check = false;
                objError.setIdentityCardError("Identity Card has lenght is 9 or 12!");
            }

            if (password.isEmpty()) {
                check = false;
                objError.setPasswordError("Password is not empty!");
            } else if (password.length() > 20) {
                check = false;
                objError.setPasswordError("Password mac lenght is 20!");
            }

            if (!confirm.equals(password)) {
                check = false;
                objError.setConfirmError("Passwords did not match!");
            }

            if (phone.isEmpty()) {
                check = false;
                objError.setPhoneError("Phone is not empty!");
            } else if (phone.length() != 10 && phone.length() != 11) {
                check = false;
                objError.setPhoneError("Phone lenght is 10 or 11!");
            }

            if (email.isEmpty()) {
                check = false;
                objError.setEmailError("Email is not empty!");
            } else if (email.length() > 50) {
                check = false;
                objError.setEmailError("Email max lenght is 50!");
            }

            if (address.isEmpty()) {
                check = false;
                objError.setAddressError("Address is not empty!");
            } else if (address.length() > 300) {
                check = false;
                objError.setAddressError("Address max lenghr is 300!");
            }

            if (check) {
                UserDTO user = new UserDTO(userID, password, fullName, identityCard, email, phone, address, "US", false);
                dao.signUp(user);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", objError);
            }

        } catch (Exception e) {
            Logger.getLogger(SignUpController.class).error(e.getMessage());
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
