package com.ahlesunnat.asws.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ahlesunnat.asws.domain.Center;
import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.domain.Zone;
import com.ahlesunnat.asws.repository.TeacherRepository;
import com.ahlesunnat.asws.response.TeacherExcelData;
import com.ahlesunnat.asws.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Teacher createTeacher(String teacher, Long zoneId, Long centerId, MultipartFile file) {
        try {
            // Generate a thumbnail from the provided image data

            // Create a new Teacher entity with the thumbnail image
            Teacher d1 = mapper.readValue(teacher, Teacher.class);
            InputStream imageInputStream = new ByteArrayInputStream(file.getBytes());
            byte[] thumbnail = imageService.generateThumbnail(imageInputStream, 100,
                    100);

            d1.setImageData(thumbnail);
            d1.setZone(new Zone(zoneId));
            d1.setCenter(new Center(centerId));

            // Save the newTeacher entity to the repository
            return teacherRepository.save(d1);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., by throwing a custom exception or
            // logging an error.
            return null; // Or some other error handling strategy
        }
    }

    @Override
    public List<Teacher> getAllTeachersInCenter(Long zoneId, Long centerId) {
        return teacherRepository.findByZoneIdAndCenterId(zoneId, centerId);
    }

    @Override
    public Teacher getTeacherInCenterById(Long zoneId, Long centerId, Long teacherId) {
        return teacherRepository.findByZoneIdAndCenterIdAndId(zoneId, centerId, teacherId);
    }

    @Override
    public Teacher updateTeacher(Teacher updatedTeacher) {
        return teacherRepository.save(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long zoneId, Long centerId, Long teacherId) {
        teacherRepository.deleteByZoneIdAndCenterIdAndId(zoneId, centerId, teacherId);
    }

    public List<Teacher> importTeachersFromExcel(MultipartFile excelFile) throws IOException {
        List<Teacher> teachers = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                TeacherExcelData excelData = createTeacherExcelDataFromRow(row);
                Teacher teacher = createTeacherFromExcelData(excelData);

                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    private TeacherExcelData createTeacherExcelDataFromRow(Row row) {
        TeacherExcelData excelData = new TeacherExcelData();

        excelData.setFirstName(row.getCell(1).getStringCellValue());
        excelData.setLastName(row.getCell(2).getStringCellValue());
        excelData.setEmail(row.getCell(3).getStringCellValue());
        excelData.setPhone((long) row.getCell(4).getNumericCellValue());
        excelData.setAddress(row.getCell(5).getStringCellValue());
        excelData.setDateOfBirth(row.getCell(6).getStringCellValue());
        excelData.setPlaceOfBirth(row.getCell(7).getStringCellValue());
        excelData.setAdharNumber((long) row.getCell(8).getNumericCellValue());
        excelData.setUniversity(row.getCell(9).getStringCellValue());
        excelData.setDegree(row.getCell(10).getStringCellValue());
        excelData.setStartDate(row.getCell(11).getStringCellValue());
        excelData.setEndDate(row.getCell(12).getStringCellValue());
        excelData.setCity(row.getCell(13).getStringCellValue());

        return excelData;
    }

    private Teacher createTeacherFromExcelData(TeacherExcelData excelData) {
        Teacher teacher = new Teacher();

        // Set the teacher details
        Map<String, Object> teacherDetails = new HashMap<>();
        teacherDetails.put("firstName", excelData.getFirstName());
        teacherDetails.put("lastName", excelData.getLastName());
        teacherDetails.put("dateOfBirth", excelData.getDateOfBirth());
        teacherDetails.put("placeOfBirth", excelData.getPlaceOfBirth());
        teacherDetails.put("email", excelData.getEmail());
        teacherDetails.put("phone", excelData.getPhone());
        teacherDetails.put("address", excelData.getAddress());
        teacherDetails.put("adharNumber", excelData.getAdharNumber());
        teacher.setTeacher_details(teacherDetails);

        // Set the education details
        Map<String, Object> educationDetails = new HashMap<>();
        educationDetails.put("university", excelData.getUniversity());
        educationDetails.put("degree", excelData.getDegree());
        educationDetails.put("startDate", excelData.getStartDate());
        educationDetails.put("endDate", excelData.getEndDate());
        educationDetails.put("city", excelData.getCity());
        teacher.setEducation_details(educationDetails);

        return teacher;
    }

    public static void main(String[] args) {

        String excelFilePath = "teacher_data.xlsx"; // Specify the file path to save the Excel file

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("teacher Data");

            // Column headers
            String[] columns = { "Photo", "First Name", "Last Name", "Email", "Phone", "Address", "Date of Birth",
                    "Place of Birth",
                    "Adhar Number", "University", "Degree", "Start Date", "End Date", "City" };

            // Create the header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Data to be added to the Excel file
            Object[][] data = {
                    { "[photo]", "John", "Doe", "john.doe@example.com", 1234567890L, "123 Main St", "1995-05-15",
                            "New York",
                            123456789012L, "ABC University", "Bachelor of Science", "2022-01-01", "2025-12-31",
                            "New York" },
                    { "[photo]", "Jane", "Smith", "jane.smith@example.com", 9876543210L, "456 Elm St", "1998-09-20",
                            "Los Angeles",
                            987654321098L, "XYZ University", "Master of Arts", "2023-03-15", "2027-02-28",
                            "Los Angeles" },
                    // Add more rows as needed
            };

            int rowNum = 1; // Start from row 1 since row 0 is for the header
            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowNum++);

                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);

                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Boolean) {
                        cell.setCellValue((Boolean) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    } else if (field instanceof Long) {
                        cell.setCellValue((Long) field);
                    }
                    // Add other data types as needed
                }
            }

            // Write the Excel data to a file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
