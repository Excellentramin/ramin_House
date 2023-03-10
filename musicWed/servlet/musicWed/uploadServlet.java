package musicWed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/uploadServlet")
@MultipartConfig
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadServlet() {
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
		Date date=new Date();
		SimpleDateFormat sj=new SimpleDateFormat("yyyy/MM/dd");//设置上传歌曲时间
		String songTime=sj.format(date);
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String singer=request.getParameter("singer");
		String type=request.getParameter("type");
		 Part part=request.getPart("file");
		 long len=part.getSize();
		 String filename=null;
		 if(len>0){
			 String s=part.getHeader("Content-Disposition");
			 int pos=s.lastIndexOf(".");
			 String ext="";
			 if(pos>0){
				 ext=s.substring(pos,s.length()-1);
			 }
			 filename=System.currentTimeMillis()+ext;
			 part.write(getServletContext().getRealPath("/")+filename);
			
		 }
		 
		//String sql="insert into musiclist values(null,?,?,?,?,?)";
		 try{
			 Connection conn=Conn.getConn();
			 Statement st=  conn.createStatement();
			 String sql="insert into musiclist(songName,singer,songUrl,songTime,type) values('"+name+"','"+singer+"','"+filename+"','"+songTime+"','"+type+"')";
			 st.executeUpdate(sql);
//			 PreparedStatement pst=conn.prepareStatement(sql);
//			 pst.setString(1, name);
//			 pst.setString(2, singer);
//			 pst.setString(3, filename);
//			 pst.setString(4, time);
//			 pst.setString(5, type);
//			 pst.execute();
			 out.print("添加成功");
				st.close();
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
