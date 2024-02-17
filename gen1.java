/**
 * @author Matt Rice
 * @version 2-13-24
 * CS 322
 * gen1.java
 * Produces a class file that can perform multiplication on several values of data types
 */
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen1{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program1", null, "java/lang/Object",null);
        
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

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            // Print Multiplying doubles:
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Multiplying doubles:");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            //Multiplying doubles
            mv.visitLdcInsn((double)3.14); // first param
            mv.visitVarInsn(Opcodes.DSTORE,1); // store first parameter

            mv.visitLdcInsn((double)2.71); // second param
            mv.visitVarInsn(Opcodes.DSTORE,3); // store second parameter

            mv.visitVarInsn(Opcodes.DLOAD,1); // load var1
            mv.visitVarInsn(Opcodes.DLOAD,3); // load var2
            mv.visitInsn(Opcodes.DMUL); // multiply var1 and var2
            mv.visitVarInsn(Opcodes.DSTORE,5); // store product

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // get out
            mv.visitVarInsn(Opcodes.DLOAD, 5); // load product
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); // invoke print
           

            // Print Multiplying longs:
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Multiplying longs:"); 
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // Multiplying longs
            mv.visitLdcInsn(300l); // first param
            mv.visitVarInsn(Opcodes.LSTORE,1); // store param 1

            mv.visitLdcInsn(400l);
            mv.visitVarInsn(Opcodes.LSTORE,3); // store second param

            mv.visitVarInsn(Opcodes.LLOAD,1); // load var1
            mv.visitVarInsn(Opcodes.LLOAD,3); // load var2
            mv.visitInsn(Opcodes.LMUL); // multiply var1 and var2
            mv.visitVarInsn(Opcodes.LSTORE,5); // store product

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // get out
            mv.visitVarInsn(Opcodes.LLOAD, 5); // load product
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(L)V", false); // print
            
           
            // Print Multiplying floats:
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Multiplying floats:");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); 
            
            // loading values to the constant pool and invoking multiplyFloat()
            mv.visitLdcInsn((float)2.71); // second param
            mv.visitVarInsn(Opcodes.FSTORE,0); // store first param

            mv.visitLdcInsn((float)3.14); // first param
            mv.visitVarInsn(Opcodes.FSTORE,1); // store second param

            mv.visitVarInsn(Opcodes.FLOAD,0); // load var1
            mv.visitVarInsn(Opcodes.FLOAD,1); // load var2
            mv.visitInsn(Opcodes.FMUL); // product of var1 and var2
            mv.visitVarInsn(Opcodes.FSTORE,3); // store product

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // get out
            mv.visitVarInsn(Opcodes.FLOAD, 4); // load product
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false); // invoke print
            
            
            
            // Print Multiplying Integers:
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Multiplying integers:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            // loading values to the constant pool and invoking multiplyInteger()
            mv.visitLdcInsn(40); // first param
            mv.visitVarInsn(Opcodes.ISTORE,0); // store first param

             // load second param
             mv.visitLdcInsn(26); // second param
            mv.visitVarInsn(Opcodes.ISTORE,1); // store second param

            mv.visitVarInsn(Opcodes.ILOAD,0); // load var1
            mv.visitVarInsn(Opcodes.ILOAD,1); // load var2
            mv.visitInsn(Opcodes.IMUL); // multiply var1 and var2
            mv.visitVarInsn(Opcodes.ISTORE,2); // store product

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // get out
            mv.visitVarInsn(Opcodes.ILOAD, 2); // load product
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); // invoke print
            
            // Ending main
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program1.class"); // Writing class file
        
        System.out.println("Done!");
    }//end main
}//end class

