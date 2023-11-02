import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../constant/apiendpoint.dart';
import '../../model/SubjectModel.dart';
import '../../utils/toast.dart';

class GetSubjectProvider extends ChangeNotifier {
  List<Subject> _subjectList = [];
  bool _isload = false;
  get subjectlist => _subjectList;
  get isload => _isload;
  List toResponseList(List data) {
    List<Subject> value = [];
    for (var element in data) {
      value.add(Subject.fromJson(element));
    }
    return value;
  }

  void getSubjectlist(ctx) async {
    debugPrint("This is subject list");
    _isload = true;
    // GlobalMethods().showLoader(ctx, true);
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;
    final url = Uri.parse(ApiEndPoints.baseurl + ApiEndPoints.getallsubjects);

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
        _subjectList =
            body?.map((dynamic item) => Subject.fromJson(item)).toList() ?? [];

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
}
