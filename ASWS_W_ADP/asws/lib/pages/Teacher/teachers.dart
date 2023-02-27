import 'package:asws/pages/Teacher/teacherdetails.dart';
import 'package:asws/pages/Teacher/teacherlist.dart';
import 'package:asws/utils/appbar.dart';
import 'package:flutter/material.dart';

import '../../ApiEndPoints/apiendpoint.dart';
import '../../Networking/apiService.dart';
import '../../utils/appColors.dart';
import '../../utils/appStrings.dart';
import '../Forms/newstudentForm.dart';
import '../homepage/linegraph.dart';
import 'addteacher.dart';
import 'importteacher.dart';

class Teachers extends StatefulWidget {
  const Teachers({Key? key}) : super(key: key);

  @override
  State<Teachers> createState() => _TeachersState();
}

class _TeachersState extends State<Teachers> {
  bool detailShow = false;
  var dropdownValue;
  List<String> list = [
    'New Teacher',
    'Add Teacher',
    'Import Teacher',
  ];
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    dropdownValue = list.first;
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      getteacher();
    });
  }
  getteacher()async{
    var response= await ApiServices().getApiCall(context, ApiEndPoint.getzonenames);
    print("This is respones ==$response");
  }

  @override
  Widget build(BuildContext context) {
    return detailShow
        ? TeacherDetails(seeDetails)
        : dropdownValue == "Add Teacher"
            ? AddTeacher(back)
            : dropdownValue == "Import Teacher"
                ? ImportTeacher(back)
                : Scaffold(
                   appBar: appbarwidget(title:  AppStrings().teacher, context: context),
                  body: SingleChildScrollView(
                      child: Padding(
                        padding: const EdgeInsets.all(20),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [

                            const SizedBox(
                              height: 30,
                            ),
                            Row(
                              children: [
                                Container(
                                  width: 400,
                                  height: 50,
                                  padding: const EdgeInsets.symmetric(
                                      horizontal: 20),
                                  decoration: BoxDecoration(
                                    color: AppColors().whiteColor,
                                    borderRadius: BorderRadius.circular(30),
                                  ),
                                  child: Row(
                                    children: [
                                      Padding(
                                        padding:
                                            const EdgeInsets.only(right: 20),
                                        child: Icon(
                                          Icons.search,
                                          color: Theme.of(context).primaryColor,
                                        ),
                                      ),
                                      Expanded(
                                        child: TextFormField(
                                          decoration: InputDecoration(
                                              hintText: AppStrings().search,
                                              border: InputBorder.none),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                                Expanded(child: Container()),
                                GestureDetector(
                                  onTap: () {
                                    showdailog(context);
                                  },
                                  child: Container(
                                    padding: const EdgeInsets.symmetric(
                                        horizontal: 30, vertical: 10),
                                    decoration: BoxDecoration(
                                      border: Border.all(
                                          color: Theme.of(context)
                                              .primaryColor),
                                      borderRadius:
                                      BorderRadius.circular(30),
                                    ),
                                    child: Row(
                                      children: [
                                        Icon(
                                          Icons.filter_alt,
                                          color: Theme.of(context)
                                              .primaryColor,
                                        ),
                                        const SizedBox(
                                          width: 10,
                                        ),
                                        Text(
                                          "Filter ",
                                          style: TextStyle(
                                              color: Theme.of(context)
                                                  .primaryColor),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                              const   SizedBox(width: 30,),
                                Container(
                                  padding: const EdgeInsets.symmetric(
                                      horizontal: 30),
                                  decoration: BoxDecoration(
                                    color: Colors.indigo,
                                    borderRadius:
                                    BorderRadius.circular(30),
                                  ),
                                  child: Theme(
                                    data: Theme.of(context).copyWith(
                                      canvasColor: Colors.indigo,
                                    ),
                                    child: DropdownButton<String>(
                                      value: dropdownValue,
                                      underline: Container(
                                        height: 2,
                                        color: Colors.transparent,
                                      ),
                                      elevation: 16,
                                      style: const TextStyle(
                                          color: Colors.white),
                                      onChanged: (String? value) {
                                        // This is called when the user selects an item.
                                        setState(() {
                                          dropdownValue = value!;
                                        });
                                      },
                                      items: list
                                          .map<DropdownMenuItem<String>>(
                                              (String value) {
                                            return DropdownMenuItem<String>(
                                              value: value,
                                              child: Text(value),
                                            );
                                          }).toList(),
                                    ),
                                  ),
                                ),
                                // Expanded( child: Container()),
                                // Expanded(
                                //
                                //     child: Row(
                                //       mainAxisAlignment:
                                //           MainAxisAlignment.spaceAround,
                                //       children: [
                                //
                                //
                                //       ],
                                //     ))
                              ],
                            ),
                            const SizedBox(
                              height: 30,
                            ),
                            TeacherList(seeDetails),
                            Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 20),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    "Showing 1-5 from 100 data",
                                    style: Theme.of(context)
                                        .textTheme
                                        .headline1
                                        ?.copyWith(fontSize: 15),
                                  ),
                                  Row(
                                    children: const [
                                      Icon(
                                        Icons.arrow_left,
                                        size: 30,
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: CircleAvatar(
                                          child: Text("1"),
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: CircleAvatar(
                                          child: Text("2"),
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: CircleAvatar(
                                          child: Text("3"),
                                        ),
                                      ),
                                      Icon(
                                        Icons.arrow_right,
                                        size: 30,
                                      ),
                                    ],
                                  )
                                ],
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                );
  }

  void showdailog(context) {
    String? center;
    String? gender;
    String? role;
    bool? zone = false;

    showDialog<String>(
      context: context,
      builder: (BuildContext context) => AlertDialog(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
        contentPadding: EdgeInsets.zero,
        content: StatefulBuilder(
            builder: (BuildContext context, StateSetter setState) {
          return SizedBox(
              width: 700,
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(10),
                    child: Row(
                      children: [
                        const Expanded(
                            child: Center(
                          child: Text(
                            "Filters",
                            style: TextStyle(fontSize: 25),
                          ),
                        )),
                        IconButton(
                            onPressed: () {
                              Navigator.pop(context);
                            },
                            icon: const Icon(Icons.close))
                      ],
                    ),
                  ),
                  const Divider(),
                  Padding(
                    padding: const EdgeInsets.all(30),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Expanded(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  textwidget("Centers"),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Active",
                                          groupValue: center,
                                          onChanged: (value) {
                                            setState(() {
                                              center = value;
                                            });
                                          }),
                                      const Text("Active")
                                    ],
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Inactive",
                                          groupValue: center,
                                          onChanged: (value) {
                                            setState(() {
                                              center = value;
                                            });
                                          }),
                                      const Text("Inactive")
                                    ],
                                  ),
                                ],
                              ),
                            ),
                            Expanded(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  textwidget("Gender"),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Male",
                                          groupValue: gender,
                                          onChanged: (value) {
                                            setState(() {
                                              gender = value;
                                            });
                                          }),
                                      const Text("Male")
                                    ],
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Female",
                                          groupValue: gender,
                                          onChanged: (value) {
                                            setState(() {
                                              gender = value;
                                            });
                                          }),
                                      const Text("Female")
                                    ],
                                  ),
                                ],
                              ),
                            ),
                            Expanded(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  textwidget("Role"),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Student",
                                          groupValue: role,
                                          onChanged: (value) {
                                            setState(() {
                                              role = value;
                                            });
                                          }),
                                      const Text("Student")
                                    ],
                                  ),
                                  Row(
                                    children: [
                                      Radio(
                                          value: "Teacher",
                                          groupValue: role,
                                          onChanged: (value) {
                                            setState(() {
                                              role = value;
                                            });
                                          }),
                                      const Text("Teacher")
                                    ],
                                  ),
                                ],
                              ),
                            )
                          ],
                        ),
                        const Divider(),
                        textwidget("Zone"),
                        const SizedBox(
                          height: 20,
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("East Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("West Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("North Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("South Zone")
                              ],
                            )
                          ],
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("East Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("West Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("North Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("South Zone")
                              ],
                            )
                          ],
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("East Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("West Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("North Zone")
                              ],
                            ),
                            Row(
                              children: [
                                Checkbox(
                                    value: zone,
                                    onChanged: (value) {
                                      setState(() {
                                        zone = value;
                                      });
                                    }),
                                Text("South Zone")
                              ],
                            )
                          ],
                        ),
                        const Divider(),
                        Padding(
                          padding: const EdgeInsets.all(10),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                "Clear",
                                style: TextStyle(
                                    decoration: TextDecoration.underline),
                              ),
                              Container(
                                padding: const EdgeInsets.symmetric(
                                    horizontal: 20, vertical: 10),
                                decoration: BoxDecoration(
                                    color: Colors.indigo,
                                    borderRadius: BorderRadius.circular(30)),
                                alignment: Alignment.center,
                                child: const Text(
                                  "Apply",
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold),
                                ),
                              )
                            ],
                          ),
                        )
                      ],
                    ),
                  )
                ],
              ));
        }),
      ),
    );
  }

  void back() {
    setState(() {
      dropdownValue = list.first;
    });
  }

  void seeDetails() {
    setState(() {
      detailShow = !detailShow;
    });
  }
}
