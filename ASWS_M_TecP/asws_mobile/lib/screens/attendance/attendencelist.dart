// import 'dart:convert';

// import 'package:asws_mobile/model/studentModel/testing.dart';
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:provider/provider.dart';

// import '../../providers/attendanceprovider.dart';
// import '../../providers/studentprovider/getstudentprovider.dart';
// import '../../utils/loader.dart';

// class AttendanceList extends StatefulWidget {
//   const AttendanceList({Key? key}) : super(key: key);

//   @override
//   State<AttendanceList> createState() => _AttendanceListState();
// }

// class _AttendanceListState extends State<AttendanceList> {
//   List<String> studentids = [];

//   bool selectall = false;
//   bool leave = false;
//   @override
//   // @override
//   // void initState() {
//   //   // TODO: implement initState
//   //   super.initState();
//   //   WidgetsBinding.instance.addPostFrameCallback((_) async {
//   //     context.read<GetStudentProvider>().getstudentslist(context);
//   //   });
//   // }

//   Widget build(BuildContext context) {
//     List<Welcome> _studentlist =
//         context.watch<GetStudentProvider>().studentlist;
//     bool isLoad = context.watch<GetStudentProvider>().isload;
//     List<CreateAttendance> attendancelist =
//         context.watch<AttendanceProvider>().getattendancelist;

//     return SingleChildScrollView(
//       child: Padding(
//         padding: const EdgeInsets.all(20),
//         child: Column(
//           children: [
//             Row(
//               mainAxisAlignment: MainAxisAlignment.spaceBetween,
//               children: [
//                 Text("Student(${_studentlist.length})"),

//                 Switch(
//                     value: false,
//                     onChanged: (value) {

//                       if (value) {
//                         Provider.of<AttendanceProvider>(context, listen: false)
//                             .addAllAttendance(_studentlist);
//                       }else{
//                         print(attendancelist);
//                       }
//                       // attendancelist.isEmpty
//                       //     ? Provider.of<AttendanceProvider>(context,
//                       //             listen: false)
//                       //         .addAllAttendance(_studentlist)
//                       //     : null;
//                       // setState(() {
//                       //   selectall=!selectall;
//                       // });
//                     }),
//               ],
//             ),
//             const SizedBox(
//               height: 20,
//             ),
//             Row(
//               children: [
//                 Expanded(
//                     flex: 3,
//                     child: Text(
//                       "Name",
//                       style: TextStyle(fontSize: 13),
//                     )),
//                 Expanded(
//                     flex: 2,
//                     child: Text(
//                       "Leave",
//                       style: TextStyle(fontSize: 13),
//                     )),
//                 Expanded(
//                     flex: 2,
//                     child: Text(
//                       "Absent/Present",
//                       style: TextStyle(fontSize: 13),
//                     )),
//               ],
//             ),
//             const SizedBox(
//               height: 20,
//             ),
//             isLoad
//                 ? AttendanceShimmer()
//                 : _studentlist.isEmpty
//                     ? Container(
//                         height: 300,
//                         width: double.infinity,
//                         alignment: Alignment.center,
//                         child: Text(
//                           "No Data Found ",
//                           style: Theme.of(context).textTheme.headline4,
//                         ),
//                       )
//                     : ListView.builder(
//                         physics: const NeverScrollableScrollPhysics(),
//                         shrinkWrap: true,
//                         itemCount: _studentlist.length,
//                         itemBuilder: (context, index) {
//                           return attendanceitem(
//                               _studentlist[index],
//                               attendancelist.isEmpty
//                                   ? ""
//                                   : attendancelist[index],
//                               index);
//                         }),
//             const SizedBox(
//               height: 20,
//             ),
//             attendancelist.isEmpty
//                 ? Container()
//                 : GestureDetector(
//                     onTap: () {
//                       context
//                           .read<AttendanceProvider>()
//                           .submitattendance(context);
//                     },
//                     child: Container(
//                       height: 50,
//                       width: 100,
//                       alignment: Alignment.center,
//                       decoration: BoxDecoration(
//                           color: Theme.of(context).primaryColor,
//                           borderRadius: BorderRadius.circular(15)),
//                       child: Text(
//                         "Submit",
//                         style: TextStyle(
//                           color: Colors.white,
//                           fontWeight: FontWeight.bold,
//                         ),
//                       ),
//                     ),
//                   )
//           ],
//         ),
//       ),
//     );
//   }

//   Widget attendanceitem(data1, attendancelist, index) {
//     print("this is attenlist===$attendancelist");
//     return Row(
//       children: [
//         Expanded(
//             flex: 3,
//             child: ListTile(
//               contentPadding: EdgeInsets.zero,
//               leading: CircleAvatar(
//                 radius: 20,
//                 backgroundImage:
//                     MemoryImage(const Base64Decoder().convert(data1.imageData)),
//               ),
//               title: Text(
//                 "${data1.studentDetails.firstName}  ${data1.studentDetails.lastName}",
//                 style:
//                     const TextStyle(fontSize: 13, fontWeight: FontWeight.bold),
//               ),
//             )),
//         Expanded(
//             flex: 2,
//             child: attendancelist == ""
//                 ? Container()
//                 : Checkbox(
//                     value: attendancelist == ""
//                         ? false
//                         : attendancelist.student.id == "${data1.id}" &&
//                             attendancelist.status == "leave",
//                     onChanged: (value) {
//                       attendancelist.student.id == "${data1.id}" &&
//                               attendancelist.status == "absent"
//                           ? Provider.of<AttendanceProvider>(context,
//                                   listen: false)
//                               .updateattendance(
//                                   attendancelist.student.id,
//                                   attendancelist.student.id == "${data1.id}" &&
//                                       attendancelist.status == "absent",
//                                   "leave")
//                           : null;
//                       // setState(() {
//                       //   leave=!leave;
//                       // });
//                     })),
//         Expanded(
//           flex: 2,
//           child: attendancelist == ""
//               ? Container()
//               : Switch(
//                   value: attendancelist == ""
//                       ? false
//                       : attendancelist.student.id == "${data1.id}" &&
//                           attendancelist.status == "present",
//                   onChanged: (value) {
//                     Provider.of<AttendanceProvider>(context, listen: false)
//                         .updateattendance(
//                             attendancelist.student.id,
//                             attendancelist.student.id == "${data1.id}" &&
//                                 attendancelist.status == "present",
//                             "");
//                     // value: attendancelist == ""
//                     //     ? false
//                     //     : attendancelist.student.id == "${data1.id}" &&
//                     //         attendancelist.status == "present",
//                     // onChanged: (value) {
//                     //   Provider.of<AttendanceProvider>(context, listen: false)
//                     //       .updateattendance(
//                     //           attendancelist.student.id,
//                     //           attendancelist.student.id == "${data1.id}" &&
//                     //               attendancelist.status == "present",
//                     //           "");
//                     // presentandabsent(data.id);
//                     // setState(() {
//                     //
//                     //   selectall=!selectall;
//                     // });
//                   }),
//         ),
//       ],
//     );
//   }

//   presentandabsent(String id) {
//     if (studentids.contains(id)) {
//       studentids.remove(id);
//     } else {
//       studentids.add(id);
//     }
//     setState(() {});
//     debugPrint(studentids.toString());
//   }
// }

import 'dart:convert';
import 'package:asws_mobile/model/studentModel/testing.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../../providers/studentprovider/getstudentprovider.dart';
import '../../utils/loader.dart';

class AttendanceList extends StatefulWidget {
  @override
  State<AttendanceList> createState() => _AttendanceListState();
}

class _AttendanceListState extends State<AttendanceList> {
  bool isLoad = false; // Initialize the state variable
  bool swit = true;
  bool chec = false;
  @override
  Widget build(BuildContext context) {
    List<Welcome> _studentlist =
        context.watch<GetStudentProvider>().studentlist;

    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text("Student(${_studentlist.length})"),
                Switch(
                  value: isLoad,
                  onChanged: (value) {
                    setState(() {
                      isLoad = value; // Update the state variable
                    });
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
                  ),
                ),
                Expanded(
                  flex: 2,
                  child: Text(
                    "Leave",
                    style: TextStyle(fontSize: 13),
                  ),
                ),
                Expanded(
                  flex: 2,
                  child: Text(
                    "Absent/Present",
                    style: TextStyle(fontSize: 13),
                  ),
                ),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            // Use ListView.builder here
            ListView.builder(
              physics: const NeverScrollableScrollPhysics(),
              shrinkWrap: true,
              itemCount: _studentlist.length,
              itemBuilder: (context, index) {
                return attendanceitem(_studentlist[index], index);
              },
            ),
            Text(
              "Submit",
              style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.bold,
              ),
            )
            // ... Rest of your UI ...
          ],
        ),
      ),
    );
  }

  Widget attendanceitem(data1, index) {
    return Row(
      children: [
        Expanded(
          flex: 3,
          child: ListTile(
            contentPadding: EdgeInsets.zero,
            leading: CircleAvatar(
              radius: 20,
              backgroundImage:
                  MemoryImage(const Base64Decoder().convert(data1.imageData)),
            ),
            title: Text(
              "${data1.studentDetails.firstName}  ${data1.studentDetails.lastName}",
              style: const TextStyle(fontSize: 13, fontWeight: FontWeight.bold),
            ),
          ),
        ),
        Expanded(
          flex: 2,
          child: Checkbox(
            value: swit,
            onChanged: (value) {
              setState(() {
                swit = false; // Update the state variable
              });
            },
          ),
        ),
        Expanded(
          flex: 2,
          child: Switch(
            value: chec,
            onChanged: (value) {
              setState(() {
                chec = value; // Update the state variable
              });
            },
          ),
        ),
      ],
    );
  }
}

class CreateAttendance {
  Student student;
  String status;
  String attendanceDate;

  CreateAttendance(
      {required this.student,
      required this.status,
      required this.attendanceDate});
}

class Student {
  int id;

  Student({required this.id});
}
