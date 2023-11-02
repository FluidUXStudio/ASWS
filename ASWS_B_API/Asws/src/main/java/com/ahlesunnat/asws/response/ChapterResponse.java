package com.ahlesunnat.asws.response;



public class ChapterResponse {
    
    
    private Long id;
    public ChapterResponse(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private Boolean isCompleted;
    private String chapterName;
    public ChapterResponse() {
    }
    public ChapterResponse(Boolean isCompleted, String chapterName) {
        this.isCompleted = isCompleted;
        this.chapterName = chapterName;
    }
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public String getChapterName() {
        return chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }


    
}
