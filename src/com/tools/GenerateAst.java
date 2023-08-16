package com.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

// Script for generating Abstract Syntax tree class file
public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: GenerateAst <output directory>");
            System.exit(-1);
        }
        String outputDir = args[0];

        defineAst(outputDir, "Expr", Arrays.asList(
                "Binary : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal : Object value",
                "Unary : Token operator, Expr right"
        ));
    }

    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

        writer.println("package com.goober;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        // Defining visitor
        defineVisitor(writer, baseName, types);
        writer.println();

        // Defining abstract method for each expr subclass to implement
        writer.println("\tabstract <T> T accept(Visitor<T> visitor);");
        writer.println();

        // Body of class
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        writer.println("}");
        writer.close();
    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println("\tinterface Visitor<T> {");

        for (String type : types) {
            String className = type.split(":")[0].trim();
            writer.println("\t\tT visit" + className + baseName + "(" + className + " " + baseName.toLowerCase() + ");");
        }
        writer.println("\t}");
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fields) {
        writer.println("\tstatic class " + className + " extends " + baseName + " {");

        // Instance variables
        String[] fieldList = fields.split(", ");
        for (String field : fieldList) {
            writer.println("\t\tfinal " + field + ";");
        }
        writer.println();

        // Constructor
        writer.println("\t\t"+ className + "(" + fields + ") {");
        for (String field : fieldList) {
            String name = field.split(" ")[1];
            writer.println("\t\t\tthis." + name + " = " + name + ";");
        }
        writer.println("\t\t}");
        writer.println();

        // Implementing abstract accept() method
        writer.println("\t\t@Override");
        writer.println("\t\t<T> T accept(Visitor<T> visitor) {");
        writer.println("\t\t\treturn visitor.visit" + className + baseName + "(this);");
        writer.println("\t\t}");

        writer.println("\t}");
        writer.println();
    }
}
