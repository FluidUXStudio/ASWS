class FamilyInformation {
  String parentEmail;
  int parentPhone;
  String parentLastName;
  String parentalStatus;
  String parentFirstName;
  String parentEducationalQualification;
  String parentOccupation;

  FamilyInformation(
      {required this.parentEmail,
      required this.parentPhone,
      required this.parentLastName,
      required this.parentalStatus,
      required this.parentFirstName,
      required this.parentOccupation,
      required this.parentEducationalQualification});

  factory FamilyInformation.fromJson(Map<String, dynamic> json) {
    return FamilyInformation(
        parentEmail: json['parentEmail'],
        parentPhone: json['parentPhone'],
        parentLastName: json['parentLastName'],
        parentalStatus: json['parentalStatus'],
        parentFirstName: json['parentFirstName'],
        parentOccupation: json['parentOccupation'],
        parentEducationalQualification: json['educationalQualification']);
  }

  Map<String, dynamic> toJson() {
    return {
      'parentEmail': parentEmail,
      'parentPhone': parentPhone,
      'parentLastName': parentLastName,
      'parentalStatus': parentalStatus,
      'parentFirstName': parentFirstName,
      'parentOccupation': parentOccupation,
      'educationalQualification': parentEducationalQualification

      // Other properties for sibling information
    };
  }
}
