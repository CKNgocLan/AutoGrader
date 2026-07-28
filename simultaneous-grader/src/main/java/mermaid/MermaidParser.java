package mermaid;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MermaidParser {
    
    public List<ClassCheckingObject> parse(String mermaidCode) {
        List<ClassCheckingObject> classes = new ArrayList<>();
        ClassCheckingObject currentClass = null;

        String[] lines = mermaidCode.split("\n");
        
        // Regex patterns
        Pattern classPattern = Pattern.compile("^class\\s+(\\w+)(?:\\s*\\{)?");
        Pattern annotationPattern = Pattern.compile("<<(.+)>>");
        // Matches: scope, type, name, method params (if any), modifiers/init values
        Pattern memberPattern = Pattern.compile("^\\s*([\\+\\-\\#\\~])?\\s*(static\\s+)?(final\\s+)?([\\w<>]+)\\s+([\\w]+)\\s*(\\([^)]*\\))?\\s*(?:\\*\\s*)?(?:=\\s*(.+))?\\s*$");

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("classDiagram")) continue;
            
            // 1. Check for Class declaration
            Matcher classMatcher = classPattern.matcher(line);
            if (classMatcher.find()) {
                currentClass = new ClassCheckingObject(classMatcher.group(1), ClassType.NORMAL);
                classes.add(currentClass);
                continue;
            }

            if (currentClass == null) continue;

            // 2. Check for Annotations (<<interface>>, <<enum>>, <<abstract>>)
            Matcher annotationMatcher = annotationPattern.matcher(line);
            if (annotationMatcher.find()) {
                String annotation = annotationMatcher.group(1).toLowerCase();
                if (annotation.contains("interface")) currentClass.setType(ClassType.INTERFACE);
                else if (annotation.contains("enum") || annotation.contains("enumeration")) currentClass.setType(ClassType.ENUM);
                else if (annotation.contains("abstract")) currentClass.setType(ClassType.ABSTRACT);
                else if (annotation.contains("inner static")) currentClass.setType(ClassType.INNER_STATIC);
                continue;
            }

            // 3. Check for Fields and Methods
            Matcher memberMatcher = memberPattern.matcher(line);
            if (memberMatcher.find()) {
                Scope scope = Scope.fromSymbol(memberMatcher.group(1) != null ? memberMatcher.group(1) : "");
                boolean isStatic = memberMatcher.group(2) != null || line.contains("$"); // $ is Mermaid's static symbol
                boolean isFinal = memberMatcher.group(3) != null;
                String type = memberMatcher.group(4);
                String name = memberMatcher.group(5);
                String params = memberMatcher.group(6);
                String initValue = memberMatcher.group(7);
                boolean isAbstract = line.endsWith("*"); // * is Mermaid's abstract symbol

                if (params != null) {
                    // It's a method
                    List<String> paramList = new ArrayList<>();
                    String rawParams = params.substring(1, params.length() - 1).trim();
                    if (!rawParams.isEmpty()) {
                        for (String p : rawParams.split(",")) paramList.add(p.trim());
                    }
                    // In Mermaid, return type is often placed at the end for methods, 
                    // but our regex handles standard Java-like "Type name()" gracefully.
                    currentClass.addMethod(new MethodCheckingObject(scope, type, name, isAbstract, paramList));
                } else {
                    // It's a field
                    currentClass.addField(new FieldCheckingObject(scope, type, name, isStatic, isFinal, initValue));
                }
            }
        }
        return classes;
    }
}