package student.testSuite.lab3.problem3;

/**
 * Utility class to verify if Shape is declared as an interface.
 */
@Deprecated
public class ShapeInterfaceChecker {
    
    /**
     * Main method to check if Shape is an interface.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        checkShapeInterface();
    }
    
    /**
     * Checks if Shape is declared as an interface using reflection.
     */
    public static void checkShapeInterface() {
        try {
            // Get the Shape class using reflection
            Class<?> shapeClass = Class.forName("Shape");
            
            // Check if it's an interface
            boolean isInterface = shapeClass.isInterface();
            
            if (isInterface) {
                System.out.println("✓ Shape is declared as an INTERFACE");
                System.out.println("  Modifiers: " + java.lang.reflect.Modifier.toString(shapeClass.getModifiers()));
                
                // Display declared methods
                System.out.println("\n  Declared methods:");
                java.lang.reflect.Method[] methods = shapeClass.getDeclaredMethods();
                for (java.lang.reflect.Method method : methods) {
                    System.out.println("    - " + method.getName() + 
                                     ": " + method.getReturnType().getSimpleName());
                }
            } else {
                System.out.println("✗ Shape is NOT an interface - it's a regular class");
                System.out.println("  Modifiers: " + java.lang.reflect.Modifier.toString(shapeClass.getModifiers()));
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Shape class not found - " + e.getMessage());
        }
    }
    
    /**
     * Alternative method to check if a class implements Shape interface.
     * 
     * @param className the name of the class to check
     * @return true if the class implements Shape, false otherwise
     */
    public static boolean implementsShape(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?>[] interfaces = clazz.getInterfaces();
            
            for (Class<?> iface : interfaces) {
                if (iface.getName().equals("Shape")) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class " + className + " not found");
            return false;
        }
    }
}
