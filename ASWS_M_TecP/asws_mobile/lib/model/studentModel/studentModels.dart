import 'package:asws_mobile/model/studentModel/centerModel.dart';
import 'package:asws_mobile/model/studentModel/familyInformation.dart';
import 'package:asws_mobile/model/studentModel/siblingDetails.dart';
import 'package:asws_mobile/model/studentModel/studentDetails.dart';
import 'package:asws_mobile/model/studentModel/zoneModel.dart';

class StudentData {
  int id;
  String? imageData;
  StudentDetails studentDetails;
  SiblingInformation siblingInformation;
  FamilyInformation familyInformation;
  Center center;
  Zone zone;

  StudentData({
    required this.id,
    this.imageData,
    required this.studentDetails,
    required this.siblingInformation,
    required this.familyInformation,
    required this.center,
    required this.zone,
  });

  factory StudentData.fromJson(Map<String, dynamic> json) {
    return StudentData(
      id: json['id'],
      imageData: json['imageData'],
      studentDetails: StudentDetails.fromJson(json['student_details']),
      siblingInformation: SiblingInformation.fromJson(json['sibling_information']),
      familyInformation: FamilyInformation.fromJson(json['family_information']),
      center: Center.fromJson(json['center']),
      zone: Zone.fromJson(json['zone']),
    );
  }
}
