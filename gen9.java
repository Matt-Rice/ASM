/**
 * @author Matt Rice
 * @version 2-16-24
 * CS 322
 * gen9.java
 * Produces a class file that will get inputs from a user and run a loop 
 * that adds the entered values to an accumulator and prints the result, the user will enter 0 to stop
 */

import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen9{

    public static void main(String[] args){

        Label startLoop = new Label(); // Label that will restart the loop given that the user does not enter 0
        Label endLoop = new Label(); // Label that will break out of the loop and print the result

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program9", null, "java/lang/Object",null);
        
        //Creating Constructor for the class
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        // main

        {
        MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
           // Initializes the Scanner object
           mv.visitTypeInsn(Opcodes.NEW, "Ljava/util/Scanner"); // Creates Scanner and pushes it to the stack
           mv.visitInsn(Opcodes.DUP); // Duplicates the Scanner reference which will be used in initializing and storing the scanner
           mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); // System.in
           mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false); // Initializes Scanner
           mv.visitVarInsn(Opcodes.ASTORE, 1); // Stores Scanner
            
            //Declare and initialize the accumulator variable to zero
            mv.visitVarInsn(Opcodes.ICONST_0, 2);

            // Label that will be visited to restart the loop
            mv.visitLabel(startLoop);
           // Body of While loop that executes if the user did not enter 0

            // Prompts the user to enter an integer or enter 0 to exit
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Please enter an integer value to add to the accumulator or 0 to exit: ");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            // Read integer input from the user
            mv.visitVarInsn(Opcodes.ALOAD, 1); // Loads scanner
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false); // Scan.nextLong()
            mv.visitVarInsn(Opcodes.ISTORE, 3); // Store the input value in a variable

            // Checks to see if the user inputted zero and if so, will exit the loop
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop);

            // Adds the current value of i with the running total of the previous sums of i and sum: sum+=i
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 2);

            // Prints a current accumulator value String
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Current Accumulator Value: ");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Prints a string that prints the value of the current accumulator
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            // Returns to the top of the loop
            mv.visitJumpInsn(Opcodes.GOTO, startLoop);

        // Label that will end the loop
        mv.visitLabel(endLoop);

        // Prints a String that says final accumulator value 
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Final Accumulator Value: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        
        // Prints the final accumulator value
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }// end main
        
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program9.class");
        
        System.out.println("Done!");
    }//end main
}//end gen9
    
