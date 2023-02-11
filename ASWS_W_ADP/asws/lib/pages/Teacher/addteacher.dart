import 'package:flutter/material.dart';

import '../../utils/appColors.dart';
import '../Forms/newstudentForm.dart';

class AddTeacher extends StatefulWidget {
  VoidCallback ontap;
  AddTeacher(this.ontap);

  @override
  State<AddTeacher> createState() => _AddTeacherState();
}

class _AddTeacherState extends State<AddTeacher> {
  TextEditingController firstname = TextEditingController();
  TextEditingController lastname = TextEditingController();
  TextEditingController Adhaarno = TextEditingController();
  TextEditingController email = TextEditingController();
  TextEditingController address = TextEditingController();
  TextEditingController palaceofbirth = TextEditingController();
  TextEditingController phone = TextEditingController();
  TextEditingController dateofbirth = TextEditingController();
  TextEditingController univerty = TextEditingController();
  TextEditingController startdate = TextEditingController();
  TextEditingController enddate = TextEditingController();
  TextEditingController city = TextEditingController();
  TextEditingController degree = TextEditingController();

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
                    "Teacher / Create Teacher",
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
            const SizedBox(
              height: 30,
            ),
            Card(
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10)),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    padding: const EdgeInsets.only(left: 30),
                    height: 60,
                    width: double.infinity,
                    decoration: BoxDecoration(
                        color: Theme.of(context).primaryColor,
                        borderRadius: const BorderRadius.only(
                            topRight: Radius.circular(10),
                            topLeft: Radius.circular(10))),
                    alignment: Alignment.centerLeft,
                    child: Text(
                      "Personal Details",
                      style: TextStyle(
                          color: AppColors().whiteColor,
                          fontWeight: FontWeight.bold,
                          fontSize: 20),
                    ),
                  ),
                  Container(
                    height: 800,
                    padding: const EdgeInsets.symmetric(
                        horizontal: 30, vertical: 20),
                    child: Row(
                      children: [
                        Expanded(
                            flex: 2,
                            child: Padding(
                              padding: const EdgeInsets.only(right: 15),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("First Name*", firstname, ""),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Email *", email, "Email"),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  titletext("Address"),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  TextFormField(
                                    maxLines: 10,
                                    controller: address,
                                    decoration: const InputDecoration(
                                        hintText: "Address",
                                        border: OutlineInputBorder(
                                            borderSide: BorderSide(
                                                color: Color(0xffC5BDEC)))),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Date of Birth *", dateofbirth, ""),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Place  of Birth *", palaceofbirth, ""),
                                ],
                              ),
                            )),
                        Expanded(
                            flex: 2,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 15),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: [
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Last Name", lastname, ""),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Phone  *", phone, "+91 9876543210"),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  titletext("Photo"),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  GestureDetector(
                                    child: Container(
                                      height: 200,
                                      width: 200,
                                      decoration: BoxDecoration(
                                          borderRadius:
                                              BorderRadius.circular(10),
                                          border: Border.all(
                                              color: Colors.grey.shade300)),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 20,
                                  ),
                                  feild("Adhaar No.", Adhaarno, "Select State"),
                                ],
                              ),
                            ))
                      ],
                    ),
                  )
                ],
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            Card(
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10)),
              child: Container(
                height: 400,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding: const EdgeInsets.only(left: 30),
                      height: 60,
                      width: double.infinity,
                      decoration: BoxDecoration(
                          color: Theme.of(context).primaryColor,
                          borderRadius: const BorderRadius.only(
                              topRight: Radius.circular(10),
                              topLeft: Radius.circular(10))),
                      alignment: Alignment.centerLeft,
                      child: Text(
                        "Education Details",
                        style: TextStyle(
                            color: AppColors().whiteColor,
                            fontWeight: FontWeight.bold,
                            fontSize: 20),
                      ),
                    ),
                    Container(
                      height: 250,
                      padding: const EdgeInsets.symmetric(
                          horizontal: 30, vertical: 20),
                      child: Row(
                        children: [
                          Expanded(
                              flex: 2,
                              child: Padding(
                                padding: const EdgeInsets.only(right: 15),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    const SizedBox(
                                      height: 20,
                                    ),
                                    feild("University", univerty, ""),
                                    const SizedBox(
                                      height: 20,
                                    ),
                                    titletext("Start & End Date *"),
                                    const SizedBox(
                                      height: 10,
                                    ),
                                    Row(
                                      children: [
                                        Expanded(
                                          child: TextFormField(
                                            controller: startdate,
                                            decoration: const InputDecoration(
                                                border: OutlineInputBorder(
                                                    borderSide: BorderSide(
                                                        color: Color(
                                                            0xffC5BDEC)))),
                                          ),
                                        ),
                                        const SizedBox(
                                          width: 20,
                                        ),
                                        Expanded(
                                          child: TextFormField(
                                            controller: enddate,
                                            decoration: const InputDecoration(
                                                border: OutlineInputBorder(
                                                    borderSide: BorderSide(
                                                        color: Color(
                                                            0xffC5BDEC)))),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ],
                                ),
                              )),
                          Expanded(
                              flex: 2,
                              child: Padding(
                                padding: const EdgeInsets.only(left: 15),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  children: [
                                    const SizedBox(
                                      height: 20,
                                    ),
                                    feild("Degree*", degree, ""),
                                    const SizedBox(
                                      height: 20,
                                    ),
                                    feild("City *", city, ""),
                                  ],
                                ),
                              ))
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
            const SizedBox(
              height: 30,
            ),
           Row(
             mainAxisAlignment: MainAxisAlignment.end,
             children: [
               Container(
                 padding:const  EdgeInsets.symmetric(horizontal: 30,vertical: 15),
                 decoration: BoxDecoration(
                   color: Theme.of(context).primaryColor,
                   borderRadius: BorderRadius.circular(30),

                 ),
                 child:  Text("Submit ",style: TextStyle(color: AppColors().lightwhite),),
               ),
             ],
           )
          ],
        ),
      ),
    );
  }
}
