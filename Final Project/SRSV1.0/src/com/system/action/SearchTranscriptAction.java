package com.system.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.model.TranscriptEntry;
import com.system.service.TranscriptService;

/**
 * Servlet implementation class SearchTranscriptAction
 */
@WebServlet("/SearchTranscriptAction")
public class SearchTranscriptAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTranscriptAction() {
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
		String id = request.getParameter("studentId");
		String semester = request.getParameter("semester");
		String course = request.getParameter("courseNo");
		
		TranscriptService transcriptService = new TranscriptService();
		try {
			List<TranscriptEntry> sTranscripts = transcriptService.getStudTranscripts(id,semester,course);
			request.setAttribute("myTranscript", sTranscripts);
			request.getRequestDispatcher("/searchTranscript.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
