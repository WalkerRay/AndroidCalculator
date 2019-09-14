package com.example.calculator;

import org.litepal.crud.LitePalSupport;

public class CalculatorModel extends LitePalSupport {
    private String expression;
    private String result;

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
