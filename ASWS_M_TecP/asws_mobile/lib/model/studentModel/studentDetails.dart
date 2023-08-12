class StudentDetails {
  String city;
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
  // String placeOfBirth;

  StudentDetails({
    required this.city,
    required this.email,
    required this.phone,
    required this.state,
    required this.address,
    required this.pinCode,
    required this.lastName,
    required this.firstName,
    required this.schoolName,
    required this.adharNumber,
    required this.dateOfBirth,
    // required this.placeOfBirth,
  });

  factory StudentDetails.fromJson(Map<String, dynamic> json) {
    return StudentDetails(
      city: json['city'],
      email: json['email'],
      phone: json['phone'],
      state: json['state'],
      address: json['address'],
      pinCode: json['pinCode'],
      lastName: json['lastName'],
      firstName: json['firstName'],
      schoolName: json['schoolName'],
      adharNumber: json['adharNumber'],
      dateOfBirth: json['dateOfBirth'],
      // placeOfBirth: json['placeOfBirth'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'city': city,
      'email': email,
      'phone': phone,
      'state': state,
      'address': address,
      'pinCode': pinCode,
      'lastName': lastName,
      'firstName': firstName,
      'schoolName': schoolName,
      'adharNumber': adharNumber,
      'dateOfBirth': dateOfBirth
      // Other properties for sibling information
    };
  }
}
