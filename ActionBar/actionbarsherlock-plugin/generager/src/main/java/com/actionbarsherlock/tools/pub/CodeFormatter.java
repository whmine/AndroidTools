package com.actionbarsherlock.tools.pub;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

public class CodeFormatter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("import java.util.ArrayList;").append("\n");
        sb.append("import java.util.List;").append("\n");
        sb.append("public class TestCodeFormat{").append("\n");
        sb.append("public static void main(String[] args){ ").append("\n");
        sb.append("List list = new ArrayList();").append("\n");
        sb.append("list.add(\"1\");").append("\n");
        sb.append("list.add(\"2\");").append("\n");
        sb.append("list.add(\"3\");").append("\n");
        sb.append("System.out.println(\"List: \" + list.toString());").append(
                "\n");
        sb.append("}").append("\n");
        sb.append("}").append("\n");

        System.out.println(sb.toString());
        try {
            String str = format(sb.toString());
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String format(String fileContent) throws Exception {
        String sourceCode = fileContent;
        // get default format for java
        Map options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();
        DefaultCodeFormatterOptions preferences = new DefaultCodeFormatterOptions(
                options);
        Document doc = new Document(sourceCode);

        Map compilerOptions = new HashMap();
        // confirm java source base on java 1.5
        compilerOptions.put(CompilerOptions.OPTION_Compliance,
                CompilerOptions.VERSION_1_5);
        compilerOptions.put(CompilerOptions.OPTION_TargetPlatform,
                CompilerOptions.VERSION_1_5);
        compilerOptions.put(CompilerOptions.OPTION_Source,
                CompilerOptions.VERSION_1_5);
        DefaultCodeFormatter codeFormatter = new DefaultCodeFormatter(
                preferences, compilerOptions);
        // format
        TextEdit edits = codeFormatter.format(codeFormatter.K_COMPILATION_UNIT,
                sourceCode, 0, sourceCode.length(), 0, null);
        try {
            edits.apply(doc);
        } catch (MalformedTreeException e) {
            throw new RuntimeException(e);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        sourceCode = doc.get();

        return sourceCode;
    }

}
