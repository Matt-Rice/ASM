import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen4backup{

    public static void main(String[] args){

        Label lGreaterEqual = new Label();
        Label iGreaterEqual = new Label();
        Label sGreaterEqual = new Label();

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

        // MethodVisitor for comparing doubles
        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "compareLong", "()V", null, null);
            mv.visitCode();
            
            mv.visitLdcInsn(48l);
            mv.visitVarInsn(Opcodes.LSTORE,1);

            mv.visitLdcInsn(45l);
            mv.visitVarInsn(Opcodes.LSTORE,3);

            mv.visitVarInsn(Opcodes.LLOAD,1);
            mv.visitVarInsn(Opcodes.LLOAD,3);
            mv.visitInsn(Opcodes.LCMP); // returns 1 if num1>num2, 0 num1==num2, -1 num1<num2
            mv.visitVarInsn(Opcodes.ISTORE,5); // store comparison result

            mv.visitVarInsn(Opcodes.ILOAD, 5); // load comparison result
            mv.visitJumpInsn(Opcodes.IFGE, lGreaterEqual); // If cmp result >=0 jump to lgreaterEqual label

            // num1<num2
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(L)V", false);

            // num1>=num2
            mv.visitLabel(lGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(L)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);

            mv.visitEnd();
        }
         
        // MethodVisitor for comparing Integers
        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "compareInteger", "()V", null, null);
            mv.visitCode();
            
            mv.visitLdcInsn((Integer)17);
            mv.visitVarInsn(Opcodes.ISTORE,1);

            mv.visitLdcInsn((Integer)44);
            mv.visitVarInsn(Opcodes.ISTORE,3);

            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,3);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, iGreaterEqual);
    
            // num1<num2
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            // num1>=num2
            mv.visitLabel(iGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "compareShort", "()V", null, null);
            mv.visitCode();
            mv.visitIntInsn(Opcodes.SIPUSH,17); // push first operand to stack
            mv.visitVarInsn(Opcodes.ISTORE,3); // store first operand

            mv.visitIntInsn(Opcodes.SIPUSH, 44); // push second operand to stack
            mv.visitVarInsn(Opcodes.ISTORE,4); // store second operand

            mv.visitVarInsn(Opcodes.ILOAD,3);
            mv.visitVarInsn(Opcodes.ILOAD,4);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, sGreaterEqual); //if num1>=num2 then jump to iG
    
            // num1<num2
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            // num1>=num2
            mv.visitLabel(sGreaterEqual);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
    
       // Main
        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing doubles:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "program4", "compareDouble", "()I", false);
            
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing longs:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "program2", "compareLong", "()V", false);
           
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Comparing shorts:");  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); 
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "program4", "compareShort", "()V", false);
           
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
        }

       
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program4.class");
        
        System.out.println("Done!");
    }
}