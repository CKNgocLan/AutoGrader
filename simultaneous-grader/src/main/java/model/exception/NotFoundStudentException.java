package model.exception;

import java.util.function.Supplier;

public class NotFoundStudentException extends Exception {
	private static final long serialVersionUID = 8238765061354147623L;

	public NotFoundStudentException(String message) {
		super(message);
	}

	public static Supplier<NotFoundStudentException> toSupplier(String message) {
		return () -> new NotFoundStudentException(message);
	}
}
