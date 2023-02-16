package com.Asws.co.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Student;
import com.Asws.co.repository.StudentRepository;
import com.Asws.co.response.StudentResponse;
import com.Asws.co.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student obj) {

        Student sd = studentRepository.save(obj);
        
        return sd;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentResponse searchServiceDetailWithFilter(Map<String, String> obj ) {
		
	    StudentResponse resp = StudentResponse.builder().build();

		List<Student> list = studentRepository.findAll();

		if (obj.size() == 0) {
            resp.setStudents(list);;
			resp.setTotal(list.size());
			return resp;
		}

		List<Student> list2 = list;
        if (obj.containsKey("id")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getId()).equalsIgnoreCase(obj.get("id")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("photo")) {
            list2 = list2.stream().filter(sd -> ((byte[]) sd.getPhoto()).equals(obj.get("photo")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("zone")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getZone()).equalsIgnoreCase(obj.get("zone")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("firstName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getFirstName()).equalsIgnoreCase(obj.get("firstName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("lastName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getLastName()).equalsIgnoreCase(obj.get("lastName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("dateOfBirth")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getDateOfBirth()).equalsIgnoreCase(obj.get("dateOfBirth")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("placeOfBirth")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getPlaceOfBirth()).equalsIgnoreCase(obj.get("placeOfBirth")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("email")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getEmail()).equalsIgnoreCase(obj.get("email")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("phone")) {
            list2 = list2.stream().filter(sd -> ((Long) sd.getPhone()).equals(obj.get("phone")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("address")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getAddress()).equalsIgnoreCase(obj.get("address")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("state")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getState()).equalsIgnoreCase(obj.get("state")))
                .collect(Collectors.toList());
        }

        if (obj.containsKey("pinCode")) {
            list2 = list2.stream().filter(sd -> ((Integer) sd.getPinCode()).equals(obj.get("pinCode")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("city")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getCity()).equalsIgnoreCase(obj.get("city")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("adharNumber")) {
            list2 = list2.stream().filter(sd -> ((Long) sd.getAdharNumber()).equals(obj.get("adharNumber")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("siblingStuding")) {
            list2 = list2.stream().filter(sd -> ((Boolean) sd.isSiblingStuding()).equals(obj.get("siblingStuding")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("brOrSis")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getBrOrSis()).equalsIgnoreCase(obj.get("brOrSis")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("siblingFullName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getSiblingFullName()).equalsIgnoreCase(obj.get("siblingFullName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("sibAge")) {
            list2 = list2.stream().filter(sd -> ((Integer) sd.getSibAge()).equals(obj.get("sibAge")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("sibStandard")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getSibStandard()).equalsIgnoreCase(obj.get("sibStandard")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("siblingSchool")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getSchoolName()).equalsIgnoreCase(obj.get("siblingSchool")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentalStatus")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getParentalStatus()).equalsIgnoreCase(obj.get("parentalStatus")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentFirstName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getParentFirstName()).equalsIgnoreCase(obj.get("parentFirstName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentLastName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getParentLastName()).equalsIgnoreCase(obj.get("parentLastName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentPhone")) {
            list2 = list2.stream().filter(sd -> ((Long) sd.getParentPhone()).equals(obj.get("parentPhone")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentOccupation")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getParentOccupation()).equalsIgnoreCase(obj.get("parentOccupation")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("schoolName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getSchoolName()).equalsIgnoreCase(obj.get("schoolName")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("parentEmail")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getParentEmail()).equalsIgnoreCase(obj.get("parentEmail")))
                .collect(Collectors.toList());
        }
        if (obj.containsKey("centerName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getCenterName()).equalsIgnoreCase(obj.get("centerName")))
                .collect(Collectors.toList());
        }
		
		resp.setStudents(list2);
		resp.setTotal(list2.size());
		return resp;
	}
    
 

    // public static String readImage(String imge) throws org.json.simple.parser.ParseException{

    //     JSONParser parser = new JSONParser();
	// 	JSONObject jsonObject = null;
	// 	try {
	// 		Object obj = parser.parse(new FileReader(imge));
    //         jsonObject =  (JSONObject) obj;
	// 	}catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
		
	// 	return (String)jsonObject.toString();
    
    // }

    // private void generateHTMLFromPDF(String filename) {
    //     PDDocument pdf = PDDocument.load(new File(filename));
    //     Writer output = new PrintWriter("src/output/pdf.html", "utf-8");
    //     new PDFDomTree().writeText(pdf, output);
        
    //     output.close();
    // // }

    // public static void main(String[] args) throws org.json.simple.parser.ParseException, IOException {
    // //   String s1 =  readImage("/home/umran/Downloads/Aakash NBTS - 04 Paper ( 2022 ) _.PDF.pdf");








    // }
}
    

