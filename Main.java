package com.arthur.delicious.bloomfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dzgz.util.Utils;

public class Main {
	
	private static long totalStartTime;
	private static long totalStopTime;
	
	public static void main(String[] argv) throws IOException{
		System.out.println("*****Analyse Files Begin*****\n");
		totalStartTime = System.currentTimeMillis();
		
		String filePath = "D:\\data\\delicious\\resultAll\\0.txt";
		String fileDirPath = "D:\\data\\delicious\\resultAll\\";
		
		BloomFilter bf = new BloomFilter(1000000);
		FileInputStream fis = new FileInputStream(new File(filePath));
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line = br.readLine())!=null){
			String[] array = line.split("\t");
			if(array.length < 3){
				continue;
			}
			String url = array[2];
			bf.addString(url);
		}
		System.out.println(bf.getCounterResult());
		

		totalStopTime = System.currentTimeMillis();
		System.out.println("\n\nTotal Cost Time:"+Utils.getCostTime(totalStartTime, totalStopTime));
		System.out.println("*****Analyse Files Begin*****");
	}

}
