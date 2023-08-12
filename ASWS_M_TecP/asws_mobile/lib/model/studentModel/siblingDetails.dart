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

  factory SiblingInformation.fromJson(Map<String, dynamic> json) {
    return SiblingInformation(
      sibAge: json['sibAge'],
      brOrSis: json['brOrSis'],
      sibStandard: json['sibStandard'],
      siblingSchool: json['siblingSchool'],
      siblingStuding: json['siblingStuding'],
      siblingFullName: json['siblingFullName'],
    );
  }


   Map<String, dynamic> toJson() {
    return {
      'sibAge': sibAge,
      'brOrSis': brOrSis,
      'sibStandard': sibStandard,
      'siblingSchool':siblingSchool,
      'siblingStuding':siblingStuding,
      'siblingFullName':siblingFullName
      // Other properties for sibling information
    };
  }
}
