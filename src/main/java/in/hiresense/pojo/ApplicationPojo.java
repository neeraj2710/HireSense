package in.hiresense.pojo;

public class ApplicationPojo {
    private int id;
    private int userId;
    private int jobId;
    private String resume_path;
    private double score;
    private String status;
    private String appliedAt;

    public ApplicationPojo(int id, int userId, int jobId, String resume_path, double score, String status, String appliedAt) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.resume_path = resume_path;
        this.score = score;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public ApplicationPojo() {
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

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getResume_path() {
        return resume_path;
    }

    public void setResume_path(String resume_path) {
        this.resume_path = resume_path;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(String appliedAt) {
        this.appliedAt = appliedAt;
    }

    @Override
    public String toString() {
        return "ApplicationPojo{" +
                "id=" + id +
                ", userId=" + userId +
                ", jobId=" + jobId +
                ", resume_path='" + resume_path + '\'' +
                ", score=" + score +
                ", status='" + status + '\'' +
                ", appliedAt='" + appliedAt + '\'' +
                '}';
    }
}
