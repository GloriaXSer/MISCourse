package com.system.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.service.SectionService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MySectionsAction
 */
@WebServlet("/MySectionsAction")
public class MySectionsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySectionsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		SectionService sectionService = new SectionService();
			try {
				JSONArray jsonArray = JSONArray.fromObject(sectionService.getMySections(id));
				PrintWriter out = response.getWriter();
				out.write(jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
