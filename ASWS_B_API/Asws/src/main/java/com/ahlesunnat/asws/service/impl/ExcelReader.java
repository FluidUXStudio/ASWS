package com.ahlesunnat.asws.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.response.StudentExcelData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExcelReader {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\umran.moosa\\Documents\\Main.xlsx";

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            List<Cell> students = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(1);

            Iterator<Row> rowIterator = sheet.iterator();
            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // StudentExcelData excelData = createStudentExcelDataFromRow(row);
                // Student student = createStudentFromExcelData(excelData);
                System.out.println(row.getCell(6).getAddress());
                students.add(row.getCell(6));
             
            }
            System.out.println(students.size());
            // // Now you have a list of Student objects with data from the Excel file
            // // You can add them to the database or perform any other operation as needed
            // for (Student student : students) {
            //     // Save the student to the database or perform any other operation
            //     System.out.println(student);
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StudentExcelData createStudentExcelDataFromRow(Row row) {
        StudentExcelData excelData = new StudentExcelData();

        // Read data from each cell in the row and set it to the StudentExcelData object
        excelData.setFirstName(row.getCell(1).getStringCellValue());
        excelData.setLastName(row.getCell(2).getStringCellValue());
        excelData.setDateOfBirth(row.getCell(3).getStringCellValue());
        excelData.setPlaceOfBirth(row.getCell(4).getStringCellValue());
        excelData.setSchoolName(row.getCell(5).getStringCellValue());
        excelData.setEmail(row.getCell(6).getStringCellValue());
        excelData.setPhone((long) row.getCell(7).getNumericCellValue());
        excelData.setAddress(row.getCell(8).getStringCellValue());
        excelData.setState(row.getCell(9).getStringCellValue());
        excelData.setPinCode((int) row.getCell(10).getNumericCellValue());
        excelData.setCity(row.getCell(11).getStringCellValue());
        excelData.setAdharNumber((long) row.getCell(12).getNumericCellValue());

        excelData.setSiblingStuding(row.getCell(13).getBooleanCellValue());
        excelData.setBrOrSis(row.getCell(14).getStringCellValue());
        excelData.setSiblingFullName(row.getCell(15).getStringCellValue());
        excelData.setSibAge((int) row.getCell(16).getNumericCellValue());
        excelData.setSibStandard((int)row.getCell(17).getNumericCellValue());
        excelData.setSiblingSchool(row.getCell(18).getStringCellValue());

        excelData.setParentalStatus(row.getCell(19).getStringCellValue());
        excelData.setParentFirstName(row.getCell(20).getStringCellValue());
        excelData.setParentLastName(row.getCell(21).getStringCellValue());
        excelData.setParentEmail(row.getCell(22).getStringCellValue());
        excelData.setParentPhone((long) row.getCell(23).getNumericCellValue());
        excelData.setParentOccupation(row.getCell(24).getStringCellValue());

        return excelData;
    }

    private static Student createStudentFromExcelData(StudentExcelData excelData) {
        Student student = new Student();

        student.setImageData(excelData.getImageData());
        // Set the student_details
        Map<String, Object> studentDetails = new HashMap<>();
        studentDetails.put("firstName", excelData.getFirstName());
        studentDetails.put("lastName", excelData.getLastName());
        studentDetails.put("dateOfBirth", excelData.getDateOfBirth());
        studentDetails.put("placeOfBirth", excelData.getPlaceOfBirth());
        studentDetails.put("schoolName", excelData.getSchoolName());
        studentDetails.put("email", excelData.getEmail());
        studentDetails.put("phone", excelData.getPhone());
        studentDetails.put("address", excelData.getAddress());
        studentDetails.put("state", excelData.getState());
        studentDetails.put("pinCode", excelData.getPinCode());
        studentDetails.put("city", excelData.getCity());
        studentDetails.put("adharNumber", excelData.getAdharNumber());
        student.setStudent_details(studentDetails);

        // Set the sibling_information
        Map<String, Object> siblingInformation = new HashMap<>();
        siblingInformation.put("siblingStuding", excelData.isSiblingStuding());
        siblingInformation.put("brOrSis", excelData.getBrOrSis());
        siblingInformation.put("siblingFullName", excelData.getSiblingFullName());
        siblingInformation.put("sibAge", excelData.getSibAge());
        siblingInformation.put("sibStandard", excelData.getSibStandard());
        siblingInformation.put("siblingSchool", excelData.getSiblingSchool());
        student.setSibling_information(siblingInformation);

        // Set the family_information
        Map<String, Object> familyInformation = new HashMap<>();
        familyInformation.put("parentalStatus", excelData.getParentalStatus());
        familyInformation.put("parentFirstName", excelData.getParentFirstName());
        familyInformation.put("parentLastName", excelData.getParentLastName());
        familyInformation.put("parentEmail", excelData.getParentEmail());
        familyInformation.put("parentPhone", excelData.getParentPhone());
        familyInformation.put("parentOccupation", excelData.getParentOccupation());
        student.setFamily_information(familyInformation);

        // You can also set other fields from StudentExcelData to Student if needed
        // ...

        return student;
    }
}
