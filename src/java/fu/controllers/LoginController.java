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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import fu.daos.UserDAO;
import fu.dtos.UserDTO;
import fu.recaptcha.VerifyUtils;

/**
 *
 * @author Dell
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String US = "search.jsp";
    private final static String AD = "ViewController";
    private final static String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("txtUserID");
            if (userID.isEmpty()) {
                request.setAttribute("ERRORID", "Input user ID");
            }
            String password = request.getParameter("txtPassword");
            if (password.isEmpty()) {
                request.setAttribute("ERRORPS", "Enter password");
            }

            boolean check = true;
            if (!userID.isEmpty() && !password.isEmpty()) {
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                check = VerifyUtils.verify(gRecaptchaResponse);
                if (!check) {
                    request.setAttribute("RECERROR", "Miss Captcha!");
                } else {
                    UserDAO dao = new UserDAO();
                    UserDTO user = dao.checkLogin(userID, password);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_USER", user);
                        if ("US".equals(user.getRoleID())) {
                            url = US;
                        } else if ("AD".equals(user.getRoleID())) {
                            url = AD;
                        }
                    } else {
                        request.setAttribute("ERRORACC", "Wrong userID or password");
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class).error(e.getMessage());
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
