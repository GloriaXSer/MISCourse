package com.system.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.model.Section;
import com.system.service.SectionService;

/**
 * Servlet implementation class CheckSectionAction
 */
@WebServlet("/CheckSectionAction")
public class CheckSectionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSectionAction() {
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
		String sectionNo = request.getParameter("sectionNo");
		HttpSession session = request.getSession();
		SectionService sectionService = new SectionService();
		try {
			Section certainSection = sectionService.getSectionBySectionNo(sectionNo);
			session.setAttribute("theSection", certainSection);
			request.getRequestDispatcher("/selectSection.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
