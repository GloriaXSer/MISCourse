package com.guitar.action;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guitar.idao.GuitarIDAO;
import com.guitar.model.*;
import com.guitar.sqlitedao.GuitarSqliteDAO;
import com.guitar.utils.AccessFactory;
import com.guitar.utils.JDBCsqlite;

/**
 * Servlet implementation class GuitarPost
 */
@WebServlet("/GuitarPost")
public class GuitarPost extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuitarPost() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String builder = request.getParameter("builder");
		String model = request.getParameter("model");
		String type = request.getParameter("type");
		String backwood = request.getParameter("backwood");
		String topwood = request.getParameter("topwood");
		String numStrings = request.getParameter("numstrings");
					
		//根据查询条件，封装成一个查询对象GuitarSpec
		GuitarSpec guitarspec = new GuitarSpec(Builder.valueOf(builder), model, Type.valueOf(type), Integer.parseInt(numStrings), Wood.valueOf(backwood), Wood.valueOf(topwood));		
		AccessFactory factory = new AccessFactory();
		GuitarIDAO guitardao = factory.createDAO();

		try {
			guitardao.initializeGuitar(guitardao);
			
			List<Guitar> matchingGuitars = guitardao.search(guitarspec);

			request.setAttribute("matchingGuitar", matchingGuitars);
			request.getRequestDispatcher("/searchGuitar.jsp").forward(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
