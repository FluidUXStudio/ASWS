import 'dart:convert';

import 'package:asws_mobile/utils/textutils.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

import '../../constant/apiendpoint.dart';
import '../../utils/buttonutils.dart';
import '../../utils/toast.dart';

class AttendanceAnalytics extends StatefulWidget {
  AttendanceAnalytics({Key? key}) : super(key: key);

  @override
  State<AttendanceAnalytics> createState() => _AttendanceAnalyticsState();
}

class _AttendanceAnalyticsState extends State<AttendanceAnalytics> {
  String? attendance;
  String? gender;
  @override
  Widget build(BuildContext context,) {

    // final List<AttendanceSummary> summaryList = [];
    // Future<Map<String, dynamic>> jsonData = getAnalytics(context);
    // jsonData.then((Map<String, dynamic> analyticsData) {
    //   // Now you can use the analyticsData variable to access the data
    //   AttendanceSummary summary = AttendanceSummary.fromJson(analyticsData);
    //   summaryList.add(AttendanceSummary(
    //     leaveCount: summary.leaveCount,
    //     endDate: summary.endDate,
    //     presentCount: summary.presentCount,
    //     totalStudents: summary.totalStudents,
    //     absentCount: summary.absentCount,
    //     startDate: summary.startDate,
    //   ));

    //   // Use summary as needed in your code
    // });
    Future<AttendanceSummary> summary = getAnalytics(context);


// Convert List<AttendanceSummary> to List<Summary>
 final List<ChartData> chartData = [
      ChartData(
        'Present',
        45,
      ),
      ChartData(
        'Leave',
        25,
      ),
      ChartData(
        'Absent',
        15,
      ),
    ];
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    normalgreytext(
                      "Today's",
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    const Text(
                      "Student Attendance",
                      style: TextStyle(
                          color: Colors.black, fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                GestureDetector(
                    onTap: () {
                      showsheet(context);
                    },
                    child: Icon(
                      Icons.tune,
                      color: Theme.of(context).primaryColor,
                    ))
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            Container(
                child: SfCircularChart(
              palette: [
                Colors.green,
                Colors.yellow,
                Colors.red,
              ],
              series: <CircularSeries>[
                // Render pie chart
                PieSeries<ChartData, String>(
                  dataSource: chartData,
                  xValueMapper: (ChartData data, _) => data.x, // Replace "x" with the actual property you want to use as the x-value
                  yValueMapper: (ChartData data, _) => data.y, // Replace "y" with the actual property you want to use as the y-value
                ),
              ],
            )),
            const SizedBox(
              height: 40,
            ),
            Stack(
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(10),
                  child: LinearProgressIndicator(
                    value: 0.4,
                    backgroundColor: Colors.green.shade50,
                    color: Colors.green,
                    minHeight: 45,
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 20, vertical: 13),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                       "present",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                         "92",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      )
                    ],
                  ),
                ),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            Stack(
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(10),
                  child: LinearProgressIndicator(
                    value: 0.2,
                    backgroundColor: Colors.red.shade50,
                    color: Colors.red,
                    minHeight: 45,
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 20, vertical: 13),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "Absent",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        "7",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      )
                    ],
                  ),
                )
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            Stack(
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(10),
                  child: LinearProgressIndicator(
                    value: 0.2,
                    backgroundColor: Colors.yellow.shade50,
                    color: Colors.yellow,
                    minHeight: 45,
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 20, vertical: 13),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "Leave",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        "3",
                        style: TextStyle(fontWeight: FontWeight.bold),
                      )
                    ],
                  ),
                )
              ],
            ),
            const SizedBox(
              height: 20,
            ),
          ],
        ),
      ),
    );
  }

  void showsheet(context) {
    showModalBottomSheet(
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
        ),
        isScrollControlled: true,
        context: context,
        builder: (context) {
          return StatefulBuilder(builder: (BuildContext context,
              StateSetter setmodelState /*You can rename this!*/) {
            return Container(
              padding: const EdgeInsets.all(20),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      const Expanded(
                          child: Center(
                              child: Text(
                        "Search Filter",
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.w500),
                      ))),
                      GestureDetector(
                        onTap: () {
                          Navigator.pop(context);
                        },
                        child: Icon(Icons.close),
                      )
                    ],
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  const Text(
                    "Attendance",
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                    children: [
                      Row(
                        children: [
                          Radio(
                              value: "Today",
                              groupValue: attendance,
                              onChanged: (value) {
                                setmodelState(() {
                                  attendance = value;
                                });
                              }),
                          Text("Today")
                        ],
                      ),
                      Row(
                        children: [
                          Radio(
                              value: "Overall",
                              groupValue: attendance,
                              onChanged: (value) {
                                setmodelState(() {
                                  attendance = value;
                                });
                              }),
                          Text("Overall")
                        ],
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  const Text(
                    "Gender",
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Row(
                    children: [
                      Row(
                        children: [
                          Radio(
                              value: "Male",
                              groupValue: gender,
                              onChanged: (value) {
                                setmodelState(() {
                                  gender = value;
                                });
                              }),
                          Text("Male")
                        ],
                      ),
                      Row(
                        children: [
                          Radio(
                              value: "Female",
                              groupValue: gender,
                              onChanged: (value) {
                                setmodelState(() {
                                  gender = value;
                                });
                              }),
                          Text("Female")
                        ],
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 30,
                  ),

                  Custombutton(
                      title: "Apply Filter",
                      ontap: () {
                        Navigator.pop(context);
                      }),
                  // Row(
                  //   children: [
                  //     Expanded(
                  //       child:
                  //     ),
                  //     Custombutton(title: "Apply Filter", ontap: (){}),
                  //   ],
                  // )
                ],
              ),
            );
          });
        });
  }

  Future<AttendanceSummary> getAnalytics(ctx) async {
    Map<String, dynamic> jsonData = {}; // Initialize an empty map
    debugPrint("This is Chapter list");
    // GlobalMethods().showLoader(ctx, true);
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl +
        "/api/students/attendance/summary?startDate=2023-09-12");
    print(url);
    try {
      var response = await get(
        url,
        headers: {"Authorization": "Bearer $token"},
      );
      // GlobalMethods().showLoader(ctx, false);
      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
        if (result is Map<String, dynamic>) {
          jsonData =
              result; // Assign the result to jsonData if it's a valid map
        }
      }
      debugPrint(result.toString());
      if (response.statusCode == 200) {
        // jsonData.addAll(json.decode(response.body)); // This line is not needed

        debugPrint("Successfully Hit the Api");
        debugPrint(result.toString());
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }
    AttendanceSummary summary = AttendanceSummary.fromJson(jsonData);

    return summary;
  }
}

class AttendanceSummary {
  final int leaveCount;
  final List<int> endDate;
  final int presentCount;
  final int totalStudents;
  final int absentCount;
  final List<int> startDate;

  AttendanceSummary({
    required this.leaveCount,
    required this.endDate,
    required this.presentCount,
    required this.totalStudents,
    required this.absentCount,
    required this.startDate,
  });

  factory AttendanceSummary.fromJson(Map<String, dynamic> json) {
    return AttendanceSummary(
      leaveCount: json['leaveCount'] ?? 0,
      endDate: List<int>.from(json['endDate'] ?? []),
      presentCount: json['presentCount'] ?? 0,
      totalStudents: json['totalStudents'] ?? 0,
      absentCount: json['absentCount'] ?? 0,
      startDate: List<int>.from(json['startDate'] ?? []),
    );
  }
}
class ChartData {
  final String x;
  final double y;
  ChartData(
    this.x,
    this.y,
  );
}