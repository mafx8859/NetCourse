package conntrol;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
//import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CourseDao;
import dao.UsersDao;
import moddel.Comment;
import moddel.Course;
import moddel.Question;
import moddel.Types;
import moddel.Users;
import moddel.shopCarBean;
import service.ChangeToHtml;
import service.NetworkCourse; 
/**
 * Servlet implementation class AllServlet
 */
@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int id1=0;
    static int typeflag=2;
    static String uname=null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String flag=request.getParameter("flag");
		switch(flag){
		           case "login" : login(request,response);break;
		           case "register" :register(request,response);break;
		           case "setQuestion":setQuestion(request,response);break;
		           case "makeQuestion":makeQuestion(request,response);break;
		           case "checkSolution":checkSolution(request,response);break;
		           case "out": safelyOut(request,response);break;
		           case "addcourse":addcourse(request, response,flag);break;
		   		   case "querycourse":querycourse(request, response);break;
		   		   case "saveCourseAndSetQue" :saveAndSetQue(request,response,flag);break;
		   		   case "selectMyCourse":selectMyCourse(request,response);break;
		   		   case "recharge":recharge(request,response);break;
		   		   case "addToShopCar":addToShopCar(request,response);break;
		   		   case "selectShopCar":selectShopCar(request,response);break;
		   		   case "delShopCar":delShopCar(request,response);break;
		   		   case "shopCarJs":shopCarJs(request,response);break;
			   		case "page":
						page(request,response);
						break;
					case "detail":
						detail(request,response);
						break;
					case "buy":
						buy(request,response);
						break;
					case "detail1":
						detail1(request,response);
						break;
					case "typeselect":
						typeselect(request,response);
						break;
					case "selectlike":
						selectlike(request,response);
						break;
					case "addcomment":
						addcomment(request,response);
						break;
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void shopCarJs(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userId=Integer.parseInt(request.getSession().getAttribute("id").toString());//用户id号
		double allPrice=Double.parseDouble(request .getParameter("allPrice"));//此时需要结算的总价钱
		double restMoney=Double.parseDouble(request.getSession().getAttribute("money").toString())-allPrice;
		System.out.println("==="+userId );
		String[] shopCarCourseId=request.getParameterValues("shopCarJs");//选中的所有课程的id号
		int[] arrayId = new int[1000];
		NetworkCourse nt=new NetworkCourse();
		for(int i=0;i<shopCarCourseId.length;i++){
			arrayId[i]=Integer.parseInt(shopCarCourseId[i]);
			System.out.println(arrayId[i]);
		}
		try {
			nt.buyCourseFromShopCar(arrayId, userId,restMoney);
			request.getSession().setAttribute("money", restMoney);
			response .getWriter().print("<script language='javascript'>alert('购买成功');history.back(-1);</script>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				response .getWriter().print("<script language='javascript'>alert('购买失败');history.back(-1);</script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void delShopCar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int shopId=Integer.parseInt(request.getParameter("shopCarId"));
		int userId=Integer.parseInt(request .getSession().getAttribute("id").toString());
		CourseDao cDao =new CourseDao ();
		List<Course> list=new ArrayList<Course>();
		int isSucceed=-1;
		try {
			isSucceed =cDao.delShopCar(shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isSucceed ==1){
		  try{
			 list=cDao.selectShopCar(userId);
			 request.getSession().setAttribute("shopCar", list);
			 request .getRequestDispatcher("shopCar.jsp").forward(request , response );
		  }catch(Exception e){
			 e.printStackTrace(); 
		  }
		}else{
			try {
				response.getWriter().print("<script language='javascript'>alert('删除购物车失败');history.back(-1);");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void selectShopCar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CourseDao cDao =new CourseDao ();
		List <Course > list;
		int userId =Integer.parseInt(request .getParameter("userId"));
		try {
			list=cDao.selectShopCar(userId);
			request.getSession().setAttribute("shopCar", list);
			request .getRequestDispatcher("shopCar.jsp").forward(request , response );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addToShopCar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NetworkCourse nt=new NetworkCourse();
		int userId=Integer.parseInt(request.getParameter("userId"));
		int shopId=Integer.parseInt(request.getParameter("shopId"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date=df.format(new Date());
		Object[] param={userId,shopId ,date};
		int isSucceed=nt.addToShopCar(param);
	    try {
	    	if(isSucceed ==1){
	    		
				response.getWriter().print("<script language='javascript'>alert('成功加入购物车');</script>");
				response.setHeader("refresh", "1,URL=index.jsp");
	    	}else if(isSucceed ==0){
	    		response.getWriter().print("<script language='javascript'>alert('加入购物车失败');</script>");
				response.setHeader("refresh", "1,URL=index.jsp");
	    	}else{
	    		response.getWriter().print("<script language='javascript'>alert('购物车中已经存在该课程');</script>");
				response.setHeader("refresh", "1,URL=index.jsp");
	    	}
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	
	public void recharge(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		double restMoney=Double.parseDouble(request.getParameter("restMoney"));
		double rechargeMoney=Double.parseDouble(request.getParameter("czMoney"));
		int id=Integer.parseInt(request.getParameter("czUserId"));
		double money=restMoney+rechargeMoney;
		NetworkCourse nCourse =new NetworkCourse();
		Object[] param={money,id};
		String message;
		
		try {
			if(nCourse.recharge(param)==true){
				response.getWriter().print("<script language='javascript'>alert('充值成功')</script>");
				response.setHeader("refresh","1,URL=personCenter.jsp");
				request.getSession().setAttribute("money", money);
			}else{
				response.getWriter().print("<script language='javascript'>alert('充值失败');history.back(-1);</script>");
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	
	public  void selectMyCourse(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		CourseDao cDao=new CourseDao();
		try {
			List<Course> list=cDao .selectMyCourse(id);
			request.getSession().setAttribute("myCourse", list);
			request.getRequestDispatcher("personCenter.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveAndSetQue(HttpServletRequest request, HttpServletResponse response,String flag) {
		addcourse(request, response,flag);
		int id;
		try {
			id = new CourseDao().getMaxId();
			request.getSession().setAttribute("question", id);
			request.getRequestDispatcher("SetQuestion.jsp").forward(request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void login(HttpServletRequest request, HttpServletResponse response){
		String username=request.getParameter("name");
		String password=request.getParameter("password");
		String yzm=request.getParameter("check");
		String trueYzm=request.getSession().getAttribute("check_code").toString();
		if(!yzm.equals(trueYzm)){
			try {
				response.getWriter().print("<script language='javascript'>alert('验证码错误，请重新登陆');history.back(-1);</script>");
			    return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		NetworkCourse g=new NetworkCourse();
		Users user=g.login(username, password);
		if(user!=null){
			request.getSession().setAttribute("name",user.getName());
			request.getSession().setAttribute("portrait", user.getPortrait());
			request.getSession().setAttribute("id", user.getId());
			id1=user.getId();
			request.getSession().setAttribute("money", user.getMoney());
			try {
				if (user.getType() == 1) {
					NetworkCourse n = new NetworkCourse();
					String p2 = request.getParameter("p");
					int p = 1;
					int r = 12;
					int count = n.getCount2(r);
					if (p2 != null && !p2.equals("")) {
						p = Integer.parseInt(p2);
					}
					if (p <= 0) {
						p = 1;
					}
					if (p >= count) {
						p = count;
					}
					List<Course> listcourse = n.selectC(p, r);
					request.getSession().setAttribute("p", p);
					request.getSession().setAttribute("count", count);
					request.getSession().setAttribute("listcourse", listcourse);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else {
					String issure = user.getName();
					request.getSession().setAttribute("issure", issure);
					NetworkCourse n1 = new NetworkCourse();
					NetworkCourse n2 = new NetworkCourse();
					List<Types> t = n1.QueryType();
					request.getSession().setAttribute("list", t);

					String p2 = request.getParameter("p");
					int p = 1;
					int r = 8;
					System.out.println(issure);
					int count = n2.getCount(r,issure);
					if (p2 != null && !p2.equals("")) {
						p = Integer.parseInt(p2);
					}
					if (p <= 0) {
						p = 1;
					}
					if (p >= count) {
						p = count;
					}

					List<Course> list1 = n2.select(p, r, issure);
					request.getSession().setAttribute("p", p);
					request.getSession().setAttribute("count", count);
					request.getSession().setAttribute("list1", list1);
					String message;
					if(list1==null){
						message="您还没有上传此类课程，请点击上传新课程上传课程";
					}
					else{
						message="您上传的此类课程有";
					}
					request.setAttribute("message",message);
					request.getRequestDispatcher("/SIndex.jsp").forward(request, response);
				}
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
				try {
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<script language='javascript'>alert('用户名或密码错误，请重新登陆');</script>");
					response.setHeader("refresh","1,URL=index.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
	}
	public void register(HttpServletRequest request, HttpServletResponse response){
		 Users u=new Users();
         NetworkCourse g=new NetworkCourse();
         String savePath = this.getServletContext().getRealPath("/image");
         //System.out.println("vefef"+savePath);
         File file = new File(savePath);
         //判断上传文件的保存目录是否存在
         if (!file.exists() && !file.isDirectory()) {
             System.out.println(savePath+"目录不存在，需要创建");
             //创建目录
             file.mkdir();
         }
         //消息提示
        
         try{
             //使用Apache文件上传组件处理文件上传步骤：
             //1、创建一个DiskFileItemFactory工厂
             DiskFileItemFactory factory = new DiskFileItemFactory();
             //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
              //解决上传文件名的中文乱码
             upload.setHeaderEncoding("UTF-8"); 
             //3、判断提交上来的数据是否是上传表单的数据
             if(!ServletFileUpload.isMultipartContent(request)){
                 //按照传统方式获取数据
                 return;
             }
             //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
             List<FileItem> list = upload.parseRequest(request);
             for(FileItem item : list){
                 //如果fileitem中封装的是普通输入项的数据
                 if(item.isFormField()){
                     String name = item.getFieldName();
                     //解决普通输入项的数据的中文乱码问题
                     String value = item.getString("UTF-8");
                     //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                     if(name.equals("name")){
                   	  u.setName(value);
                   	  
                     }
                     if(name.equals("sex")){
                   	  u.setSex(value);
                   	
                     }
                     if(name.equals("age")){
                   	  u.setAge(Integer.parseInt(value));
                   	
                     }
                     if(name.equals("password")){
                   	  u.setPassword(value);
                   	
                     }
                     if(name.equals("type")){
                   	  u.setType(Integer.parseInt(value));
                   	  System.out.println(value);
                     }
                    
                     
                 }else{//如果fileitem中封装的是上传文件
                     //得到上传的文件名称，
                     String filename = item.getName();
                     
                     if(filename==null || filename.trim().equals("")){
                         continue;
                     }
                     //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                     //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                     filename = filename.substring(filename.lastIndexOf("\\")+1);
                     //获取item中的上传文件的输入流
                     InputStream in = item.getInputStream();
                     //创建一个文件输出流
                     FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                     //创建一个缓冲区
                     byte buffer[] = new byte[1024];
                     //判断输入流中的数据是否已经读完的标识
                     int len = 0;
                     //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                     while((len=in.read(buffer))>0){
                         //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                     }
                     //关闭输入流
                     in.close();
                     //关闭输出流
                     out.close();
                     //删除处理文件上传时生成的临时文件
                     item.delete();
                     
                    System.out.println(savePath + "\\" + filename);
                     u.setPortrait("image"+ "\\" + filename);
                     g.addUsers(u);
                     Users user=g.login(u.getName(),u.getPassword());
             		 if(user!=null){
             			request.setAttribute("name",user.getName());
             			try {
             				request.getRequestDispatcher("index.jsp").forward(request, response);
             			} catch (ServletException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			} catch (IOException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}
             		}else{
             			
             				try {
             					response.setHeader("Content-type", "text/html;charset=UTF-8");  
            					response.setCharacterEncoding("utf-8");
             					response.getWriter().print("<script language='javascript'>alert('登录失败');</script>");
             					response.setHeader("refresh","1,URL=index.jsp");
             				} catch (Exception e) {
             					// TODO Auto-generated catch block
             					e.printStackTrace();
             				}
             			
             		}

                     
                 }
             }
         }catch (Exception e) {
        	 try {
        		 response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.setCharacterEncoding("utf-8");
					response.getWriter().print("<script language='javascript'>alert('注册失败');</script>");
					response.setHeader("refresh","1,URL=index.jsp");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
                  
     }
  }
	public void setQuestion(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		int isSucceed=0;
		UsersDao ud=new UsersDao();
		Question question=new Question();
		String quesDec=request.getParameter("quesDec");
		//System.out.println(quesDec);
		String selectA=request.getParameter("selectA");
		String selectB=request.getParameter("selectB");
		String selectC=request.getParameter("selectC");
		String selectD=request.getParameter("selectD");
		String solution=request.getParameter("solution");
		int id=Integer.parseInt(request.getParameter("questionId"));
		question.setClasses(id);
		System.out.println(id);
		if(selectA!=null){
			question.setSelectA(selectA);
		}
		if(selectB!=null){
			question.setSelectB(selectB);
		}
		if(selectC!=null){
			question.setSelectC(selectC);
		}
		if(selectD!=null){
			question.setSelectD(selectD);
		}
		if(solution!=null){
			question.setSolution(solution);
		}
		if(quesDec!=null){
			quesDec=new ChangeToHtml().change(quesDec);
			question.setQuesDec(quesDec);
			try {
				isSucceed=ud.setQuestion(question);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(isSucceed==1){
			try {
				response.getWriter().print("<script language='javascript'>alert('出题成功')</script>");
				response.setHeader("refresh","0.5,URL=SetQuestion.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("<script language='javascript'>alert('出题失败，请重新出题')</script>");
				response.setHeader("refresh","0.5,URL=SetQuestion.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void makeQuestion(HttpServletRequest request, HttpServletResponse response){
		int classes=Integer.parseInt(request.getParameter("question"));
		UsersDao ud=new UsersDao();
		ArrayList<Question> list=new ArrayList<Question>();
		try {
			list=ud.selectQuestion(classes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("question", list);
		request.getSession().setAttribute("question", list);
		try {
			request.getRequestDispatcher("makeQuestion.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void checkSolution(HttpServletRequest request, HttpServletResponse response){
		String[] solution=request.getParameterValues("mySolution");//获取用户的答案
		int classes=Integer.parseInt(request.getParameter("classes"));//获取题目对应的标号
		String solutionString=null;
		String[] trueSolutionString=new String[10];
		int numberOfTrue=0;
		String message=null;
		UsersDao ud=new UsersDao();
		ArrayList<Question> qu=new ArrayList<Question>();
		try {
			qu=ud.selectQuestion(classes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;
		//System.out.println(qu);
		//获取正确答案并且存进一个数组中
		for(Question ques:qu){
			trueSolutionString[i++]=ques.getSolution();		
		}
		//将用户的答案和正确答案进行对比
		for(int j=0;j<solution.length;j++){
			if(trueSolutionString[j].equals(solution[j])){
				numberOfTrue++;
				response.addCookie(new Cookie("infoDetail"+j,"正确"));
			}else{
				String messageString="错误|"+"正确答案是:"+trueSolutionString[j]+"|你的答案是:"+solution[j];	
				response.addCookie(new Cookie("infoDetail"+j,messageString));
			}
		}
		if(numberOfTrue==3){
			message="恭喜获得满分！！！，继续努力。";
		}else if(numberOfTrue<3&&numberOfTrue>0){
			message="很遗憾，正确数量为"+numberOfTrue+",再接再厉。";
		}else{
			message="what? 全部错误，要加油啊。";
		}
		try {
			request.getRequestDispatcher("makeQuestion.jsp?message="+message+"").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void safelyOut(HttpServletRequest request, HttpServletResponse response){
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addcourse(HttpServletRequest request, HttpServletResponse response, String flag) {
		NetworkCourse n = new NetworkCourse();
		int id=-1;
		Course c = new Course();
		String savePath1 = this.getServletContext().getRealPath("/image");
		String savePath2 = this.getServletContext().getRealPath("/video");
		File file1 = new File(savePath1);
		File file2 = new File(savePath2);
		String issure=null;
		// 判断上传文件的保存目录是否存在
		if (!file1.exists() && !file1.isDirectory()) {
			System.out.println(savePath1 + "目录不存在，需要创建");
			// 创建目录
			file1.mkdir();
		}
		if (!file2.exists() && !file2.isDirectory()) {
			System.out.println(savePath2 + "目录不存在，需要创建");
			// 创建目录
			file2.mkdir();
		}
		// 消息提示

		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					if (name.equals("coursename")) {
						c.setCourseName(value);
						// name1=value;
						// request.getSession().setAttribute("name", value);

					}
					if (name.equals("coursetype")) {
						c.setType(value);

					}
					if (name.equals("alt")) {
						c.setAlt(value);

					}
					if (name.equals("price")) {
						c.setPrice(Integer.parseInt(value));
						// password1=value;
						// request.getSession().setAttribute("password", value);
					}
					if (name.equals("issure")) {
						c.setIssure(value);
                        issure=c.getIssure();
					}

				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();

					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					if (filename.endsWith(".jpg")||filename.endsWith(".png")) {

						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out = new FileOutputStream(savePath1 + "\\" + filename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath +
							// "\\" + filename)当中
							out.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						c.setImage("image\\" + filename);
						System.out.println(savePath1 + "\\" + filename);
					} else {
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out = new FileOutputStream(savePath2 + "\\" + filename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath +
							// "\\" + filename)当中
							out.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						c.setVideo("video\\" + filename);
						System.out.println(savePath2 + "\\" + filename);
					}

				}

			}
			n.addCourse(c);
			if(flag.equals("addcourse"))
			    request.getRequestDispatcher("AllServlet?flag=querycourse&h=1&issure="+issure).forward(request, response);
		} catch (Exception e) {
			System.out.println("上传课程失败");
			e.printStackTrace();

		}
	}

	public void querycourse(HttpServletRequest request, HttpServletResponse response) {
		NetworkCourse n = new NetworkCourse();
		String message;
		int h=1;
		String issure = request.getParameter("issure");
		System.out.println(issure);
		String p2 = request.getParameter("p");
		List<Course> list1 = null;
		int p = 1;
		int r = 8;
		int count;
		if(request.getParameter("h")!=null&&!request.getParameter("h").equals("")){
		 h=Integer.parseInt(request.getParameter("h"));
		}
		 if(h==1){
			 count=n.getCount(r, issure);
		 }
		 else{
		 count = n.getCount1(r, h, issure);
		 }
		if (p2 != null && !p2.equals("")) {
			p = Integer.parseInt(p2);
		}
		if (p <= 0) {
			p = 1;
		}
		if (p >= count) {
			p = count;
		}
		if (h == 1) {
			list1 = n.select(p, r, issure);
		} else {
			System.out.println(h);
			list1 = n.select1(p, r, h, issure);
		}
		request.getSession().setAttribute("p", p);
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("list1", list1);
		request.getSession().setAttribute("h", h);
		if(list1==null){
			message="您还没有上传此类课程，请点击上传新课程上传课程";
		}
		else{
			message="您已上传的课程有：";
		}
		request.setAttribute("message", message);
		try {
			
			request.getRequestDispatcher("/SIndex.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void page(HttpServletRequest request, HttpServletResponse response) {
		NetworkCourse n = new NetworkCourse();
		String p2 = request.getParameter("p");
		String v=request.getParameter("v");
		typeflag=0;
		int p = 1;
		int r = 12;
		int count = n.getCount2(r);
		if (p2 != null && !p2.equals("")) {
			p = Integer.parseInt(p2);
		}
		if (p <= 0) {
			p = 1;
		}
		if (p >= count) {
			p = count;
		}
		List<Course> listcourse = n.selectC(p, r);
		request.getSession().setAttribute("p", p);
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("listcourse", listcourse);
		request.getSession().setAttribute("typeflag", typeflag);
		try {
			String v2;
			if(v!=null&&!v.equals("")){
				v2=v;
			}
			else{
				v2="";
			}
			request.getRequestDispatcher("index.jsp"+v2).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void detail(HttpServletRequest request, HttpServletResponse response) {
		if(id1==0){
			try {
				response.getWriter().print("<script language='javascript'>alert('请先登录再查看课程信息');</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setHeader("refresh", "1,URL=index.jsp");
		}
		else{
		//String videoString=request.getParameter("courseAddress");
		int id=Integer.parseInt(request.getParameter("idcourse"));
		id1=Integer .parseInt(request.getSession().getAttribute("id").toString());
		NetworkCourse n = new NetworkCourse();
		Course c=new Course();
		c=n.queryBuy(id, id1);
		if(c==null){
			try {
				NetworkCourse n1 = new NetworkCourse();
				Course c1=new Course();
				c1=n1.selectDetail(id);
				List<Comment> comm=n1.selectComment(id);
				request.setAttribute("listcomment", comm);
				request.setAttribute("course1", c1);
				request.getRequestDispatcher("detail.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				NetworkCourse n1 = new NetworkCourse();
				Course c1=new Course();
				c1=n1.selectDetail(id);
				request.getSession().setAttribute("studyID", id);
				List<Comment> comm=n1.selectComment(id);
				request.setAttribute("listcomment", comm);
				request.setAttribute("course1", c1);
				request.getRequestDispatcher("study.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}
	public static void buy(HttpServletRequest request, HttpServletResponse response) {
		double m=Double.valueOf(request.getParameter("money"));
		String issure1=request.getParameter("issure1");
		int id=Integer.parseInt(request.getParameter("idcourse"));
		NetworkCourse n = new NetworkCourse();
		Users u=n.query(id1);
		if(u.getMoney()>=m){
			double m2=u.getMoney()-m;
			u.setMoney(m2);
			request.getSession().setAttribute("money", m2);
			n.update(u);
		   try {
			Users u1=n.query1(issure1);
			System.out.println("==="+u1.getMoney());
			u1.setMoney(u1.getMoney()+m);
			n.update(u1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   n.buy(id, id1);
		   try {
				response.getWriter().print("<script language='javascript'>alert('购买成功，开始学习吧');</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setHeader("refresh", "1,URL=AllServlet?flag=detail&idcourse="+id);
		}
		else{
			 try {
					response.getWriter().print("<script language='javascript'>alert('用户余额不足，请先充值');</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setHeader("refresh", "1,URL=index.jsp");
		}
	}
	public static void detail1(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id2"));
		NetworkCourse n = new NetworkCourse();
		Course c1=new Course();
		c1=n.selectDetail(id);
		List<Comment> comm=n.selectComment(id);
		request.setAttribute("listcomment", comm);
		request.setAttribute("course1", c1);
		try {
			request.getRequestDispatcher("study.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void typeselect(HttpServletRequest request, HttpServletResponse response){
		String coursetype=request.getParameter("type");
		NetworkCourse n = new NetworkCourse();
		String p2 = request.getParameter("p");
		
		String v=request.getParameter("v");
		int p = 1;
		int r = 12;
		
		int count = n.getCount3(r, coursetype);
	    
		if (p2 != null && !p2.equals("")) {
			p = Integer.parseInt(p2);
		}
		if (p <= 0) {
			p = 1;
		}
		if (p >= count) {
			p = count;
		}
	    
		List<Course> listcourse = n.select2(p, r, coursetype);
		request.getSession().setAttribute("p", p);
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("listcourse", listcourse);
		request.getSession().setAttribute("type", coursetype);
		typeflag=1;
		request.getSession().setAttribute("typeflag", typeflag);
		try {
			String v2;
			if(v!=null&&!v.equals("")){
				v2=v;
			}
			else{
				v2="";
			}
			request.getRequestDispatcher("index.jsp"+v2).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void selectlike(HttpServletRequest request, HttpServletResponse response){
		typeflag=2;
		request.getSession().setAttribute("typeflag", typeflag);
		String name=request.getParameter("name");
		NetworkCourse n = new NetworkCourse();
		List<Course> listcourse =n.selectlike(name);
		request.getSession().setAttribute("listcourse", listcourse);
		
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addcomment(HttpServletRequest request, HttpServletResponse response){
	  int idc=Integer.parseInt(request.getParameter("idc"));
	  String content=request.getParameter("comment");
	  NetworkCourse n = new NetworkCourse();
	  Comment c=new Comment();
	  c.setClass1(idc);
	  c.setContent(content);
	  c.setIssureName(uname);
	  n.addcomment(c);
	  try {
		response.getWriter().print("<script language='javascript'>alert('发布评论成功');</script>");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  response.setHeader("refresh", "1,URL=AllServlet?flag=detail1&id2="+idc);
	}
}


	


