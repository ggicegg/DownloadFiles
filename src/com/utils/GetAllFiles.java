package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class GetAllFiles {
	/**
	 * 从properties文件中拿到一个文件的根目录
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getRoot() throws FileNotFoundException, IOException {

		String rootPath = null;
		Properties properties = new Properties();
		properties.load(new InputStreamReader(new FileInputStream("file.properties")));
		rootPath = properties.getProperty("rootPath");
		return rootPath;
	}

	/**
	 * 拿到根目录下所有文件的一个list集合
	 * 
	 * @return
	 */
	public static List<String> getFileList() {
		List<String> list = new LinkedList<>();
		String rootPath = "";
		try {
			rootPath = GetFileContent.getContent("rootPath", GetFileContent.CURRENT_TYPE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Path files = Paths.get(rootPath);
		System.out.println(rootPath);
		System.out.println(files);

		try (DirectoryStream<Path> entries = Files.newDirectoryStream(files)) {
			for (Path entry : entries) {
				list.add(entry.getFileName().toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
