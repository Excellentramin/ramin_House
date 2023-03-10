package musicWed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		if(username==""){
			out.print("�û�������Ϊ�գ�");
			return;
		}
		if(password==""){
			out.print("���벻��Ϊ�գ�");
			return;
		}
		if(password1==""){
			out.print("ȷ�����벻��Ϊ��!");
			return;
		}
		if(!password.equals(password1)){
			out.print("�����������벻һ��!");
			return;
		}
		Connection conn=Conn.getConn();
		String sql="select*from user where username=?";
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				out.print("�û����Ѵ���");
				return;
			}
			else{
				sql="insert into user values(null,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, password);
				if(pst.executeUpdate()>0){
					out.print("ע��ɹ�");
				}else{
					out.print("ע��ʧ��");
					return;
				}
			}
			pst.close();
			rs.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
