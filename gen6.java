/**
 * @author Matt Rice
 * @version 2-16-24
 * CS 322
 * gen6.java
 * Produces a class file that can read inputs of several different types and then can print them
 */
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen6{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program6", null, "java/lang/Object",null);
        
        // Creating the constructor
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        // MethodVisitor for the main method

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
           
            // Initializes the Scanner object
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner"); // Creates Scanner and pushes it to the stack
            mv.visitInsn(Opcodes.DUP); // Duplicates the Scanner reference which will be used in initializing and storing the scanner
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); // System.in
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false); // Initializes Scanner
            mv.visitVarInsn(Opcodes.ASTORE, 1); // Stores Scanner

            // Integer Scanning and Printing

                // Prompts the user to enter an integer
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Please enter an integer value: ");  
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    
                // Read integer input from the user
                mv.visitVarInsn(Opcodes.ALOAD, 1); // Loads scanner
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false); // Scan.nextLong()
                mv.visitVarInsn(Opcodes.ISTORE, 2); // Store the int value in a variable
    
                // Print the entered int value
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD, 2);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    
            // Long Printing and Scanning

                // Prompts the user to enter a long
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Please enter a long value: ");  
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                // Read long input from the user
                mv.visitVarInsn(Opcodes.ALOAD, 1); // Loads scanner
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLong", "()J", false); // Scan.nextLong()
                mv.visitVarInsn(Opcodes.LSTORE, 2); // Store the long value in a variable

                // Print the entered long value
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.LLOAD, 2);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
    
            // Double Scanning and Printing

                // Prompts the user to enter a double
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Please enter a double value: ");  
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    
                // Read double input from the user
                mv.visitVarInsn(Opcodes.ALOAD, 1); // Loads scanner
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false); // Scan.nextLong()
                mv.visitVarInsn(Opcodes.DSTORE, 2); // Store the long value in a variable
    
                // Print the entered double value
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.DLOAD, 2);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            
            // Ends the method
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }// end main methodvisitor

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program6.class");
        
        System.out.println("Done!");
    }//end main
}//end gen6
