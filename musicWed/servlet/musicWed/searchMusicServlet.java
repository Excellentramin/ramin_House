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
 * Servlet implementation class searchMusicServlet
 */
@WebServlet("/searchMusicServlet")
public class searchMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchMusicServlet() {
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
		String search=request.getParameter("search");
		search=search==null?"":search;
		Connection conn=Conn.getConn();
		String sql="select * from musiclist where songName like '%' ? '%' ";
		
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, search);
			ResultSet rs=pst.executeQuery();
			
			List<Song> song=new ArrayList<Song>();
			if(!rs.next()){
				out.print("查不到相关信息");
			}else{
				
				while(true){
					Song song1=new Song();
					song1.id=rs.getInt("id");
					song1.songName=rs.getString("songName");
					song1.singer=rs.getString("singer");
					song1.type=rs.getString("type");
					song1.songUrl=rs.getString("songUrl");
					song.add(song1);	
					if(!rs.next()){
						break;
					}
					}
				ObjectMapper om=new ObjectMapper();
				String json=om.writeValueAsString(song);
				out.print(json); 
				}
			}catch (Exception e) {
				// TODO: handle exception
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
