import 'package:flutter/cupertino.dart';

class AppState extends ChangeNotifier {
  bool isPressed = false;

  void togglePressed() {
    isPressed = !isPressed;
    notifyListeners();
  }
}