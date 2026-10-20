package fr.laboratoire.calculatricetest;

import java.math.BigDecimal;
import java.math.MathContext;

public final class CalculatorEngine {
    private String display = "0";
    private BigDecimal left = null;
    private String operator = null;
    private boolean fresh = false;

    public String display() { return display; }

    public void press(String key) {
        if ("C".equals(key)) { display = "0"; left = null; operator = null; fresh = false; return; }
        if ("0123456789".contains(key) && key.length() == 1) {
            if (fresh || "0".equals(display) || "Erreur".equals(display)) { display = key; fresh = false; }
            else if (display.length() < 16) display += key;
            return;
        }
        if (".".equals(key)) {
            if (fresh || "Erreur".equals(display)) { display = "0"; fresh = false; }
            if (!display.contains(".")) display += ".";
            return;
        }
        if ("+-×÷".contains(key) && key.length() == 1) {
            if ("Erreur".equals(display)) return;
            try {
                if (left != null && operator != null && !fresh) {
                    display = calculate(left, new BigDecimal(display), operator);
                }
                if ("Erreur".equals(display)) { left = null; operator = null; return; }
                left = new BigDecimal(display);
                operator = key;
                fresh = true;
            } catch (ArithmeticException e) { error(); }
            return;
        }
        if ("=".equals(key) && left != null && operator != null) {
            try { display = calculate(left, new BigDecimal(display), operator); }
            catch (ArithmeticException e) { error(); return; }
            left = null; operator = null; fresh = true;
        }
    }

    private String calculate(BigDecimal a, BigDecimal b, String op) {
        BigDecimal value;
        switch (op) {
            case "+": value = a.add(b); break;
            case "-": value = a.subtract(b); break;
            case "×": value = a.multiply(b); break;
            case "÷":
                if (b.compareTo(BigDecimal.ZERO) == 0) return "Erreur";
                value = a.divide(b, MathContext.DECIMAL64); break;
            default: throw new IllegalArgumentException("Unknown operation");
        }
        return value.stripTrailingZeros().toPlainString();
    }
    private void error() { display = "Erreur"; left = null; operator = null; fresh = true; }
}
