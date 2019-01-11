package com.jinhaoplus.oj.util;

import com.jinhaoplus.oj.domain.Problem;

public class ProblemUtils {
	public static Problem initProblemInfo(Problem problem,Integer problemPoster){
		problem.setProblemVisable("0");
		problem.setProblemAcTimes(0);
		problem.setProblemCeTimes(0);
		problem.setProblemWaTimes(0);
		problem.setProblemSolveTimes(0);
		problem.setProblemPoster(problemPoster.toString());
		problem.setProblemCategory("PROGRAMMING");
		problem.setProblemVisable("0");
		return problem;
	}
	public static Problem postProblemInfo(Problem problem,Integer problemPoster){
		problem.setProblemVisable("0");
		problem.setProblemAcTimes(0);
		problem.setProblemCeTimes(0);
		problem.setProblemWaTimes(0);
		problem.setProblemSolveTimes(0);
		problem.setProblemPoster(problemPoster.toString());
		problem.setProblemCategory("PROGRAMMING");
		problem.setProblemVisable("1");
		return problem;
	}
}
