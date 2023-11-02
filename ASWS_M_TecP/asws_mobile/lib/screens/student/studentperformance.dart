import 'dart:math';

import 'package:asws_mobile/providers/getStudentPerformence.dart';
import 'package:asws_mobile/utils/buttonutils.dart';
import 'package:asws_mobile/utils/loader.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

import '../../model/studentModel/testing.dart';
import '../../providers/eventproviders/getAllsubjectsProviders.dart';
import '../../providers/studentprovider/getstudentprovider.dart';

class StudentPerformanceScreen extends StatefulWidget {
  final List<Welcome> studenlist;
  StudentPerformanceScreen({Key? key, required this.studenlist})
      : super(key: key);

  @override
  State<StudentPerformanceScreen> createState() =>
      _StudentPerformanceScreenState();
}

class _StudentPerformanceScreenState extends State<StudentPerformanceScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

      Future.delayed(Duration(milliseconds: 100), () {
    showLoaderFor4Seconds(context);
  });

    WidgetsBinding.instance.addPostFrameCallback((_) async {
      context
          .read<GetStudentPerformanceProvider>()
          .getstudentsPerformancelist(context, widget.studenlist);
    });
  }

  String? studentper;
  String? gender;

  Widget build(BuildContext context) {
    List<Student> _studentPerlist =
        context.watch<GetStudentPerformanceProvider>().studentperfoemancelist;
    //  _isload=false;
    List<_SalesData> data = [
      _SalesData(_studentPerlist[0].studentName.firstName,
          _studentPerlist[0].performance),
      _SalesData(_studentPerlist[1].studentName.firstName,
          _studentPerlist[1].performance),
      _SalesData(_studentPerlist[2].studentName.firstName,
          _studentPerlist[2].performance)
    ];
    return Scaffold(
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(15),
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Text(
                    "Students Performance",
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
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
                height: 25,
              ),
              SizedBox(
                height: 300,
                child: SfCartesianChart(
                    primaryXAxis: CategoryAxis(),
                    // Chart title
                    title: ChartTitle(text: 'Top Students'),
                    // Enable legend

                    // Enable tooltip
                    tooltipBehavior: TooltipBehavior(enable: true),
                    series: <ChartSeries<_SalesData, String>>[
                      ColumnSeries<_SalesData, String>(
                        dataSource: data,
                        xValueMapper: (_SalesData sales, _) => sales.year,
                        yValueMapper: (_SalesData sales, _) => sales.sales,
                        name: 'Sales',
                        // Enable data label
                      )
                    ]),
              ),
              const SizedBox(
                height: 30,
              ),
              progressItems(_studentPerlist),
            ],
          ),
        ),
      ),
    );
  }

  // Widget progressitems(List<Student> studentPerformances) {

  //   studentPerformances.map{e => e.name}

  //   return Padding(
  //     padding: const EdgeInsets.symmetric(vertical: 20),
  //     child: Column(
  //       children: [
  //         Row(
  //           mainAxisAlignment: MainAxisAlignment.spaceBetween,
  //           children: [Text(name), Text(percent)],
  //         ),
  //         const SizedBox(
  //           height: 10,
  //         ),
  //         ClipRRect(
  //           borderRadius: BorderRadius.circular(10),
  //           child: LinearProgressIndicator(
  //             value: 0.8,
  //             backgroundColor: Colors.grey.shade300,
  //             color: Colors.blue,
  //             minHeight: 8,
  //           ),
  //         ),
  //       ],
  //     ),
  //   );
  // }
  Widget progressItems(List<Student> studentPerformances) {
    return Column(
      children: studentPerformances.map((student) {
        String name =
            '${student.studentName.firstName} ${student.studentName.lastName}';
        double percent = student.performance;

        return Padding(
          padding: const EdgeInsets.symmetric(vertical: 20),
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [Text(name), Text('$percent%')],
              ),
              const SizedBox(
                height: 10,
              ),
              ClipRRect(
                borderRadius: BorderRadius.circular(10),
                child: LinearProgressIndicator(
                  value: percent / 100,
                  backgroundColor: Colors.grey.shade300,
                  color: Colors.blue,
                  minHeight: 8,
                ),
              ),
            ],
          ),
        );
      }).toList(),
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
                      Expanded(
                          child: Text(
                        "Search Filter",
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.w500),
                      )),
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
                    "Student Performance",
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
                              value: "Top",
                              groupValue: studentper,
                              onChanged: (value) {
                                setmodelState(() {
                                  studentper = value;
                                });
                              }),
                          Text("Top")
                        ],
                      ),
                      Row(
                        children: [
                          Radio(
                              value: "Average",
                              groupValue: studentper,
                              onChanged: (value) {
                                setmodelState(() {
                                  studentper = value;
                                });
                              }),
                          Text("Average")
                        ],
                      ),
                      Row(
                        children: [
                          Radio(
                              value: "Least",
                              groupValue: studentper,
                              onChanged: (value) {
                                setmodelState(() {
                                  studentper = value;
                                });
                              }),
                          Text("Least")
                        ],
                      )
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

  void showLoaderFor4Seconds(ctx) {
    setState(() {
      GlobalMethods().showLoaderNoBag(ctx, true);
    });

    Future.delayed(Duration(seconds: 4), () {
    setState(() {
       GlobalMethods().showLoaderNoBag(ctx, false);
    });
  });
  }
}

class _SalesData {
  _SalesData(this.year, this.sales);

  final String year;
  final double sales;
}
