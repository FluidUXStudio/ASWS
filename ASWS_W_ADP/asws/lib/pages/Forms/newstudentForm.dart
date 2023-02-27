

import 'dart:io';

import 'package:asws/utils/appColors.dart';
import 'package:asws/utils/appbar.dart';
import 'package:flutter/foundation.dart';
import 'package:image_picker/image_picker.dart';
import 'package:flutter/material.dart';
class NewStudentForm extends StatefulWidget {
  VoidCallback ontap;
  NewStudentForm(this.ontap);


  @override
  State<NewStudentForm> createState() => _NewStudentFormState();
}

class _NewStudentFormState extends State<NewStudentForm> {
  TextEditingController addminsionNo=TextEditingController();
  TextEditingController firtname=TextEditingController();
  TextEditingController lastname=TextEditingController();
  TextEditingController email=TextEditingController();
  TextEditingController address=TextEditingController();
  TextEditingController zip=TextEditingController();
  TextEditingController nearbycenter=TextEditingController();
  TextEditingController admissiondate=TextEditingController();
  TextEditingController prevoiusSchool=TextEditingController();
  TextEditingController phone=TextEditingController();
  TextEditingController state=TextEditingController();
  TextEditingController city=TextEditingController();
  TextEditingController dateofbirth=TextEditingController();
  TextEditingController placeofbirth=TextEditingController();
  TextEditingController fullname=TextEditingController();
  TextEditingController age=TextEditingController();
  TextEditingController standard=TextEditingController();
  TextEditingController sid=TextEditingController();
  TextEditingController fatherfirstname=TextEditingController();
  TextEditingController fatherlastname=TextEditingController();
  TextEditingController fatheremail=TextEditingController();
  TextEditingController fathedateofbirth=TextEditingController();
  TextEditingController fatherplaceofbirth=TextEditingController();
  TextEditingController fatheraddress=TextEditingController();
  TextEditingController fatherzipcode=TextEditingController();
  TextEditingController fatherqualification=TextEditingController();
  TextEditingController fatherqualificationopt=TextEditingController();
  TextEditingController fatherphone=TextEditingController();
  TextEditingController fathermedium=TextEditingController();
  TextEditingController fatherstate=TextEditingController();
  TextEditingController fathercity=TextEditingController();
  TextEditingController fatheroccupation=TextEditingController();
  TextEditingController motherfirstname=TextEditingController();
  TextEditingController motherlastname=TextEditingController();
  TextEditingController motheremail=TextEditingController();
  TextEditingController motherdateofbirth=TextEditingController();
  TextEditingController motherplaceofbirth=TextEditingController();
  TextEditingController motheraddress=TextEditingController();
  TextEditingController motherzipcode=TextEditingController();
  TextEditingController motherqualification=TextEditingController();
  TextEditingController motherqualificationopt=TextEditingController();
  TextEditingController motherphone=TextEditingController();
  TextEditingController mothermedium=TextEditingController();
  TextEditingController motherstate=TextEditingController();
  TextEditingController mothercity=TextEditingController();
  TextEditingController motheroccupation=TextEditingController();
  File? userimage;
  Uint8List webImage=Uint8List(8);
  String _currentSelectedzone = "";
  String _currentSelectedcenter = "";


  List<String> zonelist = [
    "center",
    "Hyderabad",
    "Secunderabad",
    "Bandlaguda",
  ];
  bool iselected=false;
  String? gender;
  String? perentalstatus;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _currentSelectedzone="center";
    _currentSelectedcenter="center";

  }

  @override
  Widget build(BuildContext context) {
    var width=MediaQuery.of(context).size.width;
    return Scaffold(
      appBar: appbarwidget(title:"Student >  Add New Student" , context: context,ontap:widget.ontap ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [

             const  SizedBox(height: 30,),

             Card(
               shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
               child: Column(
                 crossAxisAlignment: CrossAxisAlignment.start,
                 children: [
                   Container(
                     padding:const  EdgeInsets.only(left: 30),
                     height: 60,
                    width: double.infinity,
                    decoration: BoxDecoration(
                      color: Theme.of(context).primaryColor,
                        borderRadius:const  BorderRadius.only(topRight: Radius.circular(10),topLeft: Radius.circular(10))
                    ),
                     alignment: Alignment.centerLeft,
                     child:Text("Student Details",style: TextStyle(color: AppColors().whiteColor,fontWeight: FontWeight.bold,fontSize: 20),),
                   ),
                   Container(

                     padding: const EdgeInsets.symmetric(horizontal: 30,vertical: 20),
                     child: Row(
                       children: [
                         Expanded(
                             flex:1,
                         child: Padding(
                           padding:const  EdgeInsets.all(20),
                           child: Column(
                             mainAxisSize: MainAxisSize.min,
                             crossAxisAlignment: CrossAxisAlignment.start,
                             mainAxisAlignment: MainAxisAlignment.start,
                             children: [
                               titletext("Photo *"),
                               const  SizedBox(height: 20,),
                               InkWell(
                                 onTap: (){
                                   pickimage(ImageSource.gallery);
                                 },
                                 child: Container(
                                   height: 150,
                                   width: double.infinity,
                                   decoration: BoxDecoration(
                                       border: Border.all(),
                                       borderRadius: BorderRadius.circular(10)
                                   ),
                                   child: userimage==null?Container():Image.memory(webImage,fit: BoxFit.fill,),
                                 ),
                               ),



                             ],
                           ),
                         )),
                         Expanded(
                             flex:2,
                             child:Padding(
                               padding: const EdgeInsets.only(right: 15),
                               child: Column(
                                 crossAxisAlignment: CrossAxisAlignment.start,
                                 children: [
                                   titletext("Select Zone*"),
                                   const  SizedBox(height: 20,),

                                  Container(
                                    padding: const EdgeInsets.symmetric(horizontal: 10),

                                      decoration: BoxDecoration(
                               border: Border.all(color: Colors.black),

                                      ),
                                    child:  DropdownButtonHideUnderline(
                                      child: DropdownButton<String>(
                                        isExpanded: true,
                                        value: _currentSelectedzone,
                                        icon: const Icon(Icons.arrow_downward),
                                        elevation: 16,
                                        // style: const TextStyle(color: Colors.deepPurple),
                                        underline: Container(
                                          height: 1,
                                          color: Colors.grey,
                                        ),
                                        onChanged: (String? value) {
                                          // This is called when the user selects an item.
                                          setState(() {
                                            _currentSelectedzone = value!;
                                          });
                                        },
                                        items:zonelist
                                            .map<DropdownMenuItem<String>>((String value) {
                                          return DropdownMenuItem<String>(
                                            value: value,
                                            child: Text(value),
                                          );
                                        }).toList(),
                                      ),
                                    ),
                                  ),

                                  const  SizedBox(height: 20,),
                                   feild("First Name *", firtname,"First Name"),
                                   const  SizedBox(height: 20,),
                                   titletext("Date & Place of Birth *"),

                                   Row(
                                     children: [
                                       Expanded(child: Padding(
                                         padding: const EdgeInsets.only(right: 10),
                                         child: feild("", dateofbirth,"Date of Birth"),
                                       )),
                                       Expanded(child: Padding(
                                         padding: const EdgeInsets.only(right: 10),
                                         child: feild("", placeofbirth,"Place of Birth"),
                                       )),
                                     ],
                                   ),
                                   const  SizedBox(height: 20,),
                                   feild("Email *", email,"Email"),
                                   const  SizedBox(height: 20,),
                                   feild("Address *", address,"Address"),
                                   const  SizedBox(height: 20,),
                                   feild("Pin Code *", zip,"Postal Code"),
                                   const  SizedBox(height: 20,),
                                   feild("Adhaar No.", nearbycenter,"9876329823923"),

                                 ],
                               ),
                             )),
                         Expanded(
                             flex:2,
                             child: Padding(
                               padding: const EdgeInsets.only(left: 15),
                               child: Column(
                                 crossAxisAlignment: CrossAxisAlignment.start,
                                 mainAxisAlignment: MainAxisAlignment.start,
                                 children: [
                                   titletext("Select Center*"),
                                   const  SizedBox(height: 20,),

                                   Container(
                                     padding: const EdgeInsets.symmetric(horizontal: 10),

                                     decoration: BoxDecoration(
                                       border: Border.all(color: Colors.black),

                                     ),
                                     child:  DropdownButtonHideUnderline(
                                       child: DropdownButton<String>(
                                         isExpanded: true,
                                         value: _currentSelectedcenter,
                                         icon: const Icon(Icons.arrow_downward),
                                         elevation: 16,
                                         // style: const TextStyle(color: Colors.deepPurple),
                                         underline: Container(
                                           height: 1,
                                           color: Colors.grey,
                                         ),
                                         onChanged: (String? value) {
                                           // This is called when the user selects an item.
                                           setState(() {
                                             _currentSelectedcenter = value!;
                                           });
                                         },
                                         items:zonelist
                                             .map<DropdownMenuItem<String>>((String value) {
                                           return DropdownMenuItem<String>(
                                             value: value,
                                             child: Text(value),
                                           );
                                         }).toList(),
                                       ),
                                     ),
                                   ),
                                   const  SizedBox(height: 20,),
                                   // feild("Admission Date *", admissiondate,""),
                                   const  SizedBox(height: 20,),
                                   feild("Last Name", lastname,"Last Name"),
                                   const  SizedBox(height: 20,),
                                   feild("Previous School *", prevoiusSchool,"School Name"),
                                   const  SizedBox(height: 20,),
                                   feild("Phone  *", phone,"+91 9876543210"),
                                   const  SizedBox(height: 20,),
                                   feild("City", city,"Selct city"),
                                   const  SizedBox(height: 20,),
                                   feild("State", state,"Select State"),
                                   const  SizedBox(height: 40,),



                                 ],

                               ),
                             ))
                       ],
                     ),
                   )
                 ],

               ),
             ),
              const  SizedBox(height: 30,),
              Card(
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding:const  EdgeInsets.only(left: 30),
                      height: 60,
                      width: double.infinity,
                      decoration: BoxDecoration(
                          color: Theme.of(context).primaryColor,
                          borderRadius:const  BorderRadius.only(topRight: Radius.circular(10),topLeft: Radius.circular(10))
                      ),
                      alignment: Alignment.centerLeft,
                      child:Text("Sibling Information",style: TextStyle(color: AppColors().whiteColor,fontWeight: FontWeight.bold,fontSize: 20),),
                    ),
                    Container(
                      height: 360,
                      padding: const EdgeInsets.symmetric(horizontal: 30,vertical: 20),
                      child:  Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          const  SizedBox(height: 20,),
                          Row(
                            children: [
                              Container(
                                margin: const EdgeInsets.only(right: 20),
                                height :10,
                                width: 10,

                                child: Checkbox(value: iselected, onChanged:(value){
                                  setState(() {
                                    iselected = value!;
                                  });

                                }),
                              ),
                              titletext("In case of any sibling ? please click here")
                            ],
                          ),


                          const  SizedBox(height: 40,),
                          titletext("Sibling "),
                          const  SizedBox(height: 20,),

                         Row(
                           children: [
                             Expanded(

                               child: RadioListTile(
                                 title: Text("Brother"),
                                 value: "Brother",
                                 groupValue: gender,
                                 onChanged: (value){
                                   setState(() {
                                     gender=value;

                                   });
                                 },
                               ),
                             ),
                             Expanded(

                               child: RadioListTile(
                                 title: Text("Sister"),
                                 value: "Sister",
                                 groupValue: gender,
                                 onChanged: (value){
                                   setState(() {
                                     gender=value;

                                   });
                                 },
                               ),
                             ),
                             // Expanded(
                             //   flex: 5,
                             //     child: Container())
                           ],
                         ),
                          const  SizedBox(height: 20,),
                          const  SizedBox(height: 20,),
                        Row(
                          children: [
                            Expanded(
                              flex:3,
                                child: feild("Full Name", fullname,"Full Name")),
                            const  SizedBox(width: 20,),
                            Expanded(
                              flex: 1,
                                child: feild("Age", age,"Age")),
                            const  SizedBox(width: 20,),
                            Expanded(
                                flex:3,
                                child: feild("Standard", standard,"Standard")),
                            const  SizedBox(width: 20,),
                            Expanded(
                                flex:3,
                                child: feild("Enter SID Number", sid,"SID Number")),
                            const  SizedBox(width: 20,),
                            Expanded(
                                flex:3,
                                child: TextButton(onPressed: (){},child: Text("Add More"),))
                          ],
                        )

                        ],

                      ),
                    )
                  ],

                ),
              ),
              const  SizedBox(height: 30,),

              Card(
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding:const  EdgeInsets.only(left: 30),
                      height: 60,
                      width: double.infinity,
                      decoration: BoxDecoration(
                          color: Theme.of(context).primaryColor,
                          borderRadius:const  BorderRadius.only(topRight: Radius.circular(10),topLeft: Radius.circular(10))
                      ),
                      alignment: Alignment.centerLeft,
                      child:Text("Family Information",style: TextStyle(color: AppColors().whiteColor,fontWeight: FontWeight.bold,fontSize: 20),),
                    ),
                    Container(

                      padding: const EdgeInsets.symmetric(horizontal: 30,vertical: 20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          titletext("Parental Status "),
                          const  SizedBox(height: 20,),
                          Row(
                            children: [
                              Expanded(


                                child: RadioListTile(
                                  title: Text("Both"),
                                  value: "Both",
                                  groupValue: perentalstatus,
                                  onChanged: (value){
                                    setState(() {
                                      perentalstatus=value;

                                    });
                                  },
                                ),
                              ),
                              Expanded(
                                // flex: 1,

                                child: RadioListTile(
                                  title: Text("Father"),
                                  value: "Father",
                                  groupValue: perentalstatus,
                                  onChanged: (value){
                                    setState(() {
                                      perentalstatus=value;

                                    });
                                  },
                                ),
                              ),
                              Expanded(
                                // flex: 1,

                                child: RadioListTile(

                                  title: Text("Mother"),
                                  value: "Mother",
                                  groupValue: perentalstatus,
                                  onChanged: (value){
                                    setState(() {
                                      perentalstatus=value;

                                    });
                                  },
                                ),
                              ),
                              Expanded(
                                // flex: 1,

                                child: RadioListTile(
                                  title: Text("Guardian"),
                                  value: "Guardian",
                                  groupValue: perentalstatus,
                                  onChanged: (value){
                                    setState(() {
                                      perentalstatus=value;

                                    });
                                  },
                                ),
                              ),
                              // Expanded(
                              //   flex: 5,
                              //     child: Container()
                              // )


                            ],
                          ),
                          const  SizedBox(height: 20,),
                          Row(
                            children: [

                              Expanded(
                                  flex:2,
                                  child:Padding(
                                    padding: const EdgeInsets.only(right: 15),
                                    child: Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: [



                                      Text("Father Information *",style: Theme.of(context).textTheme.headline1,),
                                        const  SizedBox(height: 20,),
                                        feild("First Name*", fatherfirstname,"First Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Email *", fatheremail,"First Name"),
                                        const  SizedBox(height: 20,),
                                        Row(
                                          children: [
                                            Expanded(child: Padding(
                                              padding: const EdgeInsets.only(right: 10),
                                              child: feild("Date & Place of Birth *", fathedateofbirth,"Date of Birth"),
                                            )),
                                            Expanded(child: Padding(
                                              padding: const EdgeInsets.only(right: 10),
                                              child: feild("", fatherplaceofbirth,"Place of Birth"),
                                            )),
                                          ],
                                        ),

                                        const  SizedBox(height: 20,),
                                        feild("Address *", fatheraddress,"Address"),
                                        const  SizedBox(height: 20,),
                                        feild("Zip Code *", fatherzipcode,"Postal Code"),
                                        const  SizedBox(height: 20,),
                                        feild("Education Qualification *", fatherqualification,"Education"),
                                        const  SizedBox(height: 20,),
                                        feild("Education Qualification (Optional)", fatherqualificationopt,"Education"),

                                      ],
                                    ),
                                  )),
                              Expanded(
                                  flex:2,
                                  child: Padding(
                                    padding: const EdgeInsets.only(left: 15),
                                    child: Column(
                                      mainAxisAlignment: MainAxisAlignment.start,
                                      children: [


                                        feild("Last Name", fatherlastname,"Last Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Medium of Instruction *", fathermedium,"School Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Phone  *", fatherphone,"+91 9876543210"),
                                        const  SizedBox(height: 20,),
                                        feild("State", fatherstate,"Select State"),
                                        const  SizedBox(height: 20,),
                                        feild("City", fathercity,"Selct city"),
                                        const  SizedBox(height: 20,),
                                        feild("Occupation", fatheroccupation,"Occupation"),


                                      ],

                                    ),
                                  ))
                            ],
                          ),
                          const  SizedBox(height: 50,),
                          Row(
                            children: [

                              Expanded(
                                  flex:2,
                                  child:Padding(
                                    padding: const EdgeInsets.only(right: 15),
                                    child: Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: [



                                        Text("Mother Information *",style: Theme.of(context).textTheme.headline1,),
                                        const  SizedBox(height: 20,),
                                        feild("First Name*", motherfirstname,"First Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Email *", motheremail,"First Name"),
                                        const  SizedBox(height: 20,),
                                        Row(
                                          children: [
                                            Expanded(child: Padding(
                                              padding: const EdgeInsets.only(right: 10),
                                              child: feild("Date & Place of Birth *", motherdateofbirth,"Date of Birth"),
                                            )),
                                            Expanded(child: Padding(
                                              padding: const EdgeInsets.only(right: 10),
                                              child: feild("", motherplaceofbirth,"Place of Birth"),
                                            )),
                                          ],
                                        ),

                                        const  SizedBox(height: 20,),
                                        feild("Address *", motheraddress,"Address"),
                                        const  SizedBox(height: 20,),
                                        feild("Zip Code *",motherzipcode,"Postal Code"),
                                        const  SizedBox(height: 20,),
                                        feild("Education Qualification *", motherqualification,"Education"),
                                        const  SizedBox(height: 20,),
                                        feild("Education Qualification (Optional)", motherqualificationopt,"Education"),

                                      ],
                                    ),
                                  )),
                              Expanded(
                                  flex:2,
                                  child: Padding(
                                    padding: const EdgeInsets.only(left: 15),
                                    child: Column(
                                      mainAxisAlignment: MainAxisAlignment.start,
                                      children: [


                                        feild("Last Name", motherlastname,"Last Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Medium of Instruction *", mothermedium,"School Name"),
                                        const  SizedBox(height: 20,),
                                        feild("Phone  *", motherphone,"+91 9876543210"),
                                        const  SizedBox(height: 20,),
                                        feild("State", motherstate,"Select State"),
                                        const  SizedBox(height: 20,),
                                        feild("City", mothercity,"Selct city"),
                                        const  SizedBox(height: 20,),
                                        feild("Occupation", motheroccupation,"Occupation"),


                                      ],

                                    ),
                                  ))
                            ],
                          ),
                        ],
                      ),
                    ),

                  ],

                ),
              ),
             const  SizedBox(height: 50,),
              Center(
                child: InkWell(
                  onTap: (){
                  //   {
                  //     "addmissionNumber":68787,
                  //   "addmissionDate":"2002/7/12",
                  //   "firstName":"Sajid ",
                  //   "lastName":"Khan",
                  //   "dateOfBirth":"2002/3/12",
                  //   "placeOfBirth":"ueruige3r",
                  //   "priviousSchool":"masjid e kharwa",
                  //   "email":"cfudgfs@gmail.com",
                  //   "phone":2553828738,
                  //   "address":"euirieg",
                  //   "state":"Hyderabad",
                  //   "zipCode":7878,
                  //   "city":"eirfhieu",
                  //   "nearByCentre":"erhuehr",
                  //   "siblings":true,
                  //   "brOrSis":"brother",
                  //   "siblingFullName":"nfiegfiei",
                  //   "sibAge":65,
                  //   "sibStandard":"fejfhe",
                  //   "sibSidNumber":6890,
                  //   "parentalStatus":"father",
                  //   "parentFirstName":"feugfiegu",
                  //   "parentLastName":"egfiue",
                  //   "parentEmail":"euifiueg",
                  //   "parentPhone":45432453,
                  //   "parentDateOfBirth":"2002/3/12",
                  //   "parentPlaceOfBirth":"fueuifh",
                  //   "mediumOfinstruction":"efiefib",
                  //   "parentAddress":"egigiegg",
                  //   "parentState":"euiiue",
                  //   "parentZipCode":7979,
                  //   "parentCity":"neifgeigfi",
                  //   "educationQualification":"jkefbiebf",
                  //   "occupation":"ejruie"
                  // }
                  },
                  child: Container(

                    padding:const  EdgeInsets.symmetric(horizontal: 20,vertical: 15),
                    decoration: BoxDecoration(
                      color: Theme.of(context).primaryColor,
                      borderRadius: BorderRadius.circular(30),

                    ),

                    child:  Text("Submit ",style: TextStyle(color: AppColors().lightwhite,fontWeight: FontWeight.bold),),
                  ),
                ),
              ),



            ],

          ),
        ),
      ),
    );
  }
  void pickimage(source) async {
    if (kIsWeb) {
      final ImagePicker _picker = ImagePicker();
      XFile? image = await _picker.pickImage(source: ImageSource.gallery);
      if (image != null) {
        var f = await image.readAsBytes();
        setState(() {
          userimage=File(image.path);
          // _file = File("a");
          webImage = f;
        });
      } else {
       print("fail");
      }
    }

  }




}
class titletext extends StatelessWidget {
  String title;
  titletext(this.title);


  @override
  Widget build(BuildContext context) {
    return Text(title,style: Theme.of(context).textTheme.headline1?.copyWith(fontSize: 18),);
  }
}


Widget feild(String title,TextEditingController controller,String hinttext){
  return  Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      titletext(title),
      SizedBox(
        height: 10,
      ),
      TextFormField(
        controller:controller,
        decoration: InputDecoration(
            hintText: hinttext,
            border: OutlineInputBorder(borderSide: BorderSide(color: Color(0xffC5BDEC)))
        ),

      )
    ],

  );
}
