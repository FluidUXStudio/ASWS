import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../constant/apiendpoint.dart';
import '../../model/studentModel/testing.dart';
import '../../utils/toast.dart';
class GetStudentProvider extends ChangeNotifier{
  List<Welcome>_studentlist=[];
  bool _isload =false;
  get studentlist=>_studentlist;
  get isload=>_isload;
  void getstudentslist(ctx)async{
    debugPrint("This is student list");
    _isload=true;

    // GlobalMethods().showLoader(ctx, true);
      final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    final String? zoneId = prefs.getString('zoneId');
    final String? centerId = prefs.getString('centerId');


    debugPrint("This Is token==$token");
      var result;
      final url = Uri.parse(ApiEndPoints.baseurl+'/api/zones/'+zoneId!+'/centers/'+centerId!+'/students');
      print(url);
      print(token);


      try{

        var response= await get(url,
            headers: {
          "Authorization":"Bearer $token"
            },
           );
        _isload=false;
        // GlobalMethods().showLoader(ctx, false);
        if(response.body.isNotEmpty) {
          result=   json.decode(response.body);
        }
        debugPrint(result.toString());
        if(response.statusCode==200){
          List<dynamic>? body = jsonDecode(response.body);
          _studentlist =
              body?.map((dynamic item) => Welcome.fromJson(item)).toList() ?? [];

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


