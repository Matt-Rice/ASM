/**
 * @author Matt Rice
 * @version 2-13-24
 * CS 322
 * gen3.java
 * Produces a class file that can perform division on several values of data types
 */
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen3{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program3", null, "java/lang/Object",null);
        
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

        // Method visitor for the main method
        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            // Prints dividing doubles
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Dividing doubles:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
           
            // Loads and stores variables
            mv.visitLdcInsn((Double)173.43);
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)45.56);
            mv.visitVarInsn(Opcodes.DSTORE,3);

            // Divides the variables and stores the result
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            mv.visitInsn(Opcodes.DDIV);
            mv.visitVarInsn(Opcodes.DSTORE,5);

            // Prints the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            
            // Prints dividing longs
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Dividing longs:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Loads and stores variables
            mv.visitLdcInsn((Long)17345l);
            mv.visitVarInsn(Opcodes.LSTORE,1);
            mv.visitLdcInsn((Long)4456l);
            mv.visitVarInsn(Opcodes.LSTORE,3);

            // Divides the variables and stores the result
            mv.visitVarInsn(Opcodes.LLOAD,1);
            mv.visitVarInsn(Opcodes.LLOAD,3);
            mv.visitInsn(Opcodes.LDIV);
            mv.visitVarInsn(Opcodes.LSTORE,5);

            // Prints the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            // Prints dividing floats
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Dividing floats:");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); 
            
            // Loads and stores the variables
            mv.visitLdcInsn((Float)17.4f);
            mv.visitVarInsn(Opcodes.FSTORE,1);
            mv.visitLdcInsn((Float)44.5f);
            mv.visitVarInsn(Opcodes.FSTORE,2);

            // Divides the variables and stores the result
            mv.visitVarInsn(Opcodes.FLOAD,1);
            mv.visitVarInsn(Opcodes.FLOAD,2);
            mv.visitInsn(Opcodes.FDIV);
            mv.visitVarInsn(Opcodes.FSTORE,3);

            // Prints the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
            
            // Prints dividing integers
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Dividing integers:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Loads and stores variables
            mv.visitLdcInsn((Integer)17);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((Integer)44);
            mv.visitVarInsn(Opcodes.ISTORE,2);

            // Divides the variables and stores the result
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,2);
            mv.visitInsn(Opcodes.IDIV);
            mv.visitVarInsn(Opcodes.ISTORE,3);

            // Prints the result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }// end methodvisitor
        
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program3.class");
        
        System.out.println("Done!");
    }//end main
}//end gen3

