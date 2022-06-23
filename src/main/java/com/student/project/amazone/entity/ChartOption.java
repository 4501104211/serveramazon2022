package com.student.project.amazone.entity;

import lombok.Data;




@Data
public class ChartOption {
    private int Jan;
    private int Feb;
    private int Mar;
    private int May;
    private int Jul;
    private int Sep;
    private int Nov;
    private int Apr;
    private int Jun;
    private int Aug;
    private int Oct;
    private int Dec;




    public ChartOption(int jan, int mar, int may, int jul, int sep, int nov, int feb, int apr, int jun, int aug, int oct, int dec) {
        Jan = jan;
        Mar = mar;
        May = may;
        Jul = jul;
        Sep = sep;
        Nov = nov;
        Feb = feb;
        Apr = apr;
        Jun = jun;
        Aug = aug;
        Oct = oct;
        Dec = dec;
//        this.total_yearly = total_yearly;
    }
}
