package br.com.rino.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rino.dao.PhotoDAO;
import br.com.rino.entity.Photo;
import br.com.rino.util.FileUtil;

public class TransferServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			PhotoDAO photoDAO = new PhotoDAO();
			Photo photo = new Photo();

			photo.setNomeEvento(req.getParameter("nomeEvento"));
			photo.setDescricaoEvento(req.getParameter("descricaoEvento"));
			photo.setNomeFoto(req.getParameter("nomeFoto"));
			photo.setPendente(req.getParameter("pendente"));
			photo.setEmail(req.getParameter("email"));
			photo.setFile(FileUtil.convertBase64ToByte(req.getParameter("image")));
			photoDAO.insert(photo);

			pw.println("SUCCESS");
		} catch (Exception e) {
			pw.println("ERROR");
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			PhotoDAO photoDAO = new PhotoDAO();
			Photo photo = new Photo();

			photo.setNomeEvento(req.getParameter("nomeEvento"));
			photo.setDescricaoEvento(req.getParameter("descricaoEvento"));
			photo.setNomeFoto(req.getParameter("nomeFoto"));
			photo.setPendente(req.getParameter("pendente"));
			photo.setEmail(req.getParameter("email"));
			photo.setFile(FileUtil.convertBase64ToByte(req.getParameter("image")));
			photoDAO.insert(photo);

			pw.println("SUCCESS");
		} catch (Exception e) {
			pw.println("ERROR");
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}

}