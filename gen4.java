/**
 * @author Matt Rice
 * @version 2-20-24
 * CS 322
 * gen4.java
 * Produces a class file that can compare two values of the same data type and print which is greater
 */
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen4{

    public static void main(String[] args){

        Label lGreaterEqual = new Label();
        Label iGreaterEqual = new Label();
        Label sGreaterEqual = new Label();
        Label compareInt = new Label();
        Label compareLong = new Label();
        Label endProgram = new Label();

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program4", null, "java/lang/Object",null);
        
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
    
       // Main
        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            // Prints comparing shorts
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing shorts:");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); 
            
            // Push the shorts to the stack and stores them
            mv.visitIntInsn(Opcodes.SIPUSH,17); // push first operand to stack
            mv.visitVarInsn(Opcodes.ISTORE,1); // store first operand
            mv.visitIntInsn(Opcodes.SIPUSH, 44); // push second operand to stack
            mv.visitVarInsn(Opcodes.ISTORE,2); // store second operand

            // Compares the two shorts and will jump to label sGreaterEqual if the first one is greater than or equal to the second
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,2);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, sGreaterEqual); //if short>=short2 then jump to sGreaterEqual
    
            // Executes iff short1<short2 and will print the second short which is greater than the first number
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            //Jump to next comparison
            mv.visitJumpInsn(Opcodes.GOTO, compareInt);

            // gets jumped to if num1>=num2
            mv.visitLabel(sGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitLabel(compareInt); // Jump location for comparing integers

            // Prints comparing integers
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing Integers:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Loading and storing variables
            mv.visitLdcInsn((Integer)17);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((Integer)49);
            mv.visitVarInsn(Opcodes.ISTORE,3);

            // Compares the two variables and if the first is greater than or equal to the second, it will jump to the label iGreaterEqual
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,3);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, iGreaterEqual);
    
            // If int1<int2 prints the second int variable
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            //Jump to next comparison
            mv.visitJumpInsn(Opcodes.GOTO, compareLong);

            // If int1>=int2 prints the first int variable
            mv.visitLabel(iGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            

            mv.visitLabel(compareLong); // Jump location for comparing integers

            // Prints comparing longs
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing longs:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Loads and stores variables
            mv.visitLdcInsn(4800l);
            mv.visitVarInsn(Opcodes.LSTORE,5);
            mv.visitLdcInsn(4895l);
            mv.visitVarInsn(Opcodes.LSTORE,7);

            // Compares the two longs and compares them using LCMP
            mv.visitVarInsn(Opcodes.LLOAD,5);
            mv.visitVarInsn(Opcodes.LLOAD,7);
            mv.visitInsn(Opcodes.LCMP); // returns 1 if long1>long2, 0 long1==long2, -1 long1<long2
            mv.visitVarInsn(Opcodes.ISTORE,9); // store comparison result
            
            // Loads the comparison result and if it is greater than equal to zero, it will jump to lGreaterEqual
            mv.visitVarInsn(Opcodes.ILOAD, 9); // load comparison result
            mv.visitJumpInsn(Opcodes.IFGE, lGreaterEqual); // If cmp result >=0 jump to lgreaterEqual label

            // long1<long2
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 7);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);

            //jump to end of program
            mv.visitJumpInsn(Opcodes.GOTO, endProgram);

            // long1>=long2
            mv.visitLabel(lGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            mv.visitLabel(endProgram);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }// end main method visitor

       
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program4.class");
        
        System.out.println("Done!");
    }// end main
}// end gen4
