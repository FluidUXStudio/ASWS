// To parse this JSON data, do
//
//     final welcome = welcomeFromJson(jsonString);

import 'dart:convert';

List<Welcome> welcomeFromJson(String str) => List<Welcome>.from(json.decode(str).map((x) => Welcome.fromJson(x)));

String welcomeToJson(List<Welcome> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

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
        id: json["id"],
        imageData: json["imageData"],
        studentDetails: StudentDetails.fromJson(json["student_details"]),
        siblingInformation: SiblingInformation.fromJson(json["sibling_information"]),
        familyInformation: FamilyInformation.fromJson(json["family_information"]),
        center: Center.fromJson(json["center"]),
        zone: Zone.fromJson(json["zone"]),
    );

    Map<String, dynamic> toJson(){
       final Map<String, dynamic> data1 = new Map<String, dynamic>();
    data1['id'] = id;
    data1['imageData'] = imageData;
        data1["student_details"] = studentDetails.toJson();
        data1["sibling_information"]= siblingInformation.toJson();
        data1["family_information"]= familyInformation.toJson();
        data1["center"]= center.toJson();
        data1["zone"]=zone.toJson();

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
        id: json["id"],
        centerInformation: CenterInformation.fromJson(json["center_information"]),
        images: json["images"],
        zone: Zone.fromJson(json["zone"]),
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
        email: json["email"],
        phone: json["phone"],
        address: json["address"],
        centerName: json["centerName"],
        waqtBoardNumber: json["waqtBoardNumber"],
        masjidCommiteMemberName: json["masjidCommiteMemberName"],
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
        id: json["id"],
        name: json["name"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
    };
}

class FamilyInformation {
    String fatherName;
    String motherName;
    int numberOfSiblings;

    FamilyInformation({
        required this.fatherName,
        required this.motherName,
        required this.numberOfSiblings,
    });

    factory FamilyInformation.fromJson(Map<String, dynamic> json) => FamilyInformation(
        fatherName: json["fatherName"],
        motherName: json["motherName"],
        numberOfSiblings: json["numberOfSiblings"],
    );

    Map<String, dynamic> toJson() => {
        "fatherName": fatherName,
        "motherName": motherName,
        "numberOfSiblings": numberOfSiblings,
    };
}

class SiblingInformation {
    String siblingAge;
    String siblingName;
    String siblingOccupation;

    SiblingInformation({
        required this.siblingAge,
        required this.siblingName,
        required this.siblingOccupation,
    });

    factory SiblingInformation.fromJson(Map<String, dynamic> json) => SiblingInformation(
        siblingAge: json["siblingAge"],
        siblingName: json["siblingName"],
        siblingOccupation: json["siblingOccupation"],
    );

    Map<String, dynamic> toJson() => {
        "siblingAge": siblingAge,
        "siblingName": siblingName,
        "siblingOccupation": siblingOccupation,
    };
}

class StudentDetails {
    String city;
    String email;
    String phone;
    String state;
    String address;
    String pinCode;
    String lastName;
    String firstName;
    DateTime dateOfBirth;
    String placeOfBirth;

    StudentDetails({
        required this.city,
        required this.email,
        required this.phone,
        required this.state,
        required this.address,
        required this.pinCode,
        required this.lastName,
        required this.firstName,
        required this.dateOfBirth,
        required this.placeOfBirth,
    });

    factory StudentDetails.fromJson(Map<String, dynamic> json) => StudentDetails(
        city: json["city"],
        email: json["email"],
        phone: json["phone"],
        state: json["state"],
        address: json["address"],
        pinCode: json["pinCode"],
        lastName: json["lastName"],
        firstName: json["firstName"],
        dateOfBirth: DateTime.parse(json["dateOfBirth"]),
        placeOfBirth: json["placeOfBirth"],
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
        "dateOfBirth": "${dateOfBirth.year.toString().padLeft(4, '0')}-${dateOfBirth.month.toString().padLeft(2, '0')}-${dateOfBirth.day.toString().padLeft(2, '0')}",
        "placeOfBirth": placeOfBirth,
    };
}
