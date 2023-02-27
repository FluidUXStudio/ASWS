import 'package:flutter/material.dart';

import '../../ApiEndPoints/apiendpoint.dart';
import '../../Networking/apiService.dart';
import '../../utils/appColors.dart';
import '../../utils/appStrings.dart';
import '../../utils/appbar.dart';
import 'addcenter.dart';
import 'centerdetails.dart';
import 'centerlist.dart';
import 'importcenter.dart';
class CentersPage extends StatefulWidget {
  const CentersPage({Key? key}) : super(key: key);

  @override
  State<CentersPage> createState() => _CentersPageState();
}

class _CentersPageState extends State<CentersPage> {
  bool detailShow=false;
  bool zone=false;
  var  dropdownValue;
  List<String> list = ['New Center', 'Add Center', 'Import Center',];
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    dropdownValue =list.first ;
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      getcenter();
    });
  }
  getcenter()async{
    var response= await ApiServices().getApiCall(context, ApiEndPoint.getzonenames);
    print("This is respones ==$response");
  }


  @override
  Widget build(BuildContext context) {
    var width=MediaQuery.of(context).size.width;
    return   detailShow?CenterDetails(seeDetails):dropdownValue=="Add Center"?AddCenter(back):dropdownValue=="Import Center"?ImportCenter(back): Scaffold(
    appBar:appbarwidget(title:AppStrings().center,context: context),


   body:  SingleChildScrollView(
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


                Expanded(

                  child: Container(),
                ),
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
            ),
            const  SizedBox(height: 30,),
           const  Text(" Select Zone",style: TextStyle(fontWeight: FontWeight.bold,fontSize: 30),),
            const SizedBox(height: 20,),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Row(
                  children: [
                    Checkbox(value:zone , onChanged: (value){
                      setState(() {
                        zone=value!;

                      });

                    }),
                   const  Text("East Zone")
                  ],
                ),
                Row(
                  children: [
                    Checkbox(value:zone , onChanged: (value){
                      setState(() {
                        zone=value!;

                      });

                    }),
                 const    Text("West Zone")
                  ],
                ),
                Row(
                  children: [
                    Checkbox(value:zone , onChanged: (value){
                      setState(() {
                        zone=value!;

                      });

                    }),
                 const    Text("North Zone")
                  ],
                ),
                Row(
                  children: [
                    Checkbox(value:zone , onChanged: (value){
                      setState(() {
                        zone=value!;

                      });

                    }),
                  const   Text("South Zone")
                  ],
                )

              ],
            ),
            const  SizedBox(height: 30,),
            CenterList(seeDetails),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Text("Showing 1-5 from 100 data",style: Theme.of(context).textTheme.headline1?.copyWith(fontSize: 15),),
                  Expanded(child: Container()),
                  Row(children:const  [
                    Icon(Icons.arrow_left,size: 30,),
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
                    Icon(Icons.arrow_right,size: 30,),
                  ],)

                ],),
            ),


          ],
        ),
      ),
   ));
  }
  void back(){
    setState(() {
      dropdownValue =list.first;
    });
  }
  void seeDetails(){
    setState(() {
      detailShow=!detailShow;
    });
  }
}
