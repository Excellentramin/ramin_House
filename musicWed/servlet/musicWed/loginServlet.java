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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username==""){
			out.print("用户名不能为空！");
			return;
		}
		if(password==""){
			out.print("密码不能为空！");
			return;
		}
		Connection conn=Conn.getConn();
		String sql="select*from user where username=?";
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				if(!rs.getString("password").equals(password)){
					out.print("密码错误");
					return;
				}
				}else{
				out.print("查不到此账号");
				return;
			}
			rs.close();
			pst.close();
			conn.close();
			out.print(username);
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
