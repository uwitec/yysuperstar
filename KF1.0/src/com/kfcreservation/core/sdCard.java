package com.kfcreservation.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import android.os.Environment;

public class sdCard {

	//static String FileName = "";
	final static String FileName = "/userinfo.bin";
	
	public static String red(){
		
		try{
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdCardDir = Environment.getExternalStorageDirectory();
				FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + sdCard.FileName);
				
				//将指定输入流包装成BufferedReader
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				
				StringBuilder sb = new StringBuilder("");
				String line = null;
				
				while( (line = br.readLine()) != null ){
					sb.append(line);
				}
				return sb.toString();
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
		return null;
		
	}
	
	public static void write(String content){
		
		try{
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				
				//获取SD卡目录
				File sdCardDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdCardDir.getCanonicalPath() + sdCard.FileName);
				
				//以指定文件创建 RandomAccessFile对象
				RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
				
				//将文件记录指针移动到最后
				raf.seek(targetFile.length());
				
				//输出文件内容
				raf.write(content.getBytes());
				raf.close();
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
