/**
 * @author Matt Rice
 * @version 2-16-24
 * CS 322
 * gen7.java
 * Produces a class file that will run a while loop that adds the values of 1-10 and prints the result
 */
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen7{

    public static void main(String[] args){

        Label startLoop = new Label(); // Label that will allow it to jump to the beginning of the while loop
        Label endMethod = new Label(); // Label that will jump to the end of the method

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program7", null, "java/lang/Object",null);
        
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

        //main

        {
        MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            // Loads two values
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitVarInsn(Opcodes.ISTORE, 1); // Variable that will be the loop condition (i)
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitVarInsn(Opcodes.ISTORE, 2); // Variable that will store the total sum of 1-10 (sum)

        // Loop will essentially be while(i<10) with i being the variable stored at index 1

            // Start of the loop
            mv.visitLabel(startLoop);

            /* Loads i and pushes 10 to the stack so they can be compared. If i is greater than or 
            equal to 10, the program will jump to the endMethod label which will exit the loop */
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitIntInsn(Opcodes.BIPUSH, 10);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, endMethod);

            // Increments i by 1
            mv.visitVarInsn(Opcodes.IINC, 1);

            // Adds the current value of i with the running total of the previous sums of i and sum: sum+=i
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 2);

            // Prints a string that indicates the current sum
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Current Sum: ");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Prints the current value of the sum
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            // Goes back to the top of the loop
            mv.visitJumpInsn(Opcodes.GOTO, startLoop);
            
            
            // Label for the end of the main method
            mv.visitLabel(endMethod);

            // Prints that the loop is done
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Loop is done!");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }// end main methodvisitor

        cw.visitEnd();
        
        byte[] b = cw.toByteArray();

        writeFile(b,"program7.class");
        
        System.out.println("Done!");
    }//end main
}//end gen7
    

