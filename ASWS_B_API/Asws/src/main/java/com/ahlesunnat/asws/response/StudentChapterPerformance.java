package com.ahlesunnat.asws.response;

import java.util.Map;

public class StudentChapterPerformance {
    private Map<String, Object> studentName;
    private Long chapterId;
    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    private double performance;

    // Constructors, getters, and setters

    public StudentChapterPerformance() {
        // Default constructor
    }

    public StudentChapterPerformance(Map<String, Object> studentName, double performance) {
        this.studentName = studentName;
        this.performance = performance;
    }

    public Map<String, Object> getStudentName() {
        return studentName;
    }

    public void setStudentName(Map<String, Object> map) {
        this.studentName = map;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }
}
