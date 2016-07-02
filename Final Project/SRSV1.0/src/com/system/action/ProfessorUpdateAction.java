package com.system.action;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.model.Professor;
import com.system.service.ProfessorService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProfessorUpdateAction
 */
@WebServlet("/ProfessorUpdateAction")
public class ProfessorUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorUpdateAction() {
        super();
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
		String oldid = request.getParameter("oldid");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String department = request.getParameter("department");
		Professor professor = new Professor(name, id, title, department);
		ProfessorService professorService = new ProfessorService();
		try {
			professorService.updateProfessor(professor, oldid);
			JSONArray jsonArray = JSONArray.fromObject(professorService.getAll());
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
