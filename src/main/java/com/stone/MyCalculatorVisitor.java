package com.stone;

public class MyCalculatorVisitor extends CalculatorBaseVisitor<Object> {
    @Override
    public Object visitAddOrSubtract(CalculatorParser.AddOrSubtractContext ctx) {
        Object obj0 = ctx.expr(0).accept(this);
        Object obj1 = ctx.expr(1).accept(this);
        if ("+".equals(ctx.getChild(1).getText())) {
            return (Float)obj0 + (Float)obj1;
        } else if ("-".equals(ctx.getChild(1).getText())) {
            return (Float)obj0 - (Float)obj1;
        }
        return 0;
    }

    @Override
    public Object visitFloat(CalculatorParser.FloatContext ctx) {
        return Float.parseFloat(ctx.getText());
    }

    @Override
    public Object visitParenExpr(CalculatorParser.ParenExprContext ctx) {
        Object obj0 = ctx.expr().accept(this);
        return (Float)obj0;
    }

    @Override
    public Object visitMultiOrDiv(CalculatorParser.MultiOrDivContext ctx) {
        Object obj0 = ctx.expr(0).accept(this);
        Object obj1 = ctx.expr(1).accept(this);
        if ("/".equals(ctx.getChild(1).getText())) {
            Float right = (Float)obj1;
            if (Math.abs(right - 0)< 0.000001) {
                return 0;
            }
            return (Float)obj0 / right;
        } else if ("*".equals(ctx.getChild(1).getText())) {
            return (Float)obj0 * (Float)obj1;
        }
        return 0;
    }

    @Override public Object visitLine(CalculatorParser.LineContext ctx) {
        Object obj0 = ctx.expr().accept(this);
        return (Float)obj0;
    }
}