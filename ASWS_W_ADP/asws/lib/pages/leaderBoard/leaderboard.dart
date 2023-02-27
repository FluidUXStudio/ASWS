import 'package:asws/utils/appbar.dart';
import 'package:flutter/material.dart';

import '../../utils/appColors.dart';
import '../../utils/appStrings.dart';
import '../homepage/linegraph.dart';
class LeaderBoard extends StatelessWidget {
  const LeaderBoard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      appBar: appbarwidget(title: AppStrings().leaderboard, context: context),
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
                Expanded(child: Text(AppStrings().globalleaderboard,style: Theme.of(context).textTheme.headline5,),),
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
              ],
            ),

            // const   SizedBox(height: 30,),
              // Text(AppStrings().globalleaderboard,style: Theme.of(context).textTheme.headline1,),
              const   SizedBox(height: 10,),
              Card(
                shape: RoundedRectangleBorder(
                  borderRadius:BorderRadius.circular(20)
                ),
                child:
                  Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(20),
                        child: Row(
                          children: const [
                            Expanded(child: Text("Name",style: TextStyle(fontWeight: FontWeight.bold),),),
                            Expanded(child: Text("ID",style: TextStyle(fontWeight: FontWeight.bold),),),
                            Expanded(child: Text("Center",style: TextStyle(fontWeight: FontWeight.bold),),),
                            Expanded(child: Text("Phone No.",style: TextStyle(fontWeight: FontWeight.bold),),),
                            Expanded(child: Text("Email",style: TextStyle(fontWeight: FontWeight.bold),),),
                          ],
                        ),


                      ),
                    ListView.builder(
                      itemCount: 10,
                      shrinkWrap: true,
                        itemBuilder: (context,index){
                      return   Container(
                        padding: const EdgeInsets.all(20),
                        decoration:const BoxDecoration(
                            border: Border(top: BorderSide(color: Colors.grey))
                        ),
                        child: Row(
                          children: [
                            Expanded(
                              child: Row(
                                children:  [
                                  Padding(
                                    padding: const EdgeInsets.only(right: 10),
                                    child: Text((index+1).toString(),style: const  TextStyle(fontWeight: FontWeight.bold,fontSize: 20),),
                                  ),
                               const    CircleAvatar(),
                                  const   SizedBox(width: 10,),
                                  const  Text("Mohd Junaid Khan",style: TextStyle(fontWeight: FontWeight.bold,fontSize: 11),),
                                ],
                              ),
                            ),
                            Expanded(child: Text("#2765234376",style: TextStyle(color: Theme.of(context).primaryColor,fontWeight: FontWeight.bold),)),
                            Expanded(child: Text("Hussaini Masjid",style: TextStyle(color: Theme.of(context).primaryColor,fontWeight: FontWeight.bold),)),
                            Expanded(child: Text("98786543223",style: TextStyle(color: Theme.of(context).primaryColor,fontWeight: FontWeight.bold),)),
                            Expanded(child: Text("emailfivnds@gmail.com",style: TextStyle(color: Theme.of(context).primaryColor,fontWeight: FontWeight.bold),)),



                          ],
                        ),

                      );
                    })
                    ],
                  )

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
