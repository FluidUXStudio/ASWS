import 'dart:async';

import 'package:asws_mobile/providers/attendanceprovider.dart';
import 'package:asws_mobile/providers/eventproviders/getAllChapters.dart';
import 'package:asws_mobile/providers/eventproviders/getAllsubjectsProviders.dart';
import 'package:asws_mobile/providers/eventproviders/getalleventprovider.dart';
import 'package:asws_mobile/providers/getStudentPerformence.dart';
import 'package:asws_mobile/providers/profileProvider.dart';
import 'package:asws_mobile/providers/studentprovider/getstudentprovider.dart';
import 'package:asws_mobile/screens/dashboard/dashboard.dart';
import 'package:asws_mobile/screens/signup/onboardscreen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider(create: (context) => GetStudentProvider()),
      ChangeNotifierProvider(create: (context) => GetSubjectProvider()),
      ChangeNotifierProvider(create: (context) => GetEventsProvider()),
      ChangeNotifierProvider(create: (context) => AttendanceProvider()),
      ChangeNotifierProvider(create: (context) => GetChapters()),
      ChangeNotifierProvider(create: (context) => GetProfileProvider()),
      ChangeNotifierProvider(
          create: (context) => GetStudentPerformanceProvider()),
    ],
    child: const MyApp(),
  ));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          primaryColor: Colors.blueAccent, cardColor: Colors.purple.shade50),
      home: SplashScreen(),
    );
  }
}

class SplashScreen extends StatefulWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    Timer(const Duration(seconds: 3), () {
      gotoScreen();
    });
  }

  // void gotoScreen() async {
  //   // final prefs = await SharedPreferences.getInstance();
  //   // final String? token = prefs.getString('token');
  //   Navigator.of(context).pushReplacement(
  //       MaterialPageRoute(builder: (context) => const OnboardScreen()));
  //   // if(token==null){
  //   // }else{
  //   //   Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context)=>const OnboardScreen()));
  //   // }
  // }
void gotoScreen() async {
  final prefs = await SharedPreferences.getInstance();
  final String? token = prefs.getString('token');
  if (token == null) {
      print("No get ttoken");
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(builder: (context) => const OnboardScreen())
    );
  } else {
    // Navigate to another screen (e.g., Dashboard or HomeScreen)
    // Example:
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(builder: (context) => DashBoard())
    );
          print("get ttoken");

  }
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Image.asset("assets/images/splash.png"),
      ),
    );
  }
}
