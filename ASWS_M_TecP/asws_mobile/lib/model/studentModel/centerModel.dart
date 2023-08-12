
import 'package:asws_mobile/model/studentModel/centerInformation.dart';

class Center {
  int id;
  CenterInformation centerInformation;
  dynamic images; // You can define an appropriate data type for images, such as List<String> or Map<String, String>

  Center({
    required this.id,
    required this.centerInformation,
    this.images,
  });

  factory Center.fromJson(Map<String, dynamic> json) {
    return Center(
      id: json['id'],
      centerInformation: CenterInformation.fromJson(json['center_information']),
      images: json['images'],
    );
  }
}
