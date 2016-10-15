package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.GetAllFiles;
import com.utils.GetFileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Random;
import java.util.*;

/**
 * Created by ice on 2016/9/5.
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String filePath = "";
    	String rootPath = GetFileContent.getContent("rootPath", GetFileContent.CURRENT_TYPE);
    	filePath = request.getParameter("fileName");
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("utf-8");
        response.setCharacterEncoding("utf-8");
        
        
        	String path = rootPath+"\\"+filePath;
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            String name = path.substring(path.lastIndexOf("\\")+1);
            response.setContentType("application/x-msdownload");
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(name.trim(),"UTF-8"));
            response.addHeader("Content-Length",file.length()+"");
            byte[] b = new byte[65536];
            int len = 0;
            try{
                while ((len = fileInputStream.read(b)) > 0){
                    outputStream.write(b,0,len);
                }
            }catch (Exception e){

            }finally {
                fileInputStream.close();
            }

        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        System.out.println(username);
    }
}
