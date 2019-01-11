package com.jinhaoplus.oj.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class ResultReadCallable implements Callable<String>{
	private InputStream inputStream;
	public ResultReadCallable(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@Override
	public String call() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (!"".equals(line)) {
					stringBuffer.append(line+"\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}
}
