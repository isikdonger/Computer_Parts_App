package System_and_Main;

import java.io.FileNotFoundException;

public class ComputerMain {

	public static void main(String[] args) {
		try {
			HardwareSystem.readComponentData();
			HardwareSystem.readComputerData();
			System.out.println(HardwareSystem.getComputers());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
