package com.tx.TruextendProblem1.entities;

public class ClassStudentDetail {

    private int studentId;
    private int code;

    public ClassStudentDetail(int studentId, int code) {
        this.studentId = studentId;
        this.code = code;
    }

    public ClassStudentDetail() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
