/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 123
 */
public class processTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String table = request.getParameter("tableName");
            String[] column = request.getParameter("column").split(", ");
            String col = request.getParameter("column");
            String con = request.getParameter("con");
            
            ArrayList result = select(table, column, con);
            for (int i = 0; i < result.size(); i++) {
                out.println(result.get(i) + "<br>");
            }
        }
    }
    
    public ArrayList select(String table, String[] column, String condition) throws ClassNotFoundException {
        ArrayList<ArrayList> result = new ArrayList<ArrayList>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement stmt = connection.createStatement();
            String sql = "";
            String col = "";
            int count = column.length;

            for (int i = 0; i < column.length; i++) {
                if (i == column.length - 1) {
                    col += column[i];
                } else {
                    col += column[i] + ", ";
                }
            }

            if (!condition.equals("")) {
                sql = "SELECT " + col + " FROM " + table + " WHERE " + condition;
            } else {
                sql = "SELECT " + col + " FROM " + table;
            }

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsd = rs.getMetaData();

            while (rs.next()) {
                ArrayList al = new ArrayList();
                for (int j = 1; j <= count; j++) {
                    String s = rsd.getColumnTypeName(j);
                    if (s.equals("INT")) {
                       al.add(rs.getInt(rsd.getColumnName(j)));
                    } else if(s.equals("DOUBLE")) {
                        al.add(rs.getDouble(rsd.getColumnName(j)));
                    }else{
                        al.add(rs.getString(rsd.getColumnName(j)));
                    }
                    
                }
                result.add(al);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(processTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(processTable.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(processTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(processTable.class.getName()).log(Level.SEVERE, null, ex);
        }
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
