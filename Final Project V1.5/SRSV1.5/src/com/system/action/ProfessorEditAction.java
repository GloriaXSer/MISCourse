package com.system.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.service.ProfessorService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProfessorEditAction
 */
@WebServlet("/ProfessorEditAction")
public class ProfessorEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorEditAction() {
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
		try {
			ProfessorService professorService = new ProfessorService();
			JSONArray jsonArray = JSONArray.fromObject(professorService.getAll());
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
