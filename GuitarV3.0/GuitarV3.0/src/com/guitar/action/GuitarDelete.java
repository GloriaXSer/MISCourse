package com.guitar.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guitar.idao.GuitarIDAO;
import com.guitar.model.Guitar;
import com.guitar.sqlitedao.GuitarSqliteDAO;
import com.guitar.utils.AccessFactory;

/**
 * Servlet implementation class GuitarDelete
 */
@WebServlet("/GuitarDelete")
public class GuitarDelete extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuitarDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String serialNumber = request.getParameter("serialNumber");
		AccessFactory factory = new AccessFactory();
		GuitarIDAO guitar = factory.createDAO();
		List<Guitar> allGuitars = null;
		try {
			guitar.deleteGuitar(serialNumber);
			allGuitars = guitar.findAll();
			request.setAttribute("allGuitars", allGuitars);
			request.getRequestDispatcher("/editGuitar.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
