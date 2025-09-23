package in.hiresense.pojo;

import java.util.Date;

public class ResumeAnalysisLogsPojo {
    private int id;
    private int userId;
    private String jsonResult;
    private String createdAt;

    public ResumeAnalysisLogsPojo(int id, int userId, String jsonResult, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.jsonResult = jsonResult;
        this.createdAt = createdAt;
    }

    public ResumeAnalysisLogsPojo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ResumeAnalysisLogsPojo{" +
                "id=" + id +
                ", userId=" + userId +
                ", jsonResult='" + jsonResult + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
