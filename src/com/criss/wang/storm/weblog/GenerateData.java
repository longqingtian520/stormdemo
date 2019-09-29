package com.criss.wang.storm.weblog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GenerateData {
	
	public static void main(String[] args) {
		// 创建文件路径
		File file = new File("E:/weblog.log");
		// 准备数据集
		String[] hosts = {"www.atguigu.com"};
		String[] session_id = {"ABYH6Y4V4SCVXTG6DPB4VH9U123", "XXYH6YCGFJYERTT834R52FDXV9U34",
				"BBYH61456FGHHJ7JL89RG5VV9UYU7", "CYYH6Y2345GHI899OFG4V9U567", "VVVYH6Y4V4SFXZ56JIPDPB4V678"};
		String[] time = {"2017-08-07 08:40:50", "2017-08-07 08:40:51", "2017-08-07 08:40:52", "2017-08-07 08:40:53",
				"2017-08-07 09:40:49", "2017-08-07 10:40:49", "2017-08-07 11:40:49", "2017-08-07 12:40:49"};
		// 拼接数据
		Random random = new Random();
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i< 30; i++) {
			sBuilder.append(hosts[0]+ "\t" + session_id[random.nextInt(5)] + "\t" + time[random.nextInt(8)] + "\n");
		}
		// 写数据到文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(sBuilder.toString().getBytes());
			System.out.println("generate data successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
