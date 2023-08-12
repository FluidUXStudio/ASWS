import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constant/apiendpoint.dart';
import '../utils/toast.dart';

class profile {



  static Future<dynamic> getProfile(BuildContext ctx, String email) async {
    final prefs = await SharedPreferences.getInstance();
    final String? token = prefs.getString('token');
    debugPrint("This Is token==$token");
    var result;

    final url = Uri.parse( ApiEndPoints.baseurl+'/api/teacherProfile');
    final queryParams = {'email': email}; // Add the email as a query parameter

    try {
      var response = await get(
        url.replace(queryParameters: queryParams),
        headers: {"Authorization": "Bearer $token"},
      );

      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
        debugPrint("Successfully Hit the API");
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }

    return result;
  }
}
