package System_and_Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import Computer.*;
import GUI.MainFrame;

public class ComputerMain {

	public static void main(String[] args) throws IOException {
		try {
			HardwareSystem.readComponentData();
			HardwareSystem.readComputerData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

}
