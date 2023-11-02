package com.ahlesunnat.asws.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
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
import com.ahlesunnat.asws.repository.CenterRepository;
import com.ahlesunnat.asws.response.CenterExcelData;
import com.ahlesunnat.asws.service.CenterService;


@Service
public class CenterServiceImpl implements CenterService {
    @Autowired
    private CenterRepository centerRepository;

    @Override
    public Center createCenter(Center center) {
        return centerRepository.save(center);
    }

    @Override
    public List<Center> getAllCentersInZone(Long zoneId) {
        return centerRepository.findByZoneId(zoneId);
    }

    @Override
    public Center getCenterInZoneById(Long zoneId, Long centerId) {
        return centerRepository.findByZoneIdAndId(zoneId, centerId);
    }

    public List<Center> importCentersFromExcel(MultipartFile excelFile) throws IOException {
    List<Center> centers = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // System.out.println(row.getCell(0).getCellType());
            // System.out.println(row.getCell(1).getCellType());
            //  System.out.println(row.getCell(2).getCellType());
            // System.out.println(row.getCell(3).getCellType());
            //  System.out.println(row.getCell(4).getCellType());
            // System.out.println(row.getCell(5).getCellType());
            //  System.out.println(row.getCell(6).getCellType());



            CenterExcelData excelData = createCenterExcelDataFromRow(row);
            Center teacher = createCenterFromExcelData(excelData);

            centers.add(teacher);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return centers;
}

private CenterExcelData createCenterExcelDataFromRow(Row row) {
    CenterExcelData excelData = new CenterExcelData();

    excelData.setCenterName(row.getCell(1).getStringCellValue());
    excelData.setEmail(row.getCell(2).getStringCellValue());
    excelData.setAddress(row.getCell(3).getStringCellValue());
    excelData.setPhone((long) row.getCell(4).getNumericCellValue());
    excelData.setMasjidCommiteMemberName(row.getCell(5).getStringCellValue());
    excelData.setWaqtBoardNumber((int) row.getCell(6).getNumericCellValue());

    return excelData;
}

private Center createCenterFromExcelData(CenterExcelData excelData) {
    Center center = new Center();
    

    // Set the education details
    Map<String, Object> centerDetails = new HashMap<>();
    centerDetails.put("centerName", excelData.getCenterName());
    centerDetails.put("email", excelData.getEmail());
    centerDetails.put("address", excelData.getAddress());
    centerDetails.put("phone", excelData.getPhone());
    centerDetails.put("masjidCommiteMemberName", excelData.getMasjidCommiteMemberName());
    centerDetails.put("waqtBoardNumber", excelData.getWaqtBoardNumber());

    center.setCenter_information(centerDetails);

    return center;
}

    
    // private Center createCenterFromExcelData(CenterExcelData excelData) {
    //     Center center = new Center();

    //     // Set the center_information
    //     Map<String, Object> centerInformation = new HashMap<>();
    //     centerInformation.put("centerName", excelData.getCenterName());
    //     centerInformation.put("email", excelData.getEmail());
    //     centerInformation.put("address", excelData.getAddress());
    //     centerInformation.put("phone", excelData.getPhone());
    //     centerInformation.put("masjidCommiteMemberName", excelData.getMasjidCommiteMemberName());
    //     centerInformation.put("waqtBoardNumber", excelData.getWaqtBoardNumber());
    //     center.setCenter_information(centerInformation);

    //     // You can set other fields of the Center entity here if needed.

    //     return center;
    // }


    public static void main(String[] args) {
        // Column headers
        String[] columns = {"Photo", "Center Name", "Email", "Address", "Phone", "Masjid Committee Member Name", "Waqt Board Number"};

        // Sample data for three records
        byte[] photo1 = new byte[]{/* Insert photo data for record 1 here */};
        String centerName1 = "ABC Center";
        String email1 = "abc@example.com";
        String address1 = "123 Main St";
        String phone1 = "1234567890";
        String masjidCommiteMemberName1 = "John Doe";
        Integer waqtBoardNumber1 = 123;

        byte[] photo2 = new byte[]{/* Insert photo data for record 2 here */};
        String centerName2 = "XYZ Center";
        String email2 = "xyz@example.com";
        String address2 = "456 Elm St";
        String phone2 = "9876543210";
        String masjidCommiteMemberName2 = "Jane Smith";
        Integer waqtBoardNumber2 = 456;

        byte[] photo3 = new byte[]{/* Insert photo data for record 3 here */};
        String centerName3 = "PQR Center";
        String email3 = "pqr@example.com";
        String address3 = "789 Oak St";
        String phone3 = "5555555555";
        String masjidCommiteMemberName3 = "Mike Johnson";
        Integer waqtBoardNumber3 = 789;

        // Create the Excel workbook and sheet
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Center Data");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Data rows
            Object[][] data = {
                    {photo1, centerName1, email1, address1, phone1, masjidCommiteMemberName1, waqtBoardNumber1},
                    {photo2, centerName2, email2, address2, phone2, masjidCommiteMemberName2, waqtBoardNumber2},
                    {photo3, centerName3, email3, address3, phone3, masjidCommiteMemberName3, waqtBoardNumber3}
            };

            int rowNum = 1;
            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof byte[]) {
                        // If it's a byte array (photo), you can convert it to an appropriate representation.
                        // For example, we are just writing "Photo" here.
                        cell.setCellValue("Photo");
                    } else if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    // Add other data types as needed
                }
            }

            // Save the Excel data to a file
            String excelFilePath = "center_data.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
