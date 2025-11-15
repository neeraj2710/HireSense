package in.hiresense.utils;





import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class AffindaAPI {

    private static final String API_KEY = AppConfig.getProperty("api.key");


    public static String analyzeResume(File resumeFile) throws IOException{
        String boundary = "----WebKitFormBoundary" + UUID.randomUUID();
        String LINE_FEED = "\r\n";

        URL url = new URL("https://api.affinda.com/v2/resumes");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setDoOutput(true);
        //try with resources, autocloseable
        try (OutputStream output = connection.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true)) {

            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + resumeFile.getName() + "\"")
                    .append(LINE_FEED);
            writer.append("Content-Type: application/pdf").append(LINE_FEED);
            writer.append(LINE_FEED).flush();

            Files.copy(resumeFile.toPath(), output);
            output.flush();

            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.flush();
        }
        InputStream responseStream = connection.getResponseCode() == 200
                ? connection.getInputStream()
                : connection.getErrorStream();

        return new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    public static String extractSummary(String resultJson){
        String summary=null;
        try{
            JSONObject result = new JSONObject(resultJson);
            JSONObject data = result.getJSONObject("data");
            JSONArray summaryArr = data.getJSONArray("sections");
            if(summaryArr != null){
                for(int i = 0 ; i < summaryArr.length() ; i++){
                    JSONObject summaryObj = summaryArr.getJSONObject(i);
                    String sectionType = summaryObj.optString("sectionType");
                    if(sectionType.equalsIgnoreCase("Summary")){
                        summary = summaryObj.optString("text");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }

    public static String extractPersonalDetails(String resultJson){
        String personalDetails = null;
        try{
            JSONObject result = new JSONObject(resultJson);
            JSONObject data = result.getJSONObject("data");
            JSONArray suctionArr = data.getJSONArray("sections");
            if(suctionArr != null){
                for(int i = 0 ; i < suctionArr.length() ; i++){
                    JSONObject suctionObj = suctionArr.getJSONObject(i);
                    String sectionType = suctionObj.optString("sectionType");
                    if(sectionType.equalsIgnoreCase("PersonalDetails")){
                        personalDetails = suctionObj.optString("text");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails;
    }

    public static String extractEducation(String resultJson) {
        String education=null;
        try {
            JSONObject result = new JSONObject(resultJson);
            JSONObject data = result.getJSONObject("data");
            JSONArray educationArr = data.optJSONArray("sections");
            if (educationArr != null) {
                for (int i = 0; i < educationArr.length(); i++) {
                    JSONObject educationObj = educationArr.getJSONObject(i);
                    String sectionType = educationObj.optString("sectionType");
                    if(sectionType.equalsIgnoreCase("Education")) {
                        education = educationObj.optString("text");
                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return education;
    }

    public static String extractWorkExperience(String resultJson) {
        String workEx = null;
        try {
            JSONObject result = new JSONObject(resultJson);
            JSONObject data = result.getJSONObject("data");
            JSONArray workExArr = data.optJSONArray("sections");
            if (workExArr != null) {
                for (int i = 0; i < workExArr.length(); i++) {
                    JSONObject workExObj = workExArr.getJSONObject(i);
                    String sectionType = workExObj.optString("sectionType");
                    if(sectionType.equalsIgnoreCase("WorkExperience")) {
                        workEx = workExObj.optString("text");
                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return workEx;
    }

    public static List<String> extractSkills(String resultJson) {
        List<String> skills = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(resultJson);
            JSONObject data = result.getJSONObject("data");
            JSONArray skillArray = data.optJSONArray("skills");
            if (skillArray != null) {
                for (int i = 0; i < skillArray.length(); i++) {
                    JSONObject skillObj = skillArray.getJSONObject(i);
                    String name = skillObj.optString("name");
                    if (name != null && !name.isEmpty()) {
                        skills.add(name.trim().toLowerCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skills;
    }

    public static int calculateMatchScore(String jobSkillsCsv, List<String> resumeSkills){
        if(resumeSkills == null || resumeSkills.isEmpty()) return 0;

        String[] jobSkills = jobSkillsCsv.split(",");
        Set<String> required = new HashSet<>();
        for(String s : jobSkills){
            required.add(s.trim().toLowerCase());
            System.out.println(s);
        }
        int matched = 0;
        System.out.println("my skills");
        for(String r : required){
            for (String s : resumeSkills) {
                if (s.toLowerCase().contains(r.toLowerCase())) {
                    matched ++;
                    break;
                }
            }

        }

        System.out.println(matched);

        return (int) ((matched*100)/required.size());
    }

}
