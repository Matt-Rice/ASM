import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen8{

    public static void main(String[] args){

        Label oddLabel = new Label(); // Label that will allow it to jump to the else statement if the number is odd
        Label endMethod = new Label(); // Label that will jump to the end of the method

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program8", null, "java/lang/Object",null);
        
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

        MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);{
            mv.visitCode();
            
           // Initializes the Scanner object
           mv.visitTypeInsn(Opcodes.NEW, "Ljava/util/Scanner"); // Creates Scanner and pushes it to the stack
           mv.visitInsn(Opcodes.DUP); // Duplicates the Scanner reference which will be used in initializing and storing the scanner
           mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); // System.in
           mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false); // Initializes Scanner
           mv.visitVarInsn(Opcodes.ASTORE, 1); // Stores Scanner

           // Prompts the user to enter an integer
           mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
           mv.visitLdcInsn("Please enter an integer value: ");  
           mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

           // Read integer input from the user
           mv.visitVarInsn(Opcodes.ALOAD, 1); // Loads scanner
           mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false); // Scan.nextLong()
           mv.visitVarInsn(Opcodes.ISTORE, 2); // Store the int value in a variable

           // Computes and stores the remainder of the inputted variable and 2 to see if it is an odd number
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitIntInsn(Opcodes.BIPUSH, 2);
            mv.visitInsn(Opcodes.IREM);
            mv.visitVarInsn(Opcodes.ISTORE, 3);

            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitJumpInsn(Opcodes.IFGT, oddLabel); // if input%2 > 0, then jump to odd label
            
            // Executes IFF the inputted number is even (input%2 == 0)

                // Prints that the entered number is even
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("This number is even!");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                
                // Jumps to the end once the code is executed
                mv.visitJumpInsn(Opcodes.GOTO, endMethod);

            // Label for if the input is odd. Executes iff the inputted number is odd. (input%2==1)
            mv.visitLabel(oddLabel);
            
                // Prints that the number is odd
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("This number is odd!");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            // Label for the end of the main method
            mv.visitLabel(endMethod);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        
        byte[] b = cw.toByteArray();

        writeFile(b,"program8.class");
        
        System.out.println("Done!");
    }//end main
}//end gen8
    
