package conntrol;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.NEW;

@WebServlet("/CaseYzmServlet")
public class CaseYzmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    private static int WIDTH=60;
    private static int HEIGHT=20;
    public CaseYzmServlet() {
        super();
       
    }

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();//创建一个session
		response.setContentType("image/jpeg");//指定编码方式
		ServletOutputStream sos=response.getOutputStream();//获取输出流
		response.setHeader("pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");//设置浏览器不要缓存图片
		response.setDateHeader("Expirse",0);//设置过期时间
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//创建一个不透明的内存图像
		Graphics g=image.getGraphics();//调用getGraphics()方法产生一个用于往image上绘图的Graphics对象
		char[] rands=generateCheckCode();//通过调用该方法产生一个随机数组
		drawBackground(g);//绘画上边产生的内存图像的背景
		drawRands(g,rands);//往绘画过背景的内存图像上绘制字母或数字
		g.dispose();
		//输出到客户端
		ByteArrayOutputStream bos=new ByteArrayOutputStream();//创建字节数组输出流
		ImageIO.write(image, "JPEG", bos);//将jpeg格式的图像写入字节流bos中
		byte[] buf=bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);//将buf字节数组中的数据写入ServletOutputStream流中
		bos.close();
	    sos.close();
	    session.setAttribute("check_code", new String(rands));
	}

	private char[] generateCheckCode(){
		String chars="0123456789abcdefghijknmlopqrstuvwxyz";
        char[] rands=new char[4];
        for(int i=0;i<4;i++){
        	int rand=(int)(Math.random()*36);//产生一个随机数
        	rands[i]=chars.charAt(rand);//提取chars字符串中的字母或者数字，用于验证码的生成
        }
	    return rands;
	}
	private void drawBackground(Graphics g){
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i=0;i<120;i++){
			int x=(int)(Math.random()*WIDTH);
			int y=(int)(Math.random()*HEIGHT);
			int red=(int)(Math.random()*255);
			int green=(int)(Math.random()*255);
			int blue=(int)(Math.random()*255);
			g.setColor(new Color(red,green,blue));
			g.drawOval(x, y, 1, 0);
		}
	}
	private void drawRands(Graphics g,char[] rands){
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
		g.drawString(""+rands[0], 1, 17);
		g.drawString(""+rands[1], 16, 15);
		g.drawString(""+rands[2], 31, 18);
		g.drawString(""+rands[3], 46, 16);
		//System.out.println(rands);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
