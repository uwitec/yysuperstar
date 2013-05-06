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
				
				//��ָ����������װ��BufferedReader
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
				
				//��ȡSD��Ŀ¼
				File sdCardDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdCardDir.getCanonicalPath() + sdCard.FileName);
				
				//��ָ���ļ����� RandomAccessFile����
				RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
				
				//���ļ���¼ָ���ƶ������
				raf.seek(targetFile.length());
				
				//����ļ�����
				raf.write(content.getBytes());
				raf.close();
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
