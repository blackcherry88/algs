package demo.antlr;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UppercaseMethodListener extends Java8ParserBaseListener {

    private final List<String> errors = new ArrayList<>();

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        TerminalNode node = ctx.Identifier();
        String methodName = node.getText();

        if (Character.isUpperCase(methodName.charAt(0))){
            errors.add(String.format("Method %s is uppercased!", methodName));
        }
    }

    public List<String> getErrors(){
        return Collections.unmodifiableList(errors);
    }
}