package mermaid;

import java.util.List;

public class MermaidConvertor {
	public static void main(String[] args) {
//		String mermaidCode = "classDiagram\n" + "class BankAccount {\n" + "  <<abstract>>\n" + "  +String ownerName\n"
//				+ "  -double balance = 0.0\n" + "  #static final int MAX_LIMIT = 1000\n"
//				+ "  +void deposit(double amount)\n" + "  -boolean withdraw(double amount) *\n" + "}\n"
//				+ "class Status {\n" + "  <<enumeration>>\n" + "  +int ACTIVE = 1\n" + "  +int INACTIVE = 0\n" + "}\n";
		String mermaidCode = MermaidFileReader.readFromFile("CSE203-OOP-254m.mmd");

		MermaidParser parser = new MermaidParser();
		List<ClassCheckingObject> classes = parser.parse(mermaidCode);

		for (ClassCheckingObject c : classes) {
			System.out.println(c);
		}
	}
}
