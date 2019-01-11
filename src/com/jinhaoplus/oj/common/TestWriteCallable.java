package com.jinhaoplus.oj.common;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.Callable;

public class TestWriteCallable implements Callable<Object>{
	private OutputStream outputStream;
	private String testToWrite;
	public TestWriteCallable(OutputStream outputStream,String testToWrite) {
		this.outputStream = outputStream;
		this.testToWrite = testToWrite;
	}
	@Override
	public Object call() throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		try {
			bufferedWriter.write(testToWrite);
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
