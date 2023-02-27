import 'package:asws/utils/appbar.dart';
import 'package:flutter/material.dart';

import '../../ApiEndPoints/apiendpoint.dart';
import '../../Networking/apiService.dart';
import '../../utils/appColors.dart';
import '../../utils/appStrings.dart';
import '../Students/studentdetails.dart';
import '../Students/studenttable.dart';
import '../homepage/linegraph.dart';
import 'attendenceTable.dart';
class AttendencePage extends StatefulWidget {
  const AttendencePage({Key? key}) : super(key: key);

  @override
  State<AttendencePage> createState() => _AttendencePageState();
}

class _AttendencePageState extends State<AttendencePage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      getattendance();
    });

  }
  getattendance()async{
    var response= await ApiServices().getApiCall(context, ApiEndPoint.getattendance);
    print("This is respones ==$response");
  }

  bool detailShow=false;
  @override
  Widget build(BuildContext context) {
    return detailShow?StudentDetails(seeDetails): Scaffold(
      appBar: appbarwidget(title: AppStrings().attendence, context: context),
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
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [


                  GestureDetector(
                    onTap: (){

                      showdailog(context);

                    },
                    child: Container(
                      padding:const  EdgeInsets.symmetric(vertical: 5,horizontal: 20),
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(30),

                          border: Border.all(color: Colors.indigo,)


                      ),
                      child:const  Text("Filter",style: TextStyle(fontSize: 17,color: Colors.indigo),),
                    ),
                  ),
                  Container(
                    padding:const  EdgeInsets.symmetric(horizontal: 30,vertical: 15),
                    decoration: BoxDecoration(
                      color: Theme.of(context).primaryColor,
                      borderRadius: BorderRadius.circular(30),

                    ),
                    child:  Text("Export CSV File ",style: TextStyle(color: AppColors().lightwhite),),
                  ),

                ],
              ),
              const  SizedBox(height: 30,),
              AttendenceTable(seeDetails)

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
  void seeDetails(){
    setState(() {
      detailShow=!detailShow;
    });
  }
}
