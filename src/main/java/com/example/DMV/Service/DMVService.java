package com.example.DMV.Service;

import com.example.DMV.Model.DMV;
import com.example.DMV.Repository.DMVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class DMVService {

    private DMVRepository dmvRepository;

    @Autowired

    public DMVService(DMVRepository dmvRepository) {
        this.dmvRepository = dmvRepository;
    }

    public ResponseEntity<List<DMV>> getAllDrivingLicense() {
        File file = new File("E:/APIIT/DMV_License/DMV_License_1.csv");
        BufferedReader bufferedReader = null;
        String line = "";
        String split = ",";
        List<DMV> dmvList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                String[] records = line.split(split);
//                System.out.println(records);
                DMV dmv = new DMV();
                dmv.setDrivingLicence(records[0]);
                dmv.setType(records[1]);
                dmv.setOffenceDate(records[2]);
                dmvList.add(dmv);
            }
            System.out.println(dmvList);
            return ResponseEntity.ok().body(dmvList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public DMV validateLicenseNo(String drivingLicence) {
//        File dir = new File("E:/APIIT/DMV_License/");
//        File[] files = dir.listFiles();
//        sortFilesByCreatedDate(files);
//        printFiles(files);
//
//        //read the file
//        List<String> filelist = printFiles(files);
//        String latestUpdateFileName = filelist.get(filelist.size() - 1);
//
//        String csvFile = "E:/APIIT/DMV_License/" + latestUpdateFileName;
//        System.out.println("file is :" + csvFile);
//        BufferedReader bufferedReader = null;
//        String line = "";
//        String split = ",";
//        try {
//            bufferedReader = new BufferedReader(new FileReader(csvFile));
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] records = line.split(split);
//                System.out.println("drivingLicence: " + drivingLicence);
//                System.out.println("records of zeor: " + records[1]);
//                if (drivingLicence.equals(records[1])) {
//                    System.out.println(records[1] + " same " + drivingLicence);
//                    DMV dmv = new DMV();
////                    dmv.setDmvId(Integer.parseInt(records[0]));
//                    dmv.setDrivingLicence(records[1]);
//                    dmv.setType(records[2]);
//                    dmv.setOffenceDate(records[3]);
//                    return dmv;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static void sortFilesByCreatedDate(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long l1 = getFileCreationEpoch(f1);
                long l2 = getFileCreationEpoch(f2);
                return Long.valueOf(l1).compareTo(l2);
            }
        });
    }

    public static long getFileCreationEpoch(File file) {
        try {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(),
                    BasicFileAttributes.class);
            return attr.creationTime()
                    .toInstant().toEpochMilli();
        } catch (IOException e) {
            throw new RuntimeException(file.getAbsolutePath(), e);
        }
    }

    private static List<String> printFiles(File[] files) {
        // Create an empty List
        List<String> list = new ArrayList<>();
        for (File file : files) {
            long m = getFileCreationEpoch(file);
            Instant instant = Instant.ofEpochMilli(m);
            LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            list.add(file.getName());
        }
        return list;
    }
}
