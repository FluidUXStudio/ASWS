import 'dart:convert';

import 'package:asws_mobile/providers/getStudentPerformence.dart';
import 'package:asws_mobile/utils/buttonutils.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../constant/apiendpoint.dart';
import '../../utils/toast.dart';

class SubmitScreen extends StatelessWidget {
  final String studentName;

  const SubmitScreen({Key? key, required this.studentName}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Center(
              child: CircleAvatar(
            radius: 60,
            backgroundColor: Colors.green,
            child: Icon(
              Icons.done,
              color: Colors.white,
              size: 100,
            ),
          )),
          const SizedBox(
            height: 20,
          ),
          Text(
            "You have submitted",
            style: TextStyle(
                color: Theme.of(context).primaryColor,
                fontWeight: FontWeight.bold,
                fontSize: 20),
          ),
          Text(
            "$studentName 's lesson for today",
            style: TextStyle(
                color: Theme.of(context).primaryColor,
                fontWeight: FontWeight.bold,
                fontSize: 20),
          ),
          const SizedBox(
            height: 20,
          ),
          GestureDetector(
            onTap: () {
              Navigator.pop(context);
            },
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 15),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                  color: Theme.of(context).primaryColor),
              child: Text(
                "Done",
                style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.bold),
              ),
            ),
          )
        ],
      ),
    );
  }
}
