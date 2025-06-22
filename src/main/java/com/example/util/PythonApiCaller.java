package com.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonApiCaller {
    public static String getApiResult() {
        StringBuilder result = new StringBuilder();
        try {
            // Command to execute the Python script
            Process process = Runtime.getRuntime().exec("face_id/main.py");


            // Read the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching API result.";
        }
        return result.toString();
    }
}

