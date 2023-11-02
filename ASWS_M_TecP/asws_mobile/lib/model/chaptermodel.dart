// class Student {
//   int id;
//   String imageData;
//   StudentDetails studentDetails;
//   SiblingInformation siblingInformation;
//   FamilyInformation familyInformation;
//   Center center;
//   Zone zone;

//   Student({
//     required this.id,
//     required this.imageData,
//     required this.studentDetails,
//     required this.siblingInformation,
//     required this.familyInformation,
//     required this.center,
//     required this.zone,
//   });

//   factory Student.fromJson(Map<String, dynamic> json) {
//     return Student(
//       id: json['id'] as int,
//       imageData: json['imageData'] as String,
//       studentDetails: StudentDetails.fromJson(json['student_details']),
//       siblingInformation:
//           SiblingInformation.fromJson(json['sibling_information']),
//       familyInformation:
//           FamilyInformation.fromJson(json['family_information']),
//       center: Center.fromJson(json['center']),
//       zone: Zone.fromJson(json['zone']),
//     );
//   }
// }

// class StudentDetails {
//   String city;
//   String email;
//   int phone;
//   String state;
//   String gender;
//   String address;
//   int pinCode;
//   String lastName;
//   String firstName;
//   String schoolName;
//   int adharNumber;
//   String dateOfBirth;

//   StudentDetails({
//     required this.city,
//     required this.email,
//     required this.phone,
//     required this.state,
//     required this.gender,
//     required this.address,
//     required this.pinCode,
//     required this.lastName,
//     required this.firstName,
//     required this.schoolName,
//     required this.adharNumber,
//     required this.dateOfBirth,
//   });

//   factory StudentDetails.fromJson(Map<String, dynamic> json) {
//     return StudentDetails(
//       city: json['city'] as String,
//       email: json['email'] as String,
//       phone: json['phone'] as int,
//       state: json['state'] as String,
//       gender: json['gender'] as String,
//       address: json['address'] as String,
//       pinCode: json['pinCode'] as int,
//       lastName: json['lastName'] as String,
//       firstName: json['firstName'] as String,
//       schoolName: json['schoolName'] as String,
//       adharNumber: json['adharNumber'] as int,
//       dateOfBirth: json['dateOfBirth'] as String,
//     );
//   }
// }

// class SiblingInformation {
//   int sibAge;
//   String brOrSis;
//   String sibStandard;
//   String siblingSchool;
//   bool siblingStuding;
//   String siblingFullName;

//   SiblingInformation({
//     required this.sibAge,
//     required this.brOrSis,
//     required this.sibStandard,
//     required this.siblingSchool,
//     required this.siblingStuding,
//     required this.siblingFullName,
//   });

//   factory SiblingInformation.fromJson(Map<String, dynamic> json) {
//     return SiblingInformation(
//       sibAge: json['sibAge'] as int,
//       brOrSis: json['brOrSis'] as String,
//       sibStandard: json['sibStandard'] as String,
//       siblingSchool: json['siblingSchool'] as String,
//       siblingStuding: json['siblingStuding'] as bool,
//       siblingFullName: json['siblingFullName'] as String,
//     );
//   }
// }

// class FamilyInformation {
//   String email;
//   int phone;
//   String fatherName;
//   String motherName;
//   String parentOccupation;
//   String educationalQualification;

//   FamilyInformation({
//     required this.email,
//     required this.phone,
//     required this.fatherName,
//     required this.motherName,
//     required this.parentOccupation,
//     required this.educationalQualification,
//   });

//   factory FamilyInformation.fromJson(Map<String, dynamic> json) {
//     return FamilyInformation(
//       email: json['email'] as String,
//       phone: json['phone'] as int,
//       fatherName: json['fatherName'] as String,
//       motherName: json['motherName'] as String,
//       parentOccupation: json['parentOccupation'] as String,
//       educationalQualification: json['educationalQualification'] as String,
//     );
//   }
// }

// class Center {
//   int id;
//   CenterInformation centerInformation;
//   List<String> images;
//   Zone zone;

//   Center({
//     required this.id,
//     required this.centerInformation,
//     required this.images,
//     required this.zone,
//   });

//   factory Center.fromJson(Map<String, dynamic> json) {
//     return Center(
//       id: json['id'] as int,
//       centerInformation:
//           CenterInformation.fromJson(json['center_information']),
//       images: (json['images'] as List<dynamic>).cast<String>(),
//       zone: Zone.fromJson(json['zone']),
//     );
//   }
// }

// class CenterInformation {
//   String email;
//   int phone;
//   String address;
//   String centerName;
//   int waqtBoardNumber;
//   String masjidCommiteMemberName;

//   CenterInformation({
//     required this.email,
//     required this.phone,
//     required this.address,
//     required this.centerName,
//     required this.waqtBoardNumber,
//     required this.masjidCommiteMemberName,
//   });

//   factory CenterInformation.fromJson(Map<String, dynamic> json) {
//     return CenterInformation(
//       email: json['email'] as String,
//       phone: json['phone'] as int,
//       address: json['address'] as String,
//       centerName: json['centerName'] as String,
//       waqtBoardNumber: json['waqtBoardNumber'] as int,
//       masjidCommiteMemberName: json['masjidCommiteMemberName'] as String,
//     );
//   }
// }

// class Zone {
//   int id;
//   String name;

//   Zone({
//     required this.id,
//     required this.name,
//   });

//   factory Zone.fromJson(Map<String, dynamic> json) {
//     return Zone(
//       id: json['id'] as int,
//       name: json['name'] as String,
//     );
//   }
// }

// class Subject {
//   int id;
//   String name;
//   String? description;

//   Subject({
//     required this.id,
//     required this.name,
//     this.description,
//   });

//   factory Subject.fromJson(Map<String, dynamic> json) {
//     return Subject(
//       id: json['id'] as int,
//       name: json['name'] as String,
//       description: json['description'] as String?,
//     );
//   }
// }

import 'package:asws_mobile/model/studentModel/testing.dart';

import 'SubjectModel.dart';

// class Chapter {
//   int id;
//   Welcome student;
//   Subject subject;
//   bool completed;

//   Chapter({
//     required this.id,
//     required this.student,
//     required this.subject,
//     required this.completed,
//   });

//   factory Chapter.fromJson(Map<String, dynamic> json) {
//     return Chapter(
//       id: json['id'] as int,
//       subject: Subject.fromJson(json['subject']),
//       student: Welcome.fromJson(json['student']),
//       completed: json['completed'] as bool,
//     );
//   }
// }

class Chapter {
  int id;
  bool isCompleted;
  String chapterName;

  Chapter({
    required this.id,
    required this.isCompleted,
    required this.chapterName,
  });

  factory Chapter.fromJson(Map<String, dynamic> json) {
    return Chapter(
      isCompleted: json['isCompleted'] as bool, 
      id: json['id'],
      chapterName: json['chapterName']
    );
  }
}
