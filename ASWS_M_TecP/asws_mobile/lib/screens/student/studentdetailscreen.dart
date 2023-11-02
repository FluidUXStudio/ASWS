// // import 'dart:math';

// // import 'package:asws_mobile/model/SubjectModel.dart';
// // import 'package:asws_mobile/providers/eventproviders/getAllsubjectsProviders.dart';
// // import 'package:asws_mobile/screens/student/subjectscreen.dart';
// // import 'package:flutter/material.dart';
// // import 'package:provider/provider.dart';
// // import 'package:shared_preferences/shared_preferences.dart';

// // class StudentDetailsScreen extends StatelessWidget {
// //   const StudentDetailsScreen({Key? key}) : super(key: key);
// //  final String snff = "";

// //   @override
// //   Widget build(BuildContext context) {

// //     List<Subject> subjectlist =
// //         context.watch<GetSubjectProvider>().subjectlist;

// //     return Scaffold(
// //       appBar: AppBar(
// //         title: Text("Subjects"),
// //       ),
// //       body: SingleChildScrollView(
// //         child: Padding(
// //           padding: const EdgeInsets.all(20),
// //           child: GridView.builder(
// //               physics: const NeverScrollableScrollPhysics(),
// //               shrinkWrap: true,
// //               gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
// //                 crossAxisCount: 2,
// //                 childAspectRatio: 3 / 4,
// //                 crossAxisSpacing: 20,
// //                 mainAxisSpacing: 20,
// //               ),
// //               itemCount: subjectlist.length,
// //               itemBuilder: (BuildContext ctx, index) {
// //                 Subject subject = subjectlist[index];
// //                 String title = "";
// //                 String chapter = "";
// //                 if (index == 0) {
// //                   int randomNumber = generateRandomNumber(29, 40);

// //                   title = subject.name;
// //                   chapter = "${randomNumber}/40";
// //                   saveData(title);
// //                 } else if (index == 1) {
// //                   title = "Dua";
// //                   int randomNumber = generateRandomNumber(29, 40);
// //                   saveData(title);

// //                   chapter = "${randomNumber}/40";
// //                 } else if (index == 2) {
// //                   int randomNumber = generateRandomNumber(2, 7);

// //                   title = "Azkar-E-Namaz";
// //                   saveData(title);

// //                   chapter = "${randomNumber}/7";
// //                 } else if (index == 3) {
// //                   int randomNumber = generateRandomNumber(2, 7);

// //                   title = "Kalima";
// //                   saveData(title);

// //                   chapter = "${randomNumber}/7";
// //                 } else if (index == 4) {
// //                   int randomNumber = generateRandomNumber(12, 20);

// //                   title = "Surah";
// //                   saveData(title);

// //                   chapter = "${randomNumber}/20";
// //                 } else if (index == 5) {
// //                   int randomNumber = generateRandomNumber(15, 28);

// //                   title = "Tafseer-E-Quran";
// //                   saveData(title);

// //                   chapter = "${randomNumber}/28";
// //                 }
// //                 return GestureDetector(
// //                   onTap: () {
// //                     Navigator.of(context).push(MaterialPageRoute(
// //                         builder: (context) => SubjectScreen()));
// //                   },
// //                   child: Container(
// //                     padding: const EdgeInsets.all(10),
// //                     alignment: Alignment.center,
// //                     decoration: BoxDecoration(
// //                         color: index.isEven
// //                             ? Colors.pink.shade100
// //                             : Colors.blue.shade100,
// //                         borderRadius: BorderRadius.circular(15)),
// //                     child: Column(
// //                       crossAxisAlignment: CrossAxisAlignment.start,
// //                       children: [
// //                         Text(
// //                           title,
// //                           style: TextStyle(
// //                               fontSize: 16, fontWeight: FontWeight.bold),
// //                         ),
// //                         const Spacer(),
// //                         ClipRRect(
// //                           borderRadius: BorderRadius.circular(10),
// //                           child: LinearProgressIndicator(
// //                             value: 0.3,
// //                             backgroundColor: Colors.white,
// //                             color: index.isEven ? Colors.orange : Colors.blue,
// //                             minHeight: 8,
// //                           ),
// //                         ),
// //                         const SizedBox(
// //                           height: 5,
// //                         ),
// //                         const Text("In- Progress"),
// //                         const SizedBox(
// //                           height: 15,
// //                         ),
// //                         Row(
// //                           mainAxisAlignment: MainAxisAlignment.spaceBetween,
// //                           children: [
// //                             Text(
// //                               chapter,
// //                               style: TextStyle(
// //                                   fontSize: 16, fontWeight: FontWeight.bold),
// //                             ),
// //                             CircleAvatar(
// //                               radius: 25,
// //                               backgroundColor: index.isEven
// //                                   ? Colors.pinkAccent
// //                                   : Colors.blueAccent,
// //                               child: const Icon(
// //                                 Icons.play_arrow,
// //                                 size: 40,
// //                                 color: Colors.white,
// //                               ),
// //                             )
// //                           ],
// //                         ),
// //                         const SizedBox(
// //                           height: 15,
// //                         ),
// //                       ],
// //                     ),
// //                   ),
// //                 );
// //               }),
// //         ),
// //       ),
// //     );
// //   }

// //   int generateRandomNumber(int min, int max) {
// //     Random random = Random();
// //     int randomNumber = random.nextInt(max - min + 1) + min;
// //     return randomNumber;
// //   }

// //     Future<String> saveData(String newValue) async {
// //     String chapter = newValue;
// //     return chapter;
// //   }
// // }

// import 'dart:convert';
// import 'dart:math';
// import 'package:asws_mobile/constant/apiendpoint.dart';
// import 'package:asws_mobile/model/SubjectModel.dart';
// import 'package:asws_mobile/model/chaptermodel.dart';
// import 'package:asws_mobile/providers/eventproviders/getAllChapters.dart';
// import 'package:asws_mobile/providers/eventproviders/getAllsubjectsProviders.dart';
// import 'package:asws_mobile/providers/getStudentPerformence.dart';
// import 'package:asws_mobile/screens/student/subjectscreen.dart';
// import 'package:asws_mobile/utils/toast.dart';
// import 'package:flutter/material.dart';
// import 'package:http/http.dart';
// import 'package:provider/provider.dart';
// import 'package:shared_preferences/shared_preferences.dart';

// class StudentDetailsScreen extends StatefulWidget {
//   final int studentId;
//   final String studentName;
//   const StudentDetailsScreen({
//     Key? key,
//     required this.studentId,
//     required this.studentName,
//   }) : super(key: key);

//   @override
//   _StudentDetailsScreenState createState() => _StudentDetailsScreenState();
// }

// class _StudentDetailsScreenState extends State<StudentDetailsScreen> {
//   @override
//   void initState() {
//     // TODO: implement initState
//     super.initState();

//     WidgetsBinding.instance.addPostFrameCallback((_) async {
//       context.read<GetSubjectProvider>().getSubjectlist(context);
//     });
//   }

//   @override
//   Widget build(BuildContext context) {
//     List<Subject> subjectlist = context.watch<GetSubjectProvider>().subjectlist;
//     final getChapters = context.watch<GetChapters>();

//     bool show = context.watch<GetSubjectProvider>().isload;
//     print(show);
//     String StudentName = widget.studentName;

//     return Scaffold(
//       appBar: AppBar(
//         title: Text("$StudentName Subjects"),
//       ),
//       body: SingleChildScrollView(
//         child: Padding(
//           padding: const EdgeInsets.all(20),
//           child: GridView.builder(
//             physics: const NeverScrollableScrollPhysics(),
//             shrinkWrap: true,
//             gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
//               crossAxisCount: 2,
//               childAspectRatio: 3 / 4,
//               crossAxisSpacing: 20,
//               mainAxisSpacing: 20,
//             ),
//             itemCount: subjectlist.length,
//             itemBuilder: (BuildContext ctx, index) {
//               Subject subject = subjectlist[index];
//               int subjectId = subject.id;

//               // Use FutureBuilder to get the total chapter count asynchronously
//               return FutureBuilder<int>(
//                 future: getChapterCount(context, widget.studentId, subjectId),
//                 builder: (context, snapshot) {
//                   if (snapshot.connectionState == ConnectionState.waiting) {
//                     // While waiting for the data, display a loading indicator or placeholder
//                     return CircularProgressIndicator();
//                   } else if (snapshot.hasError) {
//                     // Handle errors
//                     return Text('Error: ${snapshot.error}');
//                   } else if (!snapshot.hasData) {
//                     // Handle the case where data is null
//                     return Text('Data is null');
//                   } else {
//                     // Data is available, you can access it using snapshot.data
//                     Future<List<bool>> completed = getIsCompleted(context);
//                     int? totalChapters = snapshot.data;
//                     String title = subject.name;
//                     int randomNumber = completed.length;
//                     String chapter = "$randomNumber/$totalChapters";
//                     return GestureDetector(
//                       onTap: () {
//                         saveSubjectId(subjectId);

//                         Navigator.of(context).push(MaterialPageRoute(
//                           builder: (context) => SubjectScreen(
//                             studentId: widget.studentId,
//                             subjectId: subjectId,
//                             subjectName: title,
//                             studentName: StudentName,
//                           ),
//                         ));
//                       },
//                       child: Container(
//                         padding: const EdgeInsets.all(10),
//                         alignment: Alignment.center,
//                         decoration: BoxDecoration(
//                           color: index.isEven
//                               ? Colors.pink.shade100
//                               : Colors.blue.shade100,
//                           borderRadius: BorderRadius.circular(15),
//                         ),
//                         child: Column(
//                           crossAxisAlignment: CrossAxisAlignment.start,
//                           children: [
//                             Text(
//                               title,
//                               style: TextStyle(
//                                 fontSize: 16,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             const Spacer(),
//                             ClipRRect(
//                               borderRadius: BorderRadius.circular(10),
//                               child: LinearProgressIndicator(
//                                 value: 0.3,
//                                 backgroundColor: Colors.white,
//                                 color:
//                                     index.isEven ? Colors.orange : Colors.blue,
//                                 minHeight: 8,
//                               ),
//                             ),
//                             const SizedBox(height: 5),
//                             const Text("In-Progress"),
//                             const SizedBox(height: 15),
//                             Row(
//                               mainAxisAlignment: MainAxisAlignment.spaceBetween,
//                               children: [
//                                 Text(
//                                   chapter,
//                                   style: TextStyle(
//                                     fontSize: 16,
//                                     fontWeight: FontWeight.bold,
//                                   ),
//                                 ),
//                                 CircleAvatar(
//                                   radius: 25,
//                                   backgroundColor: index.isEven
//                                       ? Colors.pinkAccent
//                                       : Colors.blueAccent,
//                                   child: const Icon(
//                                     Icons.play_arrow,
//                                     size: 40,
//                                     color: Colors.white,
//                                   ),
//                                 ),
//                               ],
//                             ),
//                             const SizedBox(height: 15),
//                           ],
//                         ),
//                       ),
//                     );
//                   }
//                 },
//               );
//             },
//           ),
//         ),
//       ),
//     );
//   }

//   Future<int> getChapterCount(
//       BuildContext context, int studentId, int subjectId) async {
//     try {
//       List<Chapter> chapterList =
//           await getChapterNum(context, studentId, subjectId);
//       return chapterList.length;
//     } catch (error) {
//       print('Error getting chapter count: $error');
//       return 0;
//     }
//   }

//   Future<List<Chapter>?> getChapter(
//       BuildContext context, int studentId, int subjectId) async {
//     try {
//       List<Chapter> chapterList =
//           await getChapterNum(context, studentId, subjectId);
//       if (chapterList.isNotEmpty) {
//         // You can choose which chapter to return based on your criteria
//         // For example, if you want to return the first chapter:
//         return chapterList;
//       } else {
//         // Handle the case where the chapter list is empty
//         return null; // You can return null or another default value
//       }
//     } catch (error) {
//       print('Error getting chapter: $error');
//       return null; // Handle the error and return null or another default value
//     }
//   }

//   Future<List<Chapter>> getChapterNum(
//     dynamic ctx,
//     int studentId,
//     int subjectId,
//   ) async {
//     debugPrint("This is Chapter list");
//     // GlobalMethods().showLoader(ctx, true);
//     final prefs = await SharedPreferences.getInstance();
//     final String? token = prefs.getString('token');
//     debugPrint("This Is token==$token");
//     var result;
//     final url = Uri.parse(ApiEndPoints.baseurl +
//         "/api/studentPerformance/student/$studentId/subject/$subjectId");
//     print(url);
//     try {
//       var response = await get(
//         url,
//         headers: {"Authorization": "Bearer $token"},
//       );
//       // GlobalMethods().showLoader(ctx, false);
//       if (response.body.isNotEmpty) {
//         result = json.decode(response.body);
//       }
//       debugPrint(result.toString());
//       if (response.statusCode == 200) {
//         List<dynamic>? body = jsonDecode(response.body);
//         List<Chapter> chapterList =
//             body?.map((dynamic item) => Chapter.fromJson(item)).toList() ?? [];

//         debugPrint("Successfully Hit the API");

//         return chapterList;
//       } else {
//         showToast("Something went wrong");
//         debugPrint("Something went wrong");
//       }
//     } catch (error) {
//       debugPrint(error.toString());
//     }

//     // Return an empty list in case of an error or other conditions
//     return [];
//   }

//   Future<void> saveSubjectId(int subjectId) async {
//     final prefs = await SharedPreferences.getInstance();
//     await prefs.setInt('selectedSubjectId', subjectId);
//   }

//   int generateRandomNumber(int min, int max) {
//     Random random = Random();
//     int randomNumber = random.nextInt(max - min + 1) + min;
//     return randomNumber;
//   }

//    Future<List<bool>> getIsCompleted(BuildContext ctx) async {
//   List<bool> isCompleted = [];
//   debugPrint("This is subject list");
//   final prefs = await SharedPreferences.getInstance();
//   final String? token = prefs.getString('token');
//   debugPrint("This Is token==$token");
//   var result;
//   final url = Uri.parse(ApiEndPoints.baseurl +
//       '/api/studentPerformance/student/1297/subject/3');

//   try {
//     var response = await get(
//       url,
//       headers: {"Authorization": "Bearer $token"},
//     );

//     if (response.body.isNotEmpty) {
//       result = json.decode(response.body);
//     }
//     debugPrint(result.toString());

//     if (response.statusCode == 200) {
//       List<dynamic>? body = jsonDecode(response.body);

//       List<ChapterModel> chapters =
//           body?.map((dynamic item) => ChapterModel.fromJson(item)).toList() ??
//               [];

//       isCompleted = chapters.map((e) => e.isCompleted).toList();

//       debugPrint("Successfully Hit the API");
//       debugPrint(result.toString());

//       return isCompleted;
//     } else {
//       showToast("Something went wrong");
//       debugPrint("Something went wrong");
//     }
//   } catch (error) {
//     debugPrint(error.toString());
//   }

//   return isCompleted; // Return an empty list if there's an error
// }
// }

// class ChapterModel {
//   final int id;
//   final bool isCompleted;
//   final String chapterName;

//   ChapterModel({
//     required this.id,
//     required this.isCompleted,
//     required this.chapterName,
//   });

//   factory ChapterModel.fromJson(Map<String, dynamic> json) {
//     return ChapterModel(
//       id: json['id'],
//       isCompleted: json['isCompleted'],
//       chapterName: json['chapterName'],
//     );
//   }

//   Map<String, dynamic> toJson() {
//     return {
//       'id': id,
//       'isCompleted': isCompleted,
//       'chapterName': chapterName,
//     };
//   }
// }
import 'dart:convert';
import 'package:asws_mobile/constant/apiendpoint.dart';
import 'package:asws_mobile/model/SubjectModel.dart';
import 'package:asws_mobile/model/chaptermodel.dart';
import 'package:asws_mobile/providers/eventproviders/getAllsubjectsProviders.dart';
import 'package:asws_mobile/screens/student/subjectscreen.dart';
import 'package:asws_mobile/utils/toast.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

class StudentDetailsScreen extends StatefulWidget {
  final int studentId;
  final String studentName;
  const StudentDetailsScreen({
    Key? key,
    required this.studentId,
    required this.studentName,
  }) : super(key: key);

  @override
  _StudentDetailsScreenState createState() => _StudentDetailsScreenState();
}

class _StudentDetailsScreenState extends State<StudentDetailsScreen> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      context.read<GetSubjectProvider>().getSubjectlist(context);
    });
  }

  @override
  Widget build(BuildContext context) {
    List<Subject> subjectlist = context.watch<GetSubjectProvider>().subjectlist;
    List<bool> coms =[];

    String StudentName = widget.studentName;

    return Scaffold(
      appBar: AppBar(
        title: Text("$StudentName Subjects"),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: GridView.builder(
            physics: const NeverScrollableScrollPhysics(),
            shrinkWrap: true,
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              childAspectRatio: 3 / 4,
              crossAxisSpacing: 20,
              mainAxisSpacing: 20,
            ),
            itemCount: subjectlist.length,
            itemBuilder: (BuildContext ctx, index) {
              Subject subject = subjectlist[index];
              int subjectId = subject.id;

              // Use FutureBuilder to get the total chapter count asynchronously
              return FutureBuilder<int> (
                future: getChapterCount(context, widget.studentId, subjectId),
                builder: (context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    // While waiting for the data, display a loading indicator or placeholder
                    return CircularProgressIndicator();
                  } else if (snapshot.hasError) {
                    // Handle errors
                    return Text('Error: ${snapshot.error}');
                  } else if (!snapshot.hasData) {
                    // Handle the case where data is null
                    return Text('Data is null');
                  } else {
                    
                    int totalChapters = snapshot.data!;
                    String title = subject.name;
                  
                    // Call getIsCompleted function to get the completion status
                    Future<List<bool>> completed = getIsCompleted(context,widget.studentId,subjectId);

                    return FutureBuilder<List<bool>>(
                      future: completed,
                      builder: (context, completedSnapshot) {
                        if (completedSnapshot.connectionState == ConnectionState.waiting) {
                          // While waiting for the data, display a loading indicator or placeholder
                          return CircularProgressIndicator();
                        } else if (completedSnapshot.hasError) {
                          // Handle errors
                          return Text('Error: ${completedSnapshot.error}');
                        } else if (!completedSnapshot.hasData) {
                          // Handle the case where data is null
                          return Text('Data is null');
                        } else {
                          List<bool> iscomtr =[];
                           coms = completedSnapshot.data!;
                          for(bool b in coms){
                            if(b == true){
                           
                                
                              iscomtr.add(b);
                
                            }
                          }


                          int randomNumber = iscomtr.length;
                          String chapter = "$randomNumber/$totalChapters";

                          return GestureDetector(
                            onTap: () {
                              saveSubjectId(subjectId);

                              Navigator.of(context).push(MaterialPageRoute(
                                builder: (context) => SubjectScreen(
                                  studentId: widget.studentId,
                                  subjectId: subjectId,
                                  subjectName: title,
                                  studentName: StudentName,
                                ),
                              ));
                            },
                            child: Container(
                              padding: const EdgeInsets.all(10),
                              alignment: Alignment.center,
                              decoration: BoxDecoration(
                                color: index.isEven
                                    ? Colors.pink.shade100
                                    : Colors.blue.shade100,
                                borderRadius: BorderRadius.circular(15),
                              ),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    title,
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                  const Spacer(),
                                  ClipRRect(
                                    borderRadius: BorderRadius.circular(10),
                                    child: LinearProgressIndicator(
                                      value: 0.3,
                                      backgroundColor: Colors.white,
                                      color: index.isEven ? Colors.orange : Colors.blue,
                                      minHeight: 8,
                                    ),
                                  ),
                                  const SizedBox(height: 5),
                                  const Text("In- Progress"),
                                  const SizedBox(height: 15),
                                  Row(
                                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                    children: [
                                      Text(
                                        chapter,
                                        style: TextStyle(
                                          fontSize: 16,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                      CircleAvatar(
                                        radius: 25,
                                        backgroundColor: index.isEven
                                            ? Colors.pinkAccent
                                            : Colors.blueAccent,
                                        child: const Icon(
                                          Icons.play_arrow,
                                          size: 40,
                                          color: Colors.white,
                                        ),
                                      ),
                                    ],
                                  ),
                                  const SizedBox(height: 15),
                                ],
                              ),
                            ),
                          );
                        }
                      },
                    );
                  }
                },
              );
            },
          ),
        ),
      ),
    );
  }

  Future<int> getChapterCount(
      BuildContext context, int studentId, int subjectId) async {
    try {
      List<Chapter> chapterList =
          await getChapterNum(context, studentId, subjectId);
      return chapterList.length;
    } catch (error) {
      print('Error getting chapter count: $error');
      return 0;
    }
      }
  int countTrueValues(List<bool> boolList) {
  int count = 0;
  for (bool value in boolList) {
    if (value == true) {
      count++;
    }
  }
  return count;
}


  // int countTrueValues(Future<List<bool>> boolList) {

  //   List<bool> com= [];
  //   boolList.then((List<bool> boolList) {
  //     // Use the countTrueValues function to count the true values
  //     boolList.map((e) => {
  //       if(e == true){
  //         com.add(e),
  //         print(e)
  //       }
  //     });
  //   }).catchError((error) {
  //     // Handle errors here
  //     print('Error: $error');
  //   });

  //   // int count = 0;
  //   // for (bool value in com) {
  //   //   if (value == true) {
  //   //     count++;
  //   //   }
  //   // }
  //   return com.length;
  // }

  Future<List<Chapter>?> getChapter(
      BuildContext context, int studentId, int subjectId) async {
    try {
      List<Chapter> chapterList =
          await getChapterNum(context, studentId, subjectId);
      if (chapterList.isNotEmpty) {
        // You can choose which chapter to return based on your criteria
        // For example, if you want to return the first chapter:
        return chapterList;
      } else {
        // Handle the case where the chapter list is empty
        return null; // You can return null or another default value
      }
    } catch (error) {
      print('Error getting chapter: $error');
      return null; // Handle the error and return null or another default value
    }
  }

  Future<List<Chapter>> getChapterNum(
    dynamic ctx,
    int studentId,
    int subjectId,
  ) async {
    debugPrint("This is Chapter list");
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl +
        "/api/studentPerformance/student/$studentId/subject/$subjectId");
    print(url);
    try {
      var response = await get(
        url,
        headers: {"Authorization": "Bearer $token"},
      );
      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
      }

      if (response.statusCode == 200) {
        List<dynamic>? body = jsonDecode(response.body);
        List<Chapter> chapterList =
            body?.map((dynamic item) => Chapter.fromJson(item)).toList() ?? [];

        debugPrint("Successfully Hit the API");

        return chapterList;
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }

    // Return an empty list in case of an error or other conditions
    return [];
  }

  Future<void> saveSubjectId(int subjectId) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setInt('selectedSubjectId', subjectId);
  }

  Future<List<bool>> getIsCompleted(BuildContext ctx , int studentId,int subjectId) async {
    List<bool> isCompleted = [];
    debugPrint("This is count list");
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl +
        '/api/studentPerformance/student/$studentId/subject/$subjectId');

    try {
      var response = await get(
        url,
        headers: {"Authorization": "Bearer $token"},
      );

      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
      }
      // debugPrint(result.toString());

      if (response.statusCode == 200) {
        List<dynamic>? body = jsonDecode(response.body);

        List<ChapterModel> chapters =
            body?.map((dynamic item) => ChapterModel.fromJson(item)).toList() ??
                [];

        isCompleted = chapters.map((e) => e.isCompleted).toList();

        debugPrint("Successfully Hit the API");
        // debugPrint(isCompleted.toString());

        return isCompleted;
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }

    return isCompleted; // Return an empty list if there's an error
  }
}

class ChapterModel {
  final int id;
  final bool isCompleted;
  final String chapterName;

  ChapterModel({
    required this.id,
    required this.isCompleted,
    required this.chapterName,
  });

  factory ChapterModel.fromJson(Map<String, dynamic> json) {
    return ChapterModel(
      id: json['id'],
      isCompleted: json['isCompleted'],
      chapterName: json['chapterName'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'isCompleted': isCompleted,
      'chapterName': chapterName,
    };
  }
}
