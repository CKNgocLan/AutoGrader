package model.service;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ProblemTask implements Runnable {
	private String name;
	private SingleThreadStudentExecutor studentService;

	public ProblemTask(SingleThreadStudentExecutor executor) {
		this.studentService = executor;
	}

	public ProblemTask(SingleThreadStudentExecutor executor, String name) {
		this.studentService = executor;
	}

	public void newTask() {
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running Problem Task: %s".formatted(this.name));
	}
}
