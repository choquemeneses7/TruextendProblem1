package com.tx.TruextendProblem1.entities;

public class CourseStudentDetail {

    private int studentId;
    private int code;

    public CourseStudentDetail(int studentId, int code) {
        this.studentId = studentId;
        this.code = code;
    }

    public CourseStudentDetail() {
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
