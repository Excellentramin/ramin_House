package musicWed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myMusicServlet
 */
@WebServlet("/myMusicServlet")
public class myMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myMusicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String songName=request.getParameter("songName");
		String singer=request.getParameter("singer");
		String songUrl=request.getParameter("songUrl");
		Connection conn=Conn.getConn();
		PreparedStatement pst;
		
		String sql="select * from mymusic where userName = ? and songName = ?";
		try{
			
				 pst=conn.prepareStatement(sql);
				pst.setString(1, userName);
				pst.setString(2, songName);
				ResultSet rs=pst.executeQuery();
				//若已收藏此歌曲，则从我的音乐中移除
				if(rs.next()){
				     
					sql="delete from mymusic where songName= ? and userName= ?";
					pst=conn.prepareStatement(sql);
					pst.setString(1,songName);
					pst.setString(2, userName);
					 pst.execute();
					 out.print("取消收藏");
					}else{

						 Statement st=  conn.createStatement();
						 String sql1="insert into mymusic(songName,singer,songUrl,userName) values('"+songName+"','"+singer+"','"+songUrl+"','"+userName+"')";
						 st.executeUpdate(sql1);
						out.print("收藏成功");		
					}
				rs.close();
				pst.close();
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
