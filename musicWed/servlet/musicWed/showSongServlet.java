package musicWed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class showSongServlet
 */
@WebServlet("/showSongServlet")
public class showSongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showSongServlet() {
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
		String type=request.getParameter("type");
		PrintWriter out=response.getWriter();
		Connection conn=Conn.getConn();
		if(type.equals("È«²¿")){
			String sql="select *from musiclist";
			try {
				PreparedStatement pst=conn.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				List<Song> song=new ArrayList<Song>();
				while(rs.next()){
					Song song1=new Song();
					song1.id=rs.getInt("id");
					song1.songName=rs.getString("songName");
					song1.singer=rs.getString("singer");
					song1.type=rs.getString("type");
					song1.songUrl=rs.getString("songUrl");
					song1.songTime=rs.getString("songTime");
					song.add(song1);	
				}
				ObjectMapper om=new ObjectMapper();
					String json=om.writeValueAsString(song);
					out.print(json); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
			String sql="select *from musiclist where type=?";
			try {
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, type);
				ResultSet rs=pst.executeQuery();
				List<Song> song=new ArrayList<Song>();
				while(rs.next()){
					Song song1=new Song();
					song1.songName=rs.getString("songName");
					song1.singer=rs.getString("singer");
					song1.type=rs.getString("type");
					song1.songUrl=rs.getString("songUrl");
					song1.songTime=rs.getString("songTime");
					song.add(song1);	
				}
				ObjectMapper om=new ObjectMapper();
					String json=om.writeValueAsString(song);
					out.print(json); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
