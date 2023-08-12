import 'dart:math';

import 'package:asws_mobile/screens/student/subjectscreen.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class StudentDetailsScreen extends StatelessWidget {
  const StudentDetailsScreen({Key? key}) : super(key: key);
 final String snff = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Subjects"),
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
              itemCount: 6,
              itemBuilder: (BuildContext ctx, index) {
                String title = "";
                String chapter = "";
                if (index == 0) {
                  int randomNumber = generateRandomNumber(29, 40);

                  title = "Al-Hadith";
                  chapter = "${randomNumber}/40";
                  saveData(title);
                } else if (index == 1) {
                  title = "Dua";
                  int randomNumber = generateRandomNumber(29, 40);
                  saveData(title);

                  chapter = "${randomNumber}/40";
                } else if (index == 2) {
                  int randomNumber = generateRandomNumber(2, 7);

                  title = "Azkar-E-Namaz";
                  saveData(title);

                  chapter = "${randomNumber}/7";
                } else if (index == 3) {
                  int randomNumber = generateRandomNumber(2, 7);

                  title = "Kalima";
                  saveData(title);

                  chapter = "${randomNumber}/7";
                } else if (index == 4) {
                  int randomNumber = generateRandomNumber(12, 20);

                  title = "Surah";
                  saveData(title);

                  chapter = "${randomNumber}/20";
                } else if (index == 5) {
                  int randomNumber = generateRandomNumber(15, 28);

                  title = "Tafseer-E-Quran";
                  saveData(title);

                  chapter = "${randomNumber}/28";
                }
                return GestureDetector(
                  onTap: () {
                    Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => SubjectScreen()));
                  },
                  child: Container(
                    padding: const EdgeInsets.all(10),
                    alignment: Alignment.center,
                    decoration: BoxDecoration(
                        color: index.isEven
                            ? Colors.pink.shade100
                            : Colors.blue.shade100,
                        borderRadius: BorderRadius.circular(15)),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          title,
                          style: TextStyle(
                              fontSize: 16, fontWeight: FontWeight.bold),
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
                        const SizedBox(
                          height: 5,
                        ),
                        const Text("In- Progress"),
                        const SizedBox(
                          height: 15,
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              chapter,
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
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
                            )
                          ],
                        ),
                        const SizedBox(
                          height: 15,
                        ),
                      ],
                    ),
                  ),
                );
              }),
        ),
      ),
    );
  }

  int generateRandomNumber(int min, int max) {
    Random random = Random();
    int randomNumber = random.nextInt(max - min + 1) + min;
    return randomNumber;
  }

    Future<String> saveData(String newValue) async {
    String chapter = newValue;
    return chapter;
  }
}
