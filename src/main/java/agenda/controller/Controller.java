package agenda.controller;

import java.io.IOException;
import java.util.ArrayList;

import agenda.model.DAO;
import agenda.model.JavaBeans;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/controller", "/main", "/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
	
    public Controller() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			contatos(request, response);
		}else if(action.equals("/insert")) {
			novoContato(request, response);
		}else {
			response.sendRedirect("index.html");
		}
		//Teste de conexão
		//dao.testeConexao();
	}
	protected void novoContato(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		//teste de recebimento dos dados do formulario
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		//setar as variaveis JavaBeans
		
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		// invocar metodo inserirContato passando obj contato
		dao.inserirContato(contato);
		
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
		
	}

	//Listar contatos
	protected void contatos(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
		//Criando um obj que ira receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarcontatos();
		
		// teste recebimento lista
		//for(int i = 0 ; i<lista.size() ; i++) {
		//System.out.println(lista.get(i).getIdcon());
		//System.out.println(lista.get(i).getNome());
		//System.out.println(lista.get(i).getFone());
		//System.out.println(lista.get(i).getEmail());
		//}
		
		// Encaminhar a lista ao documento agenda,jsp
		
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
	}
}
