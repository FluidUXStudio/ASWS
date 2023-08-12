import 'dart:convert';
import 'package:asws_mobile/model/studentModel/testing.dart';
import 'package:asws_mobile/utils/toast.dart';
import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constant/apiendpoint.dart';
import '../screens/attendance/attendencelist.dart';
import '../utils/loader.dart';

class AttendanceProvider extends ChangeNotifier {
  List<CreateAttendance> allattendancelist = [];
  get getattendancelist => allattendancelist;

  void addAllAttendance(List<Welcome> studentlist) {
    for (int i = 0; i < studentlist.length; i++) {
      allattendancelist.add(CreateAttendance(
        student: Student(id: studentlist[i].id),
        attendanceDate: "${DateFormat('yyyy-MM-dd').format(DateTime.now())}",
        status: "present",
      ));
    }
    notifyListeners();
  }

  void updateattendance(var id, bool check, String leave) {
    check
        ? allattendancelist[allattendancelist.indexWhere(
            (element) => element.student.id == id)] = CreateAttendance(
            student: Student(id: id),
            attendanceDate:
                "${DateFormat('yyyy-MM-dd').format(DateTime.now())}",
            status: "absent",
          )
        : allattendancelist[allattendancelist.indexWhere(
            (element) => element.student.id == id)] = CreateAttendance(
            student: Student(id: id),
            attendanceDate:
                "${DateFormat('yyyy-MM-dd').format(DateTime.now())}",
            status: "absent",
          );
    notifyListeners();

    // allattendancelist.firstWhere((element) => element.id == id) = CreateAttendance(
    //   id: "$i",
    //   name:"${studentlist[i].firstName} ${studentlist[i].lastName}",
    //   date:"${DateFormat('yyyy/MM/dd').format(DateTime.now())}",
    //   leave:"not",
    //   presentabsent:"present",
    // );
  }

  void submitattendance(ctx) async {
    List<Map<String, dynamic>> requestlist = [];

    for (int i = 0; i < allattendancelist.length; i++) {
      requestlist.add({
        "student": {
          "id": allattendancelist[i].student.id,
        },
        "attendanceDate": "${allattendancelist[i].attendanceDate}",
        "status": allattendancelist[i].status.toUpperCase(),
      });
    }

    print(jsonEncode(requestlist));
    GlobalMethods().showLoader(ctx, true);
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl + '/api/students/attendance');
    print(url);
    print(token);

    try {
      var response = await post(url,
          headers: {
            "Accept": "application/json",
            "content-type": "application/json",
            "Authorization": "Bearer $token"
          },
          body: jsonEncode(requestlist)
          // body: requestlist
          );
      GlobalMethods().showLoader(ctx, false);
      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
      }
      debugPrint(result.toString());
      if (response.statusCode == 200) {
        showToast("Attendence  Submitted");
        allattendancelist = [];

        notifyListeners();
        debugPrint("Sucessfully Hit the Api");
        debugPrint(result.toString());
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }
    // for (int i = 0; i < allattendancelist.length; i++) {
    //   print(allattendancelist[i].presentabsent);
    //   print(allattendancelist[i].id);
    //   print(allattendancelist[i].leave);
    // }
  }
}
