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

  factory CenterInformation.fromJson(Map<String, dynamic> json) {
    return CenterInformation(
      email: json['email'],
      phone: json['phone'],
      address: json['address'],
      centerName: json['centerName'],
      waqtBoardNumber: json['waqtBoardNumber'],
      masjidCommiteMemberName: json['masjidCommiteMemberName'],
    );
  }
}