package test.System_and_Interface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import HardwareComponent.*;
import Interface.HardwarePart;
import System_and_Main.HardwareSystem;
import Computer.*;

class HardwareSystemTest {

    // Test the compare method for two Computer objects
    @Test
    void compare() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        // Create mock components
        //CPU cpu1 = new CPU(3000, "01/08/2024", "Intel", 3.5, 4.4, 12000, "Alder Lake", 6, 12, 1, 64, "DDR4");
        //CPU cpu2 = new CPU(450, "05/09/2024", "AMD", 4.2, 5.0, 17000, "Zen 5", 16, 32, 2, 128, "DDR5");

        /*Computer comp1 = new Notebook("Asus", 600, cpu1); // 1 + 0 + 0
        Computer comp2 = new Notebook("MSI", 500, cpu2); // 0 + 1 + 1
        // Compare the two CPU's, testing for component
        HardwarePart result = HardwareSystem.compare(cpu1, cpu2);
        HardwarePart result2 = HardwareSystem.compare(comp1, comp2);
        // We assume that CPU should be the deciding factor, where AMD is better than Intel.
        assertEquals(cpu2, result, "CPU2 should win");
        assertEquals(comp2, result2, "Computer2 should win");
        System.out.println(((CPU)result).getBrand());
        System.out.println(((Notebook)result2).getBrand());*/
    }
    
}

