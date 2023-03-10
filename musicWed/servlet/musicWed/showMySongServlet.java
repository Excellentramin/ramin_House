package musicWed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class showMySongServlet
 */
@WebServlet("/showMySongServlet")
public class showMySongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showMySongServlet() {
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
		Connection conn=Conn.getConn();
		if(songName.equals("")){           //传过来的音乐名为空时，执行展示mymusic里的所有数据
			String sql="select *from mymusic where username=?";
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userName);
			ResultSet rs=pst.executeQuery();
			List<Song> song=new ArrayList<Song>();
			while(rs.next()){
				Song song1=new Song();
				song1.songName=rs.getString("songName");
				song1.singer=rs.getString("singer");
				song1.songUrl=rs.getString("songUrl");
				song.add(song1);
			}
			ObjectMapper om=new ObjectMapper();
			String json=om.writeValueAsString(song);
			out.print(json); 
		}catch (Exception e) {
			// TODO: handle exception
		}
		}else{                        //传过来的音乐名不为空时，执行将音乐从我的音乐移除操作
			String sql="delete from mymusic where userName=?and songName=?";
			try{
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, userName);
				pst.setString(2, songName);
				pst.execute();
				sql="select *from mymusic where userName=?";
				try{
				  pst=conn.prepareStatement(sql);
					pst.setString(1, userName);
					ResultSet rs=pst.executeQuery();
					List<Song> song=new ArrayList<Song>();
					while(rs.next()){
						Song song1=new Song();
						song1.songName=rs.getString("songName");
						song1.singer=rs.getString("singer");
						song1.songUrl=rs.getString("songUrl");
						song.add(song1);
					}
					ObjectMapper om=new ObjectMapper();
					String json=om.writeValueAsString(song);
					out.print(json); 
				}catch (Exception e) {
					// TODO: handle exception
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
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
