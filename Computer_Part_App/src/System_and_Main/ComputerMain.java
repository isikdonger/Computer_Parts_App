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
		
		try {
			System.out.println(HardwareSystem.compare(HardwareSystem.findHardwarePart(37), HardwareSystem.findHardwarePart(41)));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//MainFrame mf = new MainFrame();
		//mf.setVisible(true);
	}
	
}
