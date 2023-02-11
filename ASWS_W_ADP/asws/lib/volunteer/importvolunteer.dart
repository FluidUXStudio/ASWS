import 'package:flutter/material.dart';

class ImportVolunteer extends StatefulWidget {
  VoidCallback ontap;
  ImportVolunteer(this.ontap);

  @override
  State<ImportVolunteer> createState() => _ImportTeacherState();
}

class _ImportTeacherState extends State<ImportVolunteer> {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Expanded(
                    child: GestureDetector(
                  onTap: widget.ontap,
                  child: Text(
                    "Volunteer / Import Volunteer",
                    style: Theme.of(context).textTheme.headline1,
                  ),
                )),
                IconButton(
                    onPressed: () {},
                    icon: const Icon(
                      Icons.notifications_outlined,
                      size: 35,
                    )),
                const SizedBox(
                  width: 30,
                ),
                IconButton(
                    onPressed: () {},
                    icon: const Icon(
                      Icons.settings_outlined,
                      size: 35,
                    )),
                const SizedBox(
                  width: 30,
                ),
                Column(
                  children: const [
                    Text(
                      "Yahiya Ali",
                      style: TextStyle(color: Colors.deepPurple, fontSize: 20),
                    ),
                    Text("Admin"),
                  ],
                ),
                const SizedBox(
                  width: 20,
                ),
                Container(
                  height: 70,
                  width: 70,
                  decoration: const BoxDecoration(
                      shape: BoxShape.circle, color: Colors.lightBlueAccent),
                )
              ],
            ),
            Container(
              height: MediaQuery.of(context).size.height * 0.9,
              alignment: Alignment.center,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    "Upload your files",
                    style: Theme.of(context).textTheme.headline1,
                  ),
                  const SizedBox(
                    height: 50,
                  ),
                  Card(
                    child: Container(
                      padding: const EdgeInsets.all(50),
                      height: 250,
                      width: 400,
                      child: Column(
                        children: [
                          Icon(
                            Icons.file_copy_outlined,
                            size: 100,
                            color: Theme.of(context).primaryColor,
                          ),
                          const SizedBox(
                            height: 20,
                          ),
                          const Text("Drag and drop or Choose a file")
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
