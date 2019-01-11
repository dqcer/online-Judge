package com.jinhaoplus.oj.util;

import java.io.File;
import java.io.FileWriter;

import com.jinhaoplus.oj.domain.ProblemSolution;

public class Source2FileUtils {
	private Source2FileUtils(){
		
	}
	public static String renameForTempSource(ProblemSolution solution){
		StringBuffer rename = new StringBuffer();
		long sysMill = System.currentTimeMillis();
		rename.append("prblm");
		rename.append(solution.getProblemId());
		rename.append(solution.getSolutionCoderId());
		rename.append(new String(new Long(sysMill).toString()));
		return rename.toString();
	}
	public static void persistentFile(ProblemSolution solution,String path) {
		try {
			String source = solution.getCodeSubmit();
			File file = new File(path);
			file.createNewFile();
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(source);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
