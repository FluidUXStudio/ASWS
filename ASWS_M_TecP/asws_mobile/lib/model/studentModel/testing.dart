import 'dart:convert';

List<Welcome> welcomeFromJson(String str) =>
    List<Welcome>.from(json.decode(str).map((x) => Welcome.fromJson(x)));

String welcomeToJson(List<Welcome> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Welcome {
  int id;
  String imageData;
  StudentDetails studentDetails;
  SiblingInformation siblingInformation;
  FamilyInformation familyInformation;
  Center center;
  Zone zone;

  Welcome({
    required this.id,
    required this.imageData,
    required this.studentDetails,
    required this.siblingInformation,
    required this.familyInformation,
    required this.center,
    required this.zone,
  });

  factory Welcome.fromJson(Map<String, dynamic> json) => Welcome(
        id: json["id"] ?? 0,
        imageData: json["imageData"] ?? "",
        studentDetails: StudentDetails.fromJson(json["student_details"] ?? {}),
        siblingInformation:
            SiblingInformation.fromJson(json["sibling_information"] ?? {}),
        familyInformation:
            FamilyInformation.fromJson(json["family_information"] ?? {}),
        center: Center.fromJson(json["center"] ?? {}),
        zone: Zone.fromJson(json["zone"] ?? {}),
      );

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data1 = new Map<String, dynamic>();
    data1['id'] = id;
    data1['imageData'] = imageData;
    data1["student_details"] = studentDetails.toJson();
    data1["sibling_information"] = siblingInformation.toJson();
    data1["family_information"] = familyInformation.toJson();
    data1["center"] = center.toJson();
    data1["zone"] = zone.toJson();

    return data1;
  }
}

class Center {
  int id;
  CenterInformation centerInformation;
  dynamic images;
  Zone zone;

  Center({
    required this.id,
    required this.centerInformation,
    this.images,
    required this.zone,
  });

  factory Center.fromJson(Map<String, dynamic> json) => Center(
        id: json["id"] ?? 0,
        centerInformation:
            CenterInformation.fromJson(json["center_information"] ?? {}),
        images: json["images"] ?? null,
        zone: Zone.fromJson(json["zone"] ?? {}),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "center_information": centerInformation.toJson(),
        "images": images,
        "zone": zone.toJson(),
      };
}

class CenterInformation {
  String email;
  int phone;
  String address;
  String centerName;
  int waqtBoardNumber;
  String masjidCommiteMemberName;

  CenterInformation({
    required this.email,
    required this.phone,
    required this.address,
    required this.centerName,
    required this.waqtBoardNumber,
    required this.masjidCommiteMemberName,
  });

  factory CenterInformation.fromJson(Map<String, dynamic> json) => CenterInformation(
        email: json["email"] ?? "",
        phone: json["phone"] ?? 0,
        address: json["address"] ?? "",
        centerName: json["centerName"] ?? "",
        waqtBoardNumber: json["waqtBoardNumber"] ?? 0,
        masjidCommiteMemberName: json["masjidCommiteMemberName"] ?? "",
      );

  Map<String, dynamic> toJson() => {
        "email": email,
        "phone": phone,
        "address": address,
        "centerName": centerName,
        "waqtBoardNumber": waqtBoardNumber,
        "masjidCommiteMemberName": masjidCommiteMemberName,
      };
}

class Zone {
  int id;
  String name;

  Zone({
    required this.id,
    required this.name,
  });

  factory Zone.fromJson(Map<String, dynamic> json) => Zone(
        id: json["id"] ?? 0,
        name: json["name"] ?? "",
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
      };
}

class FamilyInformation {
  String fatherName;
  String motherName;
  int phone;
  String email;
  String educationalQualification;
  String parentOccupation;

  FamilyInformation({
    required this.fatherName,
    required this.motherName,
    required this.phone,
    required this.email,
    required this.educationalQualification,
    required this.parentOccupation,
  });

  factory FamilyInformation.fromJson(Map<String, dynamic> json) => FamilyInformation(
        fatherName: json["fatherName"] ?? "",
        motherName: json["motherName"] ?? "",
        phone: json["phone"] ?? 0,
        email: json["email"] ?? "",
        educationalQualification: json["educationalQualification"] ?? "",
        parentOccupation: json["parentOccupation"] ?? "",
      );

  Map<String, dynamic> toJson() => {
        "fatherName": fatherName,
        "motherName": motherName,
        "phone": phone,
        "email": email,
        "educationalQualification": educationalQualification,
        "parentOccupation": parentOccupation,
      };
}

class SiblingInformation {
  int sibAge;
  String brOrSis;
  String sibStandard;
  String siblingSchool;
  bool siblingStuding;
  String siblingFullName;

  SiblingInformation({
    required this.sibAge,
    required this.brOrSis,
    required this.sibStandard,
    required this.siblingSchool,
    required this.siblingStuding,
    required this.siblingFullName,
  });

  factory SiblingInformation.fromJson(Map<String, dynamic> json) => SiblingInformation(
        sibAge: json["sibAge"] ?? 0,
        brOrSis: json["brOrSis"] ?? "",
        sibStandard: json["sibStandard"] ?? "",
        siblingSchool: json["siblingSchool"] ?? "",
        siblingStuding: json["siblingStuding"] ?? false,
        siblingFullName: json["siblingFullName"] ?? "",
      );

  Map<String, dynamic> toJson() => {
        "sibAge": sibAge,
        "brOrSis": brOrSis,
        "sibStandard": sibStandard,
        "siblingSchool": siblingSchool,
        "siblingStuding": siblingStuding,
        "siblingFullName": siblingFullName,
      };
}

class StudentDetails {
  String city;
  String gender;
  String email;
  int phone;
  String state;
  String address;
  int pinCode;
  String lastName;
  String firstName;
  String schoolName;
  int adharNumber;
  String dateOfBirth;
  
  StudentDetails({
    required this.city,
    required this.email,
    required this.phone,
    required this.gender,
    required this.state,
    required this.schoolName,
    required this.address,
    required this.pinCode,
    required this.lastName,
    required this.firstName,
    required this.dateOfBirth,
    required this.adharNumber,
  });

  factory StudentDetails.fromJson(Map<String, dynamic> json) => StudentDetails(
        city: json["city"] ?? "",
        email: json["email"] ?? "",
        phone: json["phone"] ?? 0,
        state: json["state"] ?? "",
        address: json["address"] ?? "",
        pinCode: json["pinCode"] ?? 0,
        lastName: json["lastName"] ?? "",
        firstName: json["firstName"] ?? "",
        dateOfBirth: json["dateOfBirth"] ?? "",
        schoolName: json["schoolName"] ?? "",
        gender: json["gender"] ?? "",
        adharNumber: json["adharNumber"] ?? 0,
      );

  Map<String, dynamic> toJson() => {
        "city": city,
        "email": email,
        "phone": phone,
        "state": state,
        "address": address,
        "pinCode": pinCode,
        "lastName": lastName,
        "firstName": firstName,
        "dateOfBirth": dateOfBirth,
        "adharNumber": adharNumber,
        "schoolName": schoolName,
        "gender": gender,
      };
}
