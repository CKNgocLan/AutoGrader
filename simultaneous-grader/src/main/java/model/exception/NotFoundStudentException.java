package model.exception;

import java.util.function.Supplier;

import common.message.ExceptionMessage;
import model.component.Student;

public class NotFoundStudentException extends Exception {
	private static final long serialVersionUID = 8238765061354147623L;

	public NotFoundStudentException(String message) {
		super(message);
	}

	public static Supplier<NotFoundStudentException> toSupplier(Student student) {
		return () -> new NotFoundStudentException(ExceptionMessage.STUDENT_NOT_FOUND.getContent(student.number(), student.fullName()));
	}
}
