import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../constant/apiendpoint.dart';
import '../../model/chaptermodel.dart';
import '../../utils/loader.dart';
import '../../utils/toast.dart';

class GetChapters extends ChangeNotifier {
  List<Chapter> _chapterList = [];
  bool _isload = false;
  get chapterList => _chapterList;
  get isload => _isload;

  void getChapterlist(ctx ,int studentId,int subjectId) async {
    debugPrint("This is Chapter list");
    _isload = true;
    // GlobalMethods().showLoader(ctx, true);
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl + "/api/studentPerformance/student/$studentId/subject/$subjectId");
    print(url);
    try {
      var response = await get(
        url,
        headers: {"Authorization": "Bearer $token"},
      );
      _isload = false;
      // GlobalMethods().showLoader(ctx, false);
      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
      }
      debugPrint(result.toString());
      if (response.statusCode == 200) {
        List<dynamic>? body = jsonDecode(response.body);
        _chapterList =
            body?.map((dynamic item) => Chapter.fromJson(item)).toList() ?? [];

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
  }

    
   
  void submitChapters(ctx ,int studentId,int subjectId,int chapterId,bool completed) async {
   
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    GlobalMethods().showLoader(ctx, true);

    var result;
    final url = Uri.parse(ApiEndPoints.baseurl + '/api/studentPerformance/$studentId/subject/$subjectId/chapters/$chapterId/complete?completed=$completed');
    print(url);
    print(token);

    try {
      var response = await put(url,
          headers: {
            "Accept": "application/json",
            "content-type": "application/json",
            "Authorization": "Bearer $token"
          },
      );
      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
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
  void updateChapterCompletionStatus(int chapterId, bool completed , String chapterName) {
  var chapterIndex = chapterList.indexWhere((chapter) => chapter.id == chapterId);

  if (chapterIndex != -1) {
    chapterList[chapterIndex] = Chapter(
      id: chapterId,
      isCompleted: completed,
      chapterName: chapterName
      // Include other chapter properties here
    );

    notifyListeners();
  }
}
}
