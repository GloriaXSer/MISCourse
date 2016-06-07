package com.guitar.action;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class GuitarAdd
 */
@WebServlet("/GuitarAdd")
public class GuitarAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuitarAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String serialNumber = request.getParameter("serialnumber");
		String builder = request.getParameter("builder");
		String model = request.getParameter("model");
		String type = request.getParameter("type");
		String backwood = request.getParameter("backwood");
		String topwood = request.getParameter("topwood");
		String price = request.getParameter("price");
		String numStrings = request.getParameter("numstrings");

		
		AccessFactory factory = new AccessFactory();
		GuitarIDAO guitar = factory.createDAO();
		List<Guitar> allGuitars = null;
		try {
			guitar.addGuitar(serialNumber, backwood, topwood, Double.parseDouble(price), builder, model, type, Integer.parseInt(numStrings));
			allGuitars = guitar.findAll();
			request.setAttribute("allGuitars", allGuitars);
			request.getRequestDispatcher("/editGuitar.jsp").forward(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
