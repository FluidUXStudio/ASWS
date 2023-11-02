package com.ahlesunnat.asws.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.ahlesunnat.asws.domain.Chapter;
import com.ahlesunnat.asws.domain.Student;
import com.ahlesunnat.asws.domain.StudentChapterCompletion;
import com.ahlesunnat.asws.domain.Teacher;
import com.ahlesunnat.asws.repository.ChapterRepository;
import com.ahlesunnat.asws.repository.StudentChapterComRepository;
import com.ahlesunnat.asws.repository.StudentRepository;
import com.ahlesunnat.asws.response.StudentExcelData;
import com.ahlesunnat.asws.response.StudentResponse;
import com.ahlesunnat.asws.service.StudentService;

import io.jsonwebtoken.io.IOException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentChapterComRepository studentChapterCompletionRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private GoogleDriveImageDownloader imageDownloader;

    @Override
    public Student createStudent(Student student) {

        Student createdStudent = studentRepository.save(student);

        List<Chapter> chapters = chapterRepository.findAll();
        for (Chapter chapter : chapters) {
            StudentChapterCompletion studentChapterCompletion = new StudentChapterCompletion();
            studentChapterCompletion.setChapter(chapter);
            studentChapterCompletion.setStudent(createdStudent);
            studentChapterCompletion.setCompleted(false);
            studentChapterCompletion.setSubject(chapter.getSubject());
            studentChapterCompletionRepository.save(studentChapterCompletion);
        }
        return createdStudent;
    }

    @Override
    public List<StudentResponse> getAllStudentsInCenter(Long zoneId, Long centerId) {
        List<StudentResponse> studentList = new ArrayList<>();
        List<Student> ls = studentRepository.findAllByZoneIdAndCenterId(zoneId, centerId);
        for (Student s : ls) {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(s.getId());
            studentResponse.setImageData(s.getImageData());
            studentResponse.setStudent_details(s.getStudent_details());
            studentResponse.setFamily_information(s.getFamily_information());
            studentResponse.setSibling_information(s.getSibling_information());
            studentList.add(studentResponse);
        }

        return studentList;
    }

    public List<StudentResponse> getAllStudentsInCenterAndTeacher(Long zoneId, Long centerId, Long teacherId) {

        List<StudentResponse> studentList = new ArrayList<>();
        List<Student> ls = studentRepository.findAllByZoneIdAndCenterIdAndTeacherId(zoneId, centerId, teacherId);
        for (Student s : ls) {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(s.getId());
            studentResponse.setImageData(s.getImageData());
            studentResponse.setStudent_details(s.getStudent_details());
            studentResponse.setFamily_information(s.getFamily_information());
            studentResponse.setSibling_information(s.getSibling_information());
            studentList.add(studentResponse);
        }

        return studentList;
    }

    @Override
    public Student getStudentInCenterById(Long zoneId, Long centerId, Long studentId) {
        return studentRepository.findByZoneIdAndCenterIdAndId(zoneId, centerId, studentId);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    // @Override
    // @Transactional
    // public void deleteStudent(Long zoneId, Long centerId, Long studentId) {
    // studentRepository.deleteByZoneIdAndCenterIdAndId(zoneId, centerId,
    // studentId);
    // }

    public void deleteStudents(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> importStudentsFromExcel(MultipartFile excelFile) throws java.io.IOException {
        List<Student> students = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                StudentExcelData excelData = createStudentExcelDataFromRow(row);
                Student student = createStudentFromExcelData(excelData);

                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    private Date getDateOfBirthFromCell(Cell cell) {
        DataFormatter dataFormatter = new DataFormatter();
        String dateOfBirthString = dataFormatter.formatCellValue(cell); // Format the cell value

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.parse(dateOfBirthString);
        } catch (ParseException e) {
            // Handle parsing exceptions or return null if needed
            e.printStackTrace();
            return null;
        }
    }

    private StudentExcelData createStudentExcelDataFromRow(Row row) {
        StudentExcelData excelData = new StudentExcelData();
        // Date dateOfBirth = getDateOfBirthFromCell(row.getCell(4));
        // System.out.println(row.getCell(23));

        // Boolean siBoolean = false;
        // if(row.getCell(14).getStringCellValue() == "Yes"){
        // siBoolean = true;
        // }else
        // {
        // siBoolean = false;
        // }
        // Read data from each cell in the row and set it to the StudentExcelData object

        // if
        // (imageDownloader.downloadImageBytesFromGoogleDrive(row.getCell(1).getStringCellValue())
        // != null) {

        // byte[] imageBytes =
        // imageDownloader.downloadImageBytesFromGoogleDrive(row.getCell(1).getStringCellValue());

        // excelData.setImageData(imageBytes);
        // } else {
        // excelData.setImageData(new byte[0]); // Set to an empty byte array

        // }

        // Check if the cell exists before accessing its value
        Cell cell2 = row.getCell(1);
        System.out.println(cell2 + "firstName");
        if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
            // The cell is empty or blank
            // Handle it accordingly (e.g., set a default value)
            excelData.setFirstName("Default Value"); // Replace with your default value
        } else {
            excelData.setFirstName(cell2.getStringCellValue());
            System.out.println(cell2.getStringCellValue());
        }

        Cell cell3 = row.getCell(2);
        if (cell3 == null || cell3.getCellType() == CellType.BLANK) {
            // The cell is empty or blank
            // Handle it accordingly (e.g., set a default value)
            excelData.setParentFirstName("Default Value"); // Replace with your default value
        } else {
            excelData.setLastName(cell3.getStringCellValue());
            System.out.println(cell3.getStringCellValue());
        }

        Cell cell4 = row.getCell(3);
        System.out.println(cell4);
        if (cell4 == null || cell4.getCellType() == CellType.BLANK || cell4.getCellType() == CellType.STRING) {
            // The cell is empty or blank
            // Handle it accordingly (e.g., set a default value)
            excelData.setParentPhone(0L); // Replace with your default value
        } else {
            excelData.setParentPhone((long) cell4.getNumericCellValue());
            System.out.println(cell4.getNumericCellValue());
        }

        Cell cell6 = row.getCell(5);
        if (cell6 == null || cell6.getCellType() == CellType.BLANK || cell6.getCellType() == CellType.STRING) {
            // The cell is empty or blank
            // Handle it accordingly (e.g., set a default value)
            excelData.setAdharNumber(0L); // Replace with your default value
        } else {
            excelData.setAdharNumber((long) cell6.getNumericCellValue());
            System.out.println(cell6.getNumericCellValue());
        }

        Cell cell7 = row.getCell(6);
        if (cell7 == null || cell7.getCellType() == CellType.BLANK) {
            System.out.println(cell7 + " :not present");
            // The cell is empty or blank
            // // Handle it accordingly (e.g., set a default value)
            // excelData.setAdharNumber(0L); // Replace with your default value
        } else {
            excelData.setTeacherId((long) cell7.getNumericCellValue());
            System.out.println(cell7.getNumericCellValue() + ":teacher Id");
        }

        // Repeat the same pattern for other cells

        // excelData.setFirstName(row.getCell(2).getStringCellValue());
        // excelData.setLastName(row.getCell(3).getStringCellValue());
        // excelData.setParentPhone((long) row.getCell(4).getNumericCellValue());
        // excelData.setAdharNumber((long) row.getCell(6).getNumericCellValue());

        // excelData.setSchoolName(row.getCell(4).getStringCellValue());
        // excelData.setPlaceOfBirth(row.getCell(5).getStringCellValue());
        // excelData.setDateOfBirth(dateOfBirth.toString());
        // excelData.setEmail(row.getCell(7).getStringCellValue());
        // excelData.setPhone((long) row.getCell(8).getNumericCellValue());
        // excelData.setAddress(row.getCell(9).getStringCellValue());
        // excelData.setState(row.getCell(10).getStringCellValue());
        // excelData.setPinCode((int) row.getCell(11).getNumericCellValue());
        // excelData.setCity(row.getCell(12).getStringCellValue());

        // excelData.setSiblingStuding(siBoolean);
        // System.out.println(row.getCell(18).getCellType());
        // excelData.setBrOrSis(row.getCell(15).getStringCellValue());
        // excelData.setSiblingFullName(row.getCell(16).getStringCellValue());
        // excelData.setSibAge((int) row.getCell(17).getNumericCellValue());
        // excelData.setSibStandard((int)row.getCell(18).getNumericCellValue());
        // excelData.setSiblingSchool(row.getCell(19).getStringCellValue());

        // excelData.setParentalStatus(row.getCell(20).getStringCellValue());
        // excelData.setParentFirstName(row.getCell(21).getStringCellValue());
        // excelData.setParentLastName(row.getCell(22).getStringCellValue());
        // excelData.setParentEmail(row.getCell(22).getStringCellValue());
        // excelData.setParentOccupation(row.getCell(24).getStringCellValue());

        return excelData;
    }

    // private StudentExcelData createStudentExcelDataFromRow(Row row) {
    // StudentExcelData excelData = new StudentExcelData();
    // Date dateOfBirth = getDateOfBirthFromCell(row.getCell(4));
    // System.out.println(row.getCell(23));

    // // Iterate through all cells in the row and set values accordingly
    // for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
    // Cell cell = row.getCell(cellIndex);
    // if (cell != null) {
    // switch (cellIndex) {
    // case 2:
    // excelData.setFirstName(getStringValueFromCell(cell));
    // break;
    // case 3:
    // excelData.setLastName(getStringValueFromCell(cell));
    // break;
    // case 4:
    // excelData.setDateOfBirth(dateOfBirth != null ? dateOfBirth.toString() :
    // null);
    // break;
    // case 5:
    // excelData.setPlaceOfBirth(getStringValueFromCell(cell));
    // break;
    // case 6:
    // excelData.setSchoolName(getStringValueFromCell(cell));
    // break;
    // case 9:
    // excelData.setAddress(getStringValueFromCell(cell));
    // break;
    // case 10:
    // excelData.setState(getStringValueFromCell(cell));
    // break;
    // case 11:
    // excelData.setPinCode(getNumericValueFromCell(cell));
    // break;
    // case 12:
    // excelData.setCity(getStringValueFromCell(cell));
    // break;
    // case 14:
    // Boolean siBoolean = false;
    // String siValue = getStringValueFromCell(cell);
    // if (siValue != null && siValue.equalsIgnoreCase("Yes")) {
    // siBoolean = true;
    // }
    // excelData.setSiblingStuding(siBoolean);
    // break;
    // // Add cases for other cells as needed
    // }
    // }
    // }

    // return excelData;
    // }

    // Utility methods to handle null values
    private String getStringValueFromCell(Cell cell) {
        return cell != null ? cell.getStringCellValue() : null;
    }

    private Integer getNumericValueFromCell(Cell cell) {
        return cell != null ? (int) cell.getNumericCellValue() : null;
    }

    private Student createStudentFromExcelData(StudentExcelData excelData) {
        Student student = new Student();

        // Set the student_details
        student.setImageData(excelData.getImageData());
        Map<String, Object> studentDetails = new HashMap<>();
        studentDetails.put("firstName", excelData.getFirstName());
        studentDetails.put("addmissionDate", LocalDate.now().toString());
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

        student.setTeacher(new Teacher(excelData.getTeacherId()));

        return student;
    }

    List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // public static void main(String[] args) throws FileNotFoundException,
    // java.io.IOException {

    // String excelFilePath = "student_data.xlsx"; // Specify the file path to save
    // the Excel file

    // try (Workbook workbook = new XSSFWorkbook()) {
    // Sheet sheet = workbook.createSheet("Student Data");

    // // Column headers
    // String[] columns = { "Photo", "First Name", "Last Name", "Date of Birth",
    // "Place of Birth", "School Name",
    // "Email", "Phone", "Address", "State", "Pin Code", "City", "Adhar Number",
    // "Sibling Studying",
    // "Brother/Sister", "Sibling Full Name", "Sibling Age", "Sibling Standard",
    // "Sibling School",
    // "Parental Status", "Parent First Name", "Parent Last Name", "Parent Email",
    // "Parent Phone",
    // "Parent Occupation" };

    // // Create the header row
    // Row headerRow = sheet.createRow(0);
    // for (int i = 0; i < columns.length; i++) {
    // Cell cell = headerRow.createCell(i);
    // cell.setCellValue(columns[i]);
    // }

    // // Data to be added to the Excel file
    // Object[][] data = {
    // { "[photo]", "John", "Doe", "1995-05-15", "New York", "ABC School",
    // "john.doe@example.com",
    // 1234567890L, "123 Main St", "NY", 12345, "New York", 123456789012L, true,
    // "Brother",
    // "Jane Doe", 18, "12th Grade", "XYZ School", "Living with", "Mike", "Doe",
    // "mike.doe@example.com", 9876543210L, "Engineer" },
    // { "[photo]", "Jane", "Smith", "1998-09-20", "Los Angeles", "XYZ School",
    // "jane.smith@example.com",
    // 9876543210L, "456 Elm St", "CA", 56789, "LA", 987654321098L, false, "Sister",
    // "John Smith", 21, "College", "ABC College", "Living with", "Emily", "Smith",
    // "emily.smith@example.com", 1234567890L, "Teacher" }
    // // Add more rows as needed
    // };

    // int rowNum = 1; // Start from row 1 since row 0 is for the header
    // for (Object[] rowData : data) {
    // Row row = sheet.createRow(rowNum++);

    // int colNum = 0;
    // for (Object field : rowData) {
    // Cell cell = row.createCell(colNum++);

    // if (field instanceof String) {
    // cell.setCellValue((String) field);
    // } else if (field instanceof Boolean) {
    // cell.setCellValue((Boolean) field);
    // } else if (field instanceof Integer) {
    // cell.setCellValue((Integer) field);
    // } else if (field instanceof Long) {
    // cell.setCellValue((Long) field);
    // }
    // // Add other data types as needed
    // }
    // }

    // // Write the Excel data to a file
    // try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
    // workbook.write(outputStream);
    // }

    // System.out.println("Excel file created successfully!");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

}
