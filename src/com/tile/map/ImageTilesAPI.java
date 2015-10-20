package com.tile.map;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import processing.core.PImage;




/**
 * 图片接口
 * 
 * @author zinping
 * 
 */
public class ImageTilesAPI extends HttpServlet {
	private static final long serialVersionUID = 9L;
	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:D:/MBTileApp/WebRoot/MBData/google.mbtiles.mbtiles";
	PImage tile;
	@Override
	public void init() throws ServletException
	{

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");				
		int x=Integer.parseInt(request.getParameter("x"));
		int y=Integer.parseInt(request.getParameter("y"));
		int z=Integer.parseInt(request.getParameter("z"));
		y = (int)Math.pow(2, z) - 1 - y;
		tile = MBTilesLoaderUtils.getMBTile(x, y, z, JDBC_CONN_STRING_MAC);	
		if(tile!=null){
			response.setContentType("image/png");
			ImageIO.write((RenderedImage)tile.getImage(), "png", response.getOutputStream());			
		}else{
			response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
			response.getWriter().println("no data");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}