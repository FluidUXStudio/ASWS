
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class GetProfileProvider extends ChangeNotifier {

  SharedPreferences? _prefs;

  // Initialize SharedPreferences and notify listeners
  Future<void> initialize() async {
    _prefs = await SharedPreferences.getInstance();
    notifyListeners();
  }

  // Getters for SharedPreferences data
  get profileDetails => _prefs?.getString('teacherProfile');
  get image => _prefs?.getString('teacherProfileImage'); 


}
