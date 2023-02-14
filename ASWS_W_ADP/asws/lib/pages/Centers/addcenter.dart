import 'package:asws/utils/gesturebutton.dart';
import 'package:flutter/material.dart';

import '../../utils/appColors.dart';

import '../../utils/appbar.dart';
import '../Forms/newstudentForm.dart';
class AddCenter extends StatefulWidget {
  VoidCallback ontap;
  AddCenter(this.ontap);


  @override
  State<AddCenter> createState() => _AddCenterState();
}

class _AddCenterState extends State<AddCenter> {
  TextEditingController centername = TextEditingController();
  TextEditingController email = TextEditingController();
  TextEditingController address = TextEditingController();
  TextEditingController masjidcommitmembername = TextEditingController();
  TextEditingController phone = TextEditingController();
  TextEditingController waqboadno = TextEditingController();


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appbarwidget(title: "Center / Import Center", context: context,ontap: widget.ontap),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [

              const SizedBox(height: 30,),
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
                          color: Theme
                              .of(context)
                              .primaryColor,
                          borderRadius: const BorderRadius.only(
                              topRight: Radius.circular(10),
                              topLeft: Radius.circular(10))
                      ),
                      alignment: Alignment.centerLeft,
                      child: Text("Zone Information", style: TextStyle(
                          color: AppColors().whiteColor,
                          fontWeight: FontWeight.bold,
                          fontSize: 20),),
                    ),
                    Container(
                      height: 560,
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
                                    const SizedBox(height: 20,),
                                    feild("Center Name*", centername, ""),
                                    const SizedBox(height: 20,),
                                    feild("Email *", email, "Email"),
                                    const SizedBox(height: 20,),
                                    titletext("Address"),
                                    const SizedBox(
                                      height: 10,
                                    ),
                                    TextFormField(
                                      maxLines: 10,
                                      controller:address,
                                      decoration: const InputDecoration(
                                          hintText: "Address",
                                          border: OutlineInputBorder(borderSide: BorderSide(color: Color(0xffC5BDEC)))
                                      ),

                                    ),

                                    const SizedBox(height: 20,),

                                  ],
                                ),
                              )),
                          Expanded(
                              flex: 2,
                              child: Padding(
                                padding: const EdgeInsets.only(left: 15),
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.start,
                                  children: [
                                    const SizedBox(height: 20,),
                                    feild("Masjid Committies member name *",
                                        masjidcommitmembername, ""),
                                    const SizedBox(height: 20,),
                                    feild("Phone  *", phone, "+91 9876543210"),
                                    const SizedBox(height: 20,),
                                    feild("Waqt board No", waqboadno,
                                        "Select State"),

                                  ],

                                ),
                              ))
                        ],
                      ),
                    )
                  ],

                ),
              ),
            const   SizedBox(height: 30,),
              titletext("Photo collections *"),
              const   SizedBox(height: 10,),
             const  Card(
                child: SizedBox(
                  height: 400,
                  width: double.infinity,
                ),
              )


            ],
          ),


        ),

      ),
    );
  }
}