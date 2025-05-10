package com.example.bai24_th_android_finderdialog;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileFinder {

    private static final String LOG_TAG_FILE_FINDER = "FileSelector";

    private boolean enableLog;

    public FileFinder(boolean enableLog) {
        this.enableLog = enableLog;
    }

    public List<File> findByKeyword(File rootDir, String keywordFileName) {
        if(keywordFileName==null || keywordFileName.isEmpty())  {
            return new ArrayList<File>();
        }
        String regexFileName = keywordFileName.replace("*", ".*?");

        List<File> resultList = this.listOfFile(rootDir, regexFileName);
        return resultList;
    }

    public List<File> findByRegex(File rootDir, String regexFileName) {

        List<File> resultList = this.listOfFile(rootDir, regexFileName);
        return resultList;
    }

    public List<File> findInSDCardByKeyword(String keywordFileName)  {
        // (Example): /storage/emulated/0
        String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.log("External Storage Directory: " + sdCardPath);

        File sdCardDir = new File(sdCardPath);
        return this.findByKeyword(sdCardDir, keywordFileName);
    }

    public List<File> findInSDCardByRegex(String regexFileName)  {
        // (Example): /storage/emulated/0
        String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.log("External Storage Directory: " + sdCardPath);

        File sdCardDir = new File(sdCardPath);
        return this.findByRegex(sdCardDir, regexFileName);
    }

    private List<File> listOfFile(File dir, String regexFileName) {
        Pattern patternFileName = Pattern.compile(regexFileName);

        List<File> resultList = new ArrayList<File>();
        this.listOfFile(dir, patternFileName, resultList);
        return resultList;
    }

    private void listOfFile(File dir, Pattern patternFileName, List<File> resultList) {
        this.log("LIST OF DIR " + dir.getAbsolutePath());
        File[] list = dir.listFiles();

        if(list == null)  {
            this.log("Directory" + dir.getAbsolutePath()+ " has no files");
            return;
        }
        this.log("Directory" + dir.getAbsolutePath()+ " has " + list.length +" direct files");

        for (File file : list) {
            if (file.isDirectory()) {
                if (!new File(file, ".nomedia").exists() && !file.getName().startsWith(".")) {
                    this.log( "IS DIR " + file);
                    listOfFile(file, patternFileName, resultList);
                }
            } else {
                String path = file.getAbsolutePath();
                this.log( "FILE PATH: " + path);

                String fileName = file.getName();
                if(patternFileName.matcher(fileName).find())  {
                    resultList.add(file);
                    this.log( "ADD " + path);
                }
            }
        }
    }

    private void log(String message)  {
        if(enableLog) {
            Log.i(LOG_TAG_FILE_FINDER, message);
        }
    }
}
