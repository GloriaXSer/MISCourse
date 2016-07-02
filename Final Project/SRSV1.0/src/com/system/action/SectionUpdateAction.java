package com.system.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.model.Section;
import com.system.service.SectionService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SectionUpdateAction
 */
@WebServlet("/SectionUpdateAction")
public class SectionUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionUpdateAction() {
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
		request.setCharacterEncoding("UTF-8");
		String sectionNo = request.getParameter("sectionNo");
		String dayOfWeek = request.getParameter("dayOfWeek");
		String timeOfDay = request.getParameter("timeOfDay");
		String room = request.getParameter("room");
		int seatingCapacity = Integer.valueOf(request.getParameter("seatingCapacity"));
		String courseName = request.getParameter("courseName");
		String oldCourseNo = request.getParameter("oldNo");
		String professorName = request.getParameter("professorName");
		Section section = new Section(sectionNo, dayOfWeek, timeOfDay, room, seatingCapacity, courseName, professorName);
		SectionService sectionService = new SectionService();
		try {
			sectionService.updateSection(section,oldCourseNo);
			JSONArray jsonArray = JSONArray.fromObject(sectionService.getAll());
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
