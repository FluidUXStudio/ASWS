// package com.Asws.co.helper;



// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;

// import javax.mail.Multipart;

// import org.apache.poi.ss.usermodel.CellBase;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.Sheet;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.springframework.web.multipart.MultipartFile;

// import com.Asws.co.domain.Student;
// import com.google.common.collect.Table.Cell;

// import io.swagger.v3.oas.annotations.media.Content;

// public class helper {
    

//     public boolean checkFileContenetType(MultipartFile file){


//       String contentType = file.getContentType();

//       if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
//         return true;
//       }else{

//         return false;
//       }

//     }


//     public List<Student> excelToStud(InputStream is){


//         List<Student> list = new ArrayList<>();
//         try {


//             XSSFWorkbook workbook = new XSSFWorkbook(is);
//             XSSFSheet sheet = workbook.getSheet("studentData");

//             int rowNumber = 0;
//             Iterator<Row> iterator = sheet.iterator();
//             while(iterator.hasNext()){

//               Row row = iterator.next();
//             if(rowNumber == 0){
//               rowNumber++;
//               continue;
//             }
//             Student sd = new Student();
//             Iterator<org.apache.poi.ss.usermodel.Cell> cells = row.iterator();
//             int cid=0;
//             while(cells.hasNext()){
//               org.apache.poi.ss.usermodel.Cell cell = cells.next();

//               switch(cid){
//                 case 0:
//                   sd.setId(cell.getStringCellValue());

//                 case 1:
//                   sd.setPhoto(()cell.getNumericCellValue());
//               }


//               photo;

//     private String zone;
//     private String CenterName;

//     private String firstName;
//     private String lastName;

//     private String dateOfBirth;
//     private String placeOfBirth;

//     private String SchoolName;

//     @Column(unique=true)
//     private String email;

//     private Long phone;

//     private String address;

//     private String state;

//     private Integer pinCode;

//     private String city;

//     private Long adharNumber;


//     private boolean siblingStuding;

//     private String brOrSis;

//     private String siblingFullName;

//     private Integer sibAge;

//     private String sibStandard;

//     private String siblingSchool;

//     private String parentalStatus;

//     private String parentFirstName;
//     private String parentLastName;


//     private String ParentEmail;

//     private Long parentPhone;

//     private String parentOccupation;

//             }

//           }
            
//         } catch (Exception e) {

//             e.printStackTrace();
//                 }

//                 return list;
//     }
// }
