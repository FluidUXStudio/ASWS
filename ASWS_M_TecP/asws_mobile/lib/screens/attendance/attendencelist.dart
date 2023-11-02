import 'dart:convert';

import 'package:asws_mobile/model/studentModel/testing.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../model/studentmodel.dart';
import '../../providers/attendanceprovider.dart';
import '../../providers/studentprovider/getstudentprovider.dart';
import '../../utils/loader.dart';

class AttendanceList extends StatefulWidget {
  const AttendanceList({Key? key}) : super(key: key);

  @override
  State<AttendanceList> createState() => _AttendanceListState();
}

class _AttendanceListState extends State<AttendanceList> {
  List<String> studentids = [];

  bool selectall = false;
  bool leave = false;
  bool status = false;
  @override
  // @override
  // void initState() {
  //   // TODO: implement initState
  //   super.initState();
  //   WidgetsBinding.instance.addPostFrameCallback((_) async {
  //     context.read<GetStudentProvider>().getstudentslist(context);
  //   });
  // }

  Widget build(BuildContext context) {
    List<Welcome> _studentlist =
        context.watch<GetStudentProvider>().studentlist;
    bool isLoad = context.watch<GetStudentProvider>().isload;
    List<CreateAttendance> attendancelist =
        context.watch<AttendanceProvider>().getattendancelist;

    attendancelist.isEmpty
        ? Provider.of<AttendanceProvider>(context, listen: false)
            .addAllAttendance(_studentlist)
        : null;

    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text("Student(${_studentlist.length})"),
                CupertinoSwitch(
                  value: attendancelist
                      .every((attendance) => attendance.status == "present"),
                  onChanged: (value) {
                    if (value) {
                      print("${value} : Allpresent",);
                      setState(() {
                        
                      Provider.of<AttendanceProvider>(context, listen: false)
                          .markAllPresent(); // Mark all students as absent
                      });

                    } else {
                      print("${value} : AllAbsent");

                        setState(() {
                          
                      Provider.of<AttendanceProvider>(context, listen: false)
                          .markAllAbsent(); // Mark all students as present
                        });
                    }
                  },
                ),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            Row(
              children: [
                Expanded(
                    flex: 3,
                    child: Text(
                      "Name",
                      style: TextStyle(fontSize: 13),
                    )),
                SizedBox(
                  width: 40,
                ),
                Expanded(
                    flex: 2,
                    child: Text(
                      "Leave",
                      style: TextStyle(fontSize: 13),
                    )),
                Expanded(
                    flex: 2,
                    child: Text(
                      "Absent/Present",
                      style: TextStyle(fontSize: 13),
                    )),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            isLoad
                ? AttendanceShimmer()
                : _studentlist.isEmpty
                    ? Container(
                        height: 300,
                        width: double.infinity,
                        alignment: Alignment.center,
                        child: Text(
                          "No Data Found ",
                          style: Theme.of(context).textTheme.headline4,
                        ),
                      )
                    : ListView.builder(
                        physics: const NeverScrollableScrollPhysics(),
                        shrinkWrap: true,
                        itemCount: _studentlist.length,
                        itemBuilder: (context, index) {
                          return attendanceitem(
                              _studentlist[index],
                              attendancelist.isEmpty
                                  ? ""
                                  : attendancelist[index],
                              index);
                        }),
            const SizedBox(
              height: 20,
            ),
            attendancelist.isEmpty
                ? Container()
                : GestureDetector(
                    onTap: () {
                      context
                          .read<AttendanceProvider>()
                          .submitattendance(context);
                    },
                    child: Container(
                      height: 50,
                      width: 100,
                      alignment: Alignment.center,
                      decoration: BoxDecoration(
                          color: Theme.of(context).primaryColor,
                          borderRadius: BorderRadius.circular(15)),
                      child: Text(
                        "Submit",
                        style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  )
          ],
        ),
      ),
    );
  }

  Widget attendanceitem(data, attendancelist, index) {
    // bool isleave = attendancelist?.status == "leave";

    return Row(
      children: [
        Expanded(
            flex: 3,
            child: ListTile(
              contentPadding: EdgeInsets.zero,
              leading: CircleAvatar(
                radius: 20,
                backgroundImage: MemoryImage(
                    const Base64Decoder().convert(data.imageData.toString())),
              ),
              title: Text(
                "${data.studentDetails.firstName} ${data.studentDetails.lastName} ",
                style:
                    const TextStyle(fontSize: 13, fontWeight: FontWeight.bold),
              ),
            )),
        Expanded(
          flex: 2,
          child: Checkbox(
            value: attendancelist == null
                ? false
                : attendancelist.student.id == data.id &&
                    attendancelist.status == "leave",
            onChanged: (value) {
               Provider.of<AttendanceProvider>(context, listen: false)
                  .updateattendanceForLeave(
                data.id, // Assuming 'data.id' is the correct student id
              );
              // if (value!) {
              //   print("${value} : Leave");
              // setState(() {
              // Provider.of<AttendanceProvider>(context, listen: false)
              //     .updateattendanceForLeave(
              //   data.id, // Assuming 'data.id' is the correct student id
              // );
              // });
              // } else {
              //   print("${value} : notLeave");
              // }

            },
          ),
        ),
        Expanded(
            flex: 2,
            child: CupertinoSwitch(
              value: attendancelist == null
                  ? false
                  : attendancelist.student.id == data.id &&
                      attendancelist.status == "present",
              onChanged: (value) {
                if (value) {
                  print("${value} : present");
                } else {
                  print("${value} :absent");
                }

                setState(() {
                  
                Provider.of<AttendanceProvider>(context, listen: false)
                    .updateattendance(
                  data.id, // Assuming 'data.id' is the correct student id
                );
                });
                // print(attendancelist.student.id);
                // print(attendancelist.status);
              },
            )),
      ],
    );
  }
  // Widget attendanceitem(data, attendancelist, index) {
  //   print("this is attenlist===$attendancelist");
  // return Row(
  //   children: [
  // Expanded(
  //     flex: 3,
  //     child: ListTile(
  //       contentPadding: EdgeInsets.zero,
  //       leading: CircleAvatar(
  //         radius: 20,
  //         backgroundImage: MemoryImage(
  //             const Base64Decoder().convert(data.photo.toString())),
  //       ),
  //       title: Text(
  //         "${data.firstName} ${data.lastName} ",
  //         style:
  //             const TextStyle(fontSize: 13, fontWeight: FontWeight.bold),
  //       ),
  //     )),
  // Expanded(
  //     flex: 2,
  //     child: attendancelist == ""
  //         ? Container()
  //         : Checkbox(
  //             value: attendancelist == ""
  //                 ? false
  //                 : attendancelist.id == "${data.id}" &&
  //                     attendancelist.leave == "leave",
  //             onChanged: (value) {
  //               attendancelist.id == "${data.id}" &&
  //                       attendancelist.presentabsent == "absent"
  //                   ? Provider.of<AttendanceProvider>(context,
  //                           listen: false)
  //                       .updateattendance(
  //                           attendancelist.id,
  //                           attendancelist.id == "${data.id}" &&
  //                               attendancelist.presentabsent == "absent",
  //                           "leave")
  //                   : null;
  //               // setState(() {
  //               //   leave=!leave;
  //               // });
  //             })),
  // Expanded(
  //   flex: 2,
  //   child: attendancelist == ""
  //       ? Container()
  //       : CupertinoSwitch(
  // value: attendancelist == ""
  //     ? false
  //     : attendancelist.id == "${data.id}" &&
  //         attendancelist.presentabsent == "present",
  // onChanged: (value) {
  //   Provider.of<AttendanceProvider>(context, listen: false)
  //       .updateattendance(
  //           attendancelist.id,
  //           attendancelist.id == "${data.id}" &&
  //               attendancelist.presentabsent == "present",
  //           "");
  //             // presentandabsent(data.id);
  //             // setState(() {
  //             //
  //             //   selectall=!selectall;
  //             // });
  //           }),
  // ),
  //     ],
  //   );
  // }

  presentandabsent(String id) {
    if (studentids.contains(id)) {
      studentids.remove(id);
    } else {
      studentids.add(id);
    }
    setState(() {});
    debugPrint(studentids.toString());
  }
}

class CreateAttendance {
  Student student;
  String status;
  String attendanceDate;

  CreateAttendance({
    required this.student,
    required this.status,
    required this.attendanceDate,
  });
}

class Student {
  int id;

  Student({required this.id});
}
