import 'dart:convert';

import 'package:asws_mobile/model/chaptermodel.dart';
import 'package:asws_mobile/screens/student/submitScreen.dart';
import 'package:asws_mobile/utils/buttonutils.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../constant/apiendpoint.dart';
import '../../providers/eventproviders/getAllChapters.dart';
import '../../utils/loader.dart';
import '../../utils/toast.dart';

class SubjectScreen extends StatefulWidget {
  final int studentId;
  final int subjectId;
  final String subjectName;
  final String studentName;
  const SubjectScreen({
    Key? key,
    required this.studentId,
    required this.subjectId,
    required this.subjectName,
    required this.studentName,
  }) : super(key: key);
  @override
  State<SubjectScreen> createState() => _SubjectScreenState();
}

class _SubjectScreenState extends State<SubjectScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((_) async {
      context
          .read<GetChapters>()
          .getChapterlist(context, widget.studentId, widget.subjectId);
    });
  }

  @override
  Widget build(BuildContext context) {
    List<Chapter> _Chapterlist = context.watch<GetChapters>().chapterList;
    String studentName = widget.studentName;

    return Scaffold(
        appBar: AppBar(
          title: Text(widget.subjectName),
        ),
        body: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            children: [
              Expanded(
                child: SingleChildScrollView(child: listitems(_Chapterlist)),
              ),
              Custombutton(
                  title: "Submit",
                  ontap: () {
                    // Iterate through the list and call submitChapters for each chapter
                    for (final chapter in _Chapterlist) {
                      // Call submitChapters method for each chapter
                      submitChapters(
                        context,
                        widget.studentId,
                        widget.subjectId,
                        chapter.id, // Use the chapter ID
                        chapter.isCompleted, // Use the completion status
                      );
                      // GlobalMethods().showLoader(context, true);
                    }
                    Navigator.of(context).pushReplacement(MaterialPageRoute(
                        builder: (context) => SubmitScreen(
                              studentName: studentName,
                            )));
                  }),
              const SizedBox(
                height: 20,
              ),
            ],
          ),
        ));
  }
  // Widget listitems(bool complete,int no,List<Chapter> chapters){

  //  int s = chapters.length;
  //   return  Row(
  //     children: [
  //       Checkbox(value: complete, onChanged: (value){
  //         setState(() {
  //           complete = !complete;
  //         });
  //       }),Text("Page $no")
  //     ],
  //   );
  // }

  Widget listitems(List<Chapter> chapters) {
    return Column(
      children: chapters.map((chapter) {
        bool complete = chapter.isCompleted;
        String chapterName = chapter.chapterName;
        return Row(
          children: [
           
            Checkbox(
              value: complete,
              onChanged: (value) {
                setState(() {
                  complete = !complete;
                  // Update the completion status of the chapter
                  chapter.isCompleted = complete;

                  // context
                  //     .watch<GetChapters>()
                  //     .updateChapterCompletionStatus(chapter.id, complete);
                });
              },
            ),
            Text("$chapterName")
          ],
        );
      }).toList(),
    );
  }

  // List<Map<String, dynamic>> chapterList(List<Chapter> chapters) {
  //   return chapters.map((chapter) {
  //     bool complete = chapter.completed;

  //     return {
  //       'chapter': chapter,
  //       'complete': complete,
  //     };
  //   }).toList();
  // }

  void submitChapters(
      ctx, int studentId, int subjectId, int chapterId, bool completed) async {
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");

    var result;
    final url = Uri.parse(ApiEndPoints.baseurl +
        '/api/studentPerformance/$studentId/subject/$subjectId/chapters/$chapterId/complete?completed=$completed');
    print(url);
    print(token);

    try {
      var response = await put(
        url,
        headers: {
          "Accept": "application/json",
          "content-type": "application/json",
          "Authorization": "Bearer $token"
        },
      );
      if (response.body.isNotEmpty) {
        result = response.body;
      }
      debugPrint(result.toString());
      if (response.statusCode == 200) {
        debugPrint("Sucessfully Hit the Api");
        debugPrint(result.toString());
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }
  }
//   Widget listitems(List<Chapter> chapters) {
//   return Column(
//     children: chapters.asMap().entries.map((entry) {
//       int index = entry.key;
//       Chapter? chapter = entry.value; // Note the '?' after Chapter to indicate it can be null
//       bool complete = chapter?.completed ?? false; // Use null-aware operators to handle null values

//       return Row(
//         children: [
//           Checkbox(
//             value: complete,
//             onChanged: (value) {
//               setState(() {
//                 complete = value ?? false;
//                 // Update the completion status of the chapter (if chapter is not null)
//                 if (chapter != null) {
//                   chapter.completed = complete;
//                 }
//               });
//             },
//           ),
//           Text("Chapter ${index + 1}"), // Use 'index + 1' to display the correct chapter number
//         ],
//       );
//     }).toList(),
//   );
// }
}
