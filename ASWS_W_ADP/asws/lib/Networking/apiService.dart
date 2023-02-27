import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:http/http.dart';

import '../ApiEndPoints/apiendpoint.dart';
class ApiServices {
  var box = Hive.box('testBox');

  Future<dynamic> getApiCall(ctx, String endpoint) async {
    GlobalMethods().showLoader(ctx, true);
    final String? token = box.get("token");
    print(token);
    final url = Uri.parse(ApiEndPoint.baseurl + endpoint);
    try {
      var response = await get(url,
        headers: {
          "Authorization": "Bearer $token"
        },
      );

      GlobalMethods().showLoader(ctx, false);
      // if(response.body.isNotEmpty) {
      // var   result =   json.decode(response.body);
      // debugPrint(result.toString());
      // }

      print(response.body);
      if (response.statusCode == 200) {
        return response;


        debugPrint("Sucessfully Hit the Api");
      } else {
        return null;

        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }
  }

  Future<dynamic> addtodataApi(File? imageFileList, context,endpoint) async {
    // bool sib = issibling == "Yes";
    // debugPrint(sib.toString());


    final String? token = box.get("token");

    final url = Uri.parse(ApiEndPoint.baseurl + endpoint);

    GlobalMethods().showLoader(context, true);
    var request = MultipartRequest("Post", url);
    request.files
        .add(await MultipartFile.fromPath("file", imageFileList!.path));

    request.headers.addAll({
      "Content-type":
      "multipart/form-data; boundary=<calculated when request is sent>",
      "Authorization": "Bearer $token"
    });

    request.fields.addAll({

    });

    var streamResponse = await request.send();
    var response = await Response.fromStream(streamResponse);

    GlobalMethods().showLoader(context, false);
    debugPrint(response.statusCode.toString());
    if (response.statusCode == 200) {
      print("Post Uploaded Sucessfully");
    } else {
      print("Something Went Wrong");
    }
  }
}
class GlobalMethods {


  void showLoader(BuildContext ctx, bool showLoader) {
    if (showLoader) {
      showDialog(
          barrierDismissible: false,
          context: ctx,
          builder: (ctx) {
            return const Center(
              child: CircularProgressIndicator(),
            );
          });
    } else {
      Navigator.of(ctx).pop();
    }
  }
}