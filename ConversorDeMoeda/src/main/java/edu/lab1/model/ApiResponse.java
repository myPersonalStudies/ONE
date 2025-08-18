package edu.lab1.model;

public class ApiResponse {
    private String result;
    private double conversion_rate;
    private String error_type;

    // Getters e Setters para GSON
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public double getConversion_rate() { return conversion_rate; }
    public void setConversion_rate(double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public String getError_type() { return error_type; }
    public void setError_type(String error_type) { this.error_type = error_type; }

    public boolean isSuccess() {
        return "success".equals(result);
    }
}
