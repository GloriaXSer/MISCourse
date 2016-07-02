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

/**
 * Servlet implementation class EnrollSectionAction
 */
@WebServlet("/EnrollSectionAction")
public class EnrollSectionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollSectionAction() {
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
			String enrollmentStatus = section.enroll(student).toString();
			if(enrollmentStatus.equals("success")){
				studentService.enrollSection(student, section);
			}
			PrintWriter out = response.getWriter();
			out.write(enrollmentStatus);
			request.setAttribute("result", enrollmentStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
