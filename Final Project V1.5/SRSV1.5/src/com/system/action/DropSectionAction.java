package com.system.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.model.Section;
import com.system.model.Student;
import com.system.service.SectionService;
import com.system.service.StudentService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class DropSectionAction
 */
@WebServlet("/DropSectionAction")
public class DropSectionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropSectionAction() {
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
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String sectionNo = request.getParameter("sectionNo");
		Student student = new Student(id);
		Section section = new Section(sectionNo);
		StudentService studentService = new StudentService();
		SectionService sectionService = new SectionService();
		try {
			studentService.initializeStudent(student);
			sectionService.initializeTheSection(section);
			if(section.drop(student)){
				studentService.dropSection(student,section);
			}
			JSONArray jsonArray = JSONArray.fromObject(sectionService.getMySections(id));
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
