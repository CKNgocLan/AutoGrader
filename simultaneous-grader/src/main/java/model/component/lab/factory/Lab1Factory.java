package model.component.lab.factory;

import model.component.Lab;
import model.component.lab.concrete.Lab1;

public class Lab1Factory implements LabFactory {

	@Override
	public Lab createLab() {
		return new Lab1();
	}

}
