package com.system.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.CourseDao;
import com.system.dao.SectionDao;
import com.system.dao.impl.CourseDaoImpl;
import com.system.dao.impl.SectionDaoImpl;
import com.system.model.Course;
import com.system.model.Section;

/**
 * Servlet implementation class CourseAction
 */
@WebServlet("/CourseAction")
public class CourseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CourseAction() {
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
		String courseNo = request.getParameter("courseNo");
		CourseDao courseDao = new CourseDaoImpl();
		SectionDao sectionDao = new SectionDaoImpl();
		try {
			Course course = courseDao.getCourseByCourseNo(courseNo);
			Section section = courseDao.scheduleSection(course, "Monday", "time", "room", 30);
			sectionDao.addSection(section);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
