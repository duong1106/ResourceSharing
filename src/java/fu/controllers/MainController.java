/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.controllers;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Dell
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR="invalid.html";
    private final static String LOGIN="LoginController";
    private final static String LOGOUT="SignOutController";
    private final static String SIGN_UP_PAGE="signup.jsp";
    private final static String SIGN_UP="SignUpController";
    private final static String SEARCH="SearchController";
    private final static String ADD="AddToCartController";
    private final static String VIEW_BOOKING="viewcart.jsp";
    private final static String VIEW_SEARCH="search.jsp";
    private final static String REMOVE="RemoveCartController";
    private final static String DELETE="DeleteController";
    private final static String CHECKOUT="CheckOutController";
    private final static String UPDATE="UpdateController";
    private final static String CREATE="CreateController";
    private final static String CREATE_PAGE="create.jsp";
    private final static String AD_VIEW="ViewController";
    private final static String LOGINGG="LoginGGController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;       
        try {
            Properties properties = new Properties();
			properties.setProperty("log4j.rootLogger", "TRACE,stdout,MyFile");
			properties.setProperty("log4j.rootCategory", "TRACE");
			properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
			properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
			properties.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
			properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
			properties.setProperty("log4j.appender.MyFile.File", "D:\\CN4\\PRJ301\\Code WEB\\SE141124\\log4j.log"); // doi duong dẫn
			properties.setProperty("log4j.appender.MyFile.MaxFileSize", "1MB");
			properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
			properties.setProperty("log4j.appender.MyFile.layout", "org.apache.log4j.PatternLayout");
			properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern", "%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
			PropertyConfigurator.configure(properties);
            String action=request.getParameter("btnAction"); 
            if("Login".equals(action)){
                url=LOGIN;    
            }else 
                if("Sign Out".equals(action)){
                url=LOGOUT;
            }else if("Sign Up".equals(action)){
                url=SIGN_UP_PAGE;
            }else if("Sign Up New".equals(action)){
                url=SIGN_UP;
            }else if("Search".equals(action)){
                url=SEARCH;
            }else if("Booking Now".equals(action)){ 
                url=ADD;
            }else if("View Booking".equals(action)){
                url=VIEW_BOOKING;
            }else if("Remove".equals(action)){
                url=REMOVE;
            }else if("Delete".equals(action)){
                url=DELETE;
            }else if("Check Out".equals(action)){
                url=CHECKOUT;
            }else if("Booking more".equals(action)){
                url=VIEW_SEARCH;
            }else if("Update".equals(action)){
                url=UPDATE;
            }else if("Create".equals(action)){
                url=CREATE;
            }else if("CreateRooms".equals(action)){
                url=CREATE_PAGE;
            }else if("ViewAD".equals(action)){
                url=AD_VIEW;
            }else if("LoginGG".equals(action)){
                url=LOGINGG;
            }
                    
        } catch (Exception e) {
            Logger.getLogger(MainController.class).error(e.getMessage());
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
