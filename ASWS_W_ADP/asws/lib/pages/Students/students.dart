import 'package:asws/pages/Students/studentdetails.dart';
import 'package:asws/pages/Students/studenttable.dart';
import 'package:asws/utils/appColors.dart';
import 'package:asws/utils/appStrings.dart';
import 'package:flutter/material.dart';
import 'package:hive/hive.dart';

import '../../utils/appbar.dart';
import '../Forms/newstudentForm.dart';
import '../homepage/linegraph.dart';
import 'importstudent.dart';
class Students extends StatefulWidget {
  const Students({Key? key}) : super(key: key);

  @override
  State<Students> createState() => _StudentsState();
}

class _StudentsState extends State<Students> {
  var box = Hive.box('testBox');


  bool detailShow=false;
  var  dropdownValue;
  List<String> list = ['New Student', 'Add Student', 'Import Student',];
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    dropdownValue =list.first ;
  }

  @override
  Widget build(BuildContext context) {

    return detailShow?StudentDetails(seeDetails): dropdownValue=="Add Student"?NewStudentForm(back):dropdownValue=="Import Student"?ImportStudent(back):

    Scaffold(
      appBar:appbarwidget(title:AppStrings().student,context: context),

      body: SingleChildScrollView(
        child: Padding(
          padding:const EdgeInsets.all(20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [

             const  SizedBox(
                height: 30,
              ),
              Row(
                children: [


                  Container(

                    width: 300,
                    height: 50,
                    padding:const EdgeInsets.symmetric(horizontal: 20),
                    decoration: BoxDecoration(
                      color: AppColors().whiteColor,
                      borderRadius: BorderRadius.circular(30),
                    ),
                    child: Row(
                      children: [
                        Padding(padding:const  EdgeInsets.only(right: 20),child:    Icon(Icons.search,color: Theme.of(context).primaryColor,),),
                        Expanded(
                          child: TextFormField(
                            decoration: InputDecoration(
                                hintText: AppStrings().search,

                                border: InputBorder.none
                            ),
                          ),
                        ),
                      ],
                    ),

                  ),
                 Expanded(child: Container())
,              Row(

        children: [

        GestureDetector(
          onTap: (){

            showdailog(context);

          },
          child: Container(
            padding:const  EdgeInsets.symmetric(horizontal: 30,vertical: 10),
          decoration: BoxDecoration(
   border: Border.all(color: Theme.of(context).primaryColor),
          borderRadius: BorderRadius.circular(30),

          ),
          child:  Row(
            children: [
               Icon(Icons.filter_alt,color: Theme.of(context).primaryColor ,),
            const   SizedBox(width: 10,),
              Text("Filter ",style: TextStyle(color: Theme.of(context).primaryColor),),
            ],
          ),
          ),
        ),
        const   SizedBox(width: 20,),

          Container(
            padding:const  EdgeInsets.symmetric(horizontal: 30),
            decoration: BoxDecoration(
              color: Colors.indigo,
              borderRadius: BorderRadius.circular(30),

            ),
            child: Theme(
              data: Theme.of(context).copyWith(
                canvasColor: Colors.indigo,
              ),
              child :DropdownButton<String>(
                value: dropdownValue,
                underline: Container(height: 2,color: Colors.transparent,),

                elevation: 16,
                style: const TextStyle(color: Colors.white),

                onChanged: (String? value) {
                  // This is called when the user selects an item.
                  setState(() {
                    dropdownValue = value!;
                  });
                },
                items: list.map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value),
                  );
                }).toList(),
              ),
            ),
          ),
        ],

        )
                ],
              ),
            const  SizedBox(height: 30,),
              StudentTable(seeDetails)

            ],
          ),
        ),
      ),
    );
  }
  void seeDetails(){
    setState(() {
      detailShow=!detailShow;
    });
  }
  void back(){
    setState(() {
      dropdownValue =list.first;
    });
  }
  void showdailog(context) {
    String? center;
    String? gender;
    String? role;
    bool? zone =false;


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
                            const  Expanded(child:Center(
                              child:  Text("Filters",style: TextStyle(fontSize: 25),),
                            )),
                            IconButton(onPressed: (){
                              Navigator.pop(context);

                            }, icon:const  Icon(Icons.close))
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
                                Expanded(child:  Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    textwidget("Centers"),
                                    const  SizedBox(height: 10,),
                                    Row(
                                      children: [
                                        Radio(
                                            value: "Active", groupValue: center, onChanged: (value){
                                          setState(() {
                                            center=value;

                                          });}),
                                        const  Text("Active")
                                      ],
                                    ),

                                    Row(
                                      children: [
                                        Radio(
                                            value: "Inactive", groupValue: center, onChanged: (value){
                                          setState(() {
                                            center=value;

                                          });}),
                                        const  Text("Inactive")
                                      ],
                                    ),

                                  ],
                                ),),
                                Expanded(child:   Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    textwidget("Gender"),
                                    const  SizedBox(height: 10,),
                                    Row(
                                      children: [
                                        Radio(
                                            value: "Male", groupValue: gender, onChanged: (value){
                                          setState(() {
                                            gender=value;

                                          });}),
                                        const  Text("Male")
                                      ],
                                    ),

                                    Row(
                                      children: [
                                        Radio(
                                            value: "Female", groupValue: gender, onChanged: (value){
                                          setState(() {
                                            gender=value;

                                          });}),
                                        const  Text("Female")
                                      ],
                                    ),

                                  ],
                                ),),
                                Expanded(
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.start,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                      textwidget("Role"),
                                      const  SizedBox(height: 10,),
                                      Row(
                                        children: [
                                          Radio(
                                              value: "Student", groupValue: role, onChanged: (value){
                                            setState(() {
                                              role=value;

                                            });}),
                                          const  Text("Student")
                                        ],
                                      ),

                                      Row(
                                        children: [
                                          Radio(
                                              value: "Teacher", groupValue: role, onChanged: (value){
                                            setState(() {
                                              role=value;

                                            });}),
                                          const  Text("Teacher")
                                        ],
                                      ),

                                    ],
                                  ),
                                )
                              ],
                            ),
                            const  Divider(),
                            textwidget("Zone"),
                            const SizedBox(height: 20,),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("East Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("West Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("North Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

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
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("East Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("West Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("North Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

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
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("East Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("West Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("North Zone")
                                  ],
                                ),
                                Row(
                                  children: [
                                    Checkbox(value:zone , onChanged: (value){
                                      setState(() {
                                        zone=value;

                                      });

                                    }),
                                    Text("South Zone")
                                  ],
                                )

                              ],
                            ),
                            const   Divider(),
                            Padding(padding:const  EdgeInsets.all(10),
                              child:   Row(
                                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                children: [
                                  const Text("Clear",style: TextStyle(decoration: TextDecoration.underline),),
                                  Container(
                                    padding:const EdgeInsets.symmetric(horizontal: 20,vertical: 10),
                                    decoration: BoxDecoration(
                                        color: Colors.indigo,
                                        borderRadius: BorderRadius.circular(30)
                                    ),
                                    alignment: Alignment.center,
                                    child: const Text("Apply",style: TextStyle(color: Colors.white,fontWeight: FontWeight.bold),),
                                  )
                                ],),)

                          ],
                        ),
                      )
                    ],
                  )

              );
            }),
      ),
    );
  }
}
