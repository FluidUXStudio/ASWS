

import 'dart:convert';

import 'package:asws_mobile/utils/loader.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constant/apiendpoint.dart';
import '../model/studentModel/testing.dart';
import '../utils/toast.dart';

class GetStudentPerformanceProvider extends ChangeNotifier{
  List<Student>_studentPerformancelist=[];
  bool _isload =false;
  get studentperfoemancelist=>_studentPerformancelist;
  get isload=>_isload;
  void getstudentsPerformancelist(ctx,List<Welcome> students)async{
    debugPrint("This is student list");
    _isload=true;

    // GlobalMethods().showLoader(ctx, true);
      final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    // GlobalMethods().showLoader(ctx, true);
    debugPrint("This Is token==$token");
      var result;
      final url = Uri.parse(ApiEndPoints.baseurl+'/api/studentPerformance/calculate');
      print(url);
      print(token);


      try{

        var response= await post(url,
            headers: {
            "Accept": "application/json",
            "content-type": "application/json",
          "Authorization":"Bearer $token"
            },
           body: jsonEncode(students)
           );
        _isload=false;
        GlobalMethods().showLoader(ctx, false);
        if(response.body.isNotEmpty) {
          result=   json.decode(response.body);
        }
        debugPrint(result.toString());
        if(response.statusCode==200){
          List<dynamic>? body = jsonDecode(response.body);
          _studentPerformancelist =
              body?.map((dynamic item) => Student.fromJson(item)).toList() ?? [];

          notifyListeners();
          debugPrint("Sucessfully Hit the Api");
          debugPrint(result.toString());
        }else{
          showToast("Something went wrong");
          debugPrint("Something went wrong");

        }

      }catch(error){
        debugPrint(error.toString());
      }


    }

  }
class Student {
  final StudentName studentName;
  final double performance;

  Student({
    required this.studentName,
    required this.performance,
  });

  factory Student.fromJson(Map<String, dynamic> json) {
    return Student(
      studentName: StudentName.fromJson(json['studentName']),
      performance: json['performance']?.toDouble() ?? 0.0,
    );
  }
}

class StudentName {
  final String city;
  final String email;
  final int phone;
  final String state;
  final String gender;
  final String address;
  final int pinCode;
  final String lastName;
  final String firstName;
  final String schoolName;
  final int adharNumber;
  final String dateOfBirth;

  StudentName({
    required this.city,
    required this.email,
    required this.phone,
    required this.state,
    required this.gender,
    required this.address,
    required this.pinCode,
    required this.lastName,
    required this.firstName,
    required this.schoolName,
    required this.adharNumber,
    required this.dateOfBirth,
  });

  factory StudentName.fromJson(Map<String, dynamic> json) {
    return StudentName(
      city: json['city'] ?? '',
      email: json['email'] ?? '',
      phone: json['phone'] as int? ?? 0,
      state: json['state'] ?? '',
      gender: json['gender'] ?? '',
      address: json['address'] ?? '',
      pinCode: json['pinCode'] as int? ?? 0,
      lastName: json['lastName'] ?? '',
      firstName: json['firstName'] ?? '',
      schoolName: json['schoolName'] ?? '',
      adharNumber: json['adharNumber'] as int? ?? 0,
      dateOfBirth: json['dateOfBirth'] ?? '',
    );
  }
}
