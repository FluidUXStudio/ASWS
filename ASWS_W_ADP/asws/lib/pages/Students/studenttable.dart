import 'dart:convert';

import 'package:asws/utils/appColors.dart';
import 'package:flutter/material.dart';
import 'package:hive/hive.dart';

import '../../model/studentmodel.dart';
class StudentTable extends StatefulWidget {
  VoidCallback ontap;
  List<StudentModel>data;
  StudentTable(this.ontap,this.data);

  @override
  State<StudentTable> createState() => _StudentTableState();
}

class _StudentTableState extends State<StudentTable> {
  bool iselected=false;
  @override
  Widget build(BuildContext context) {


    return Card(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(15),
            child: Row(
              children: [
                Expanded(
                  flex:2,
                    child: Text("Name",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Text("ID",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Text("Joining Date",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Text("Parent Name",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Text("City",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Text("Contact",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)),
                Expanded(
                    flex:1,
                    child: Center(child: Text("Class",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),))),
                Expanded(
                    flex:1,
                    child: Center(child: Text("Action",style: TextStyle(color: AppColors().darkpurple,fontWeight: FontWeight.bold),)))
              ],
            ),
          ),
          Divider(),
         widget.data==[]?Text("NO Data "): ListView.builder(

            itemCount:  widget.data.length,
              shrinkWrap: true,
              itemBuilder: (context, index){
            return  Container(
              decoration:  BoxDecoration(
                border: Border.symmetric(horizontal: BorderSide(width: 1,color: Colors.grey.shade100))
              ),
              padding: const EdgeInsets.all(25),
              child: GestureDetector(
                onTap: widget.ontap,
                child: Row(
                  children: [
                    Expanded(
                        flex:2,child: Row(
                      children: [
                         Container(
                           height :10,
                           width: 10,

                           child: Checkbox(value: iselected, onChanged:(value){
                             setState(() {
                               iselected = value!;
                             });

                           }),
                         ),
                        SizedBox(width: 20,),
                        CircleAvatar(radius: 20,
                          backgroundImage:MemoryImage(const Base64Decoder().convert(widget.data[index].photo.toString())),),
                        // Container(
                        //   height: 35,
                        //   width: 35,
                        //   decoration: BoxDecoration(
                        //     borderRadius: BorderRadius.circular(50),
                        //     color: Colors.indigo
                        //   ),
                        // ),
                       const  SizedBox(width: 10,),
                        Text("${widget.data[index].firstName} ${widget.data[index].lastName}",style: TextStyle(color: AppColors().darkbluegrey,fontSize:15,fontWeight: FontWeight.bold))



                      ],
                    )),
                    Expanded(
                        flex:1,
                        child: Text("${widget.data[index].id}",style: TextStyle(color:Theme.of(context).primaryColor,fontWeight: FontWeight.bold),)),
                   const  Expanded(
                       flex:1,
                       child: Text("March 25 2022",style: TextStyle(color: Colors.grey,),)),
                    Expanded(
                        flex:1,
                        child: Text("${widget.data[index].parentFirstName} ${widget.data[index].parentLastName}",style: TextStyle(color: AppColors().darkpurple,),)),
                    Expanded(
                        flex:1,
                        child: Text("${widget.data[index].city}",style: TextStyle(color: AppColors().darkpurple,),)),
                    Expanded(
                        flex:1,
                        child:Row(
                      children: [

                        Container(
                          height:30,
                          width:30,
                          decoration: BoxDecoration(
                            color: Theme.of(context).scaffoldBackgroundColor,
                            borderRadius: BorderRadius.circular(50)
                          ),
                          child: Icon(Icons.call,color: Theme.of(context).primaryColor,),
                        ),
                        const SizedBox(width: 20,),
                        Container(
                          height:30,
                          width:30,
                          decoration: BoxDecoration(
                              color: Theme.of(context).scaffoldBackgroundColor,
                              borderRadius: BorderRadius.circular(50)
                          ),
                          child: Icon(Icons.mail,color: Theme.of(context).primaryColor,),
                        )
                      ],
                    )),
                    Expanded(
                      flex:1,
                      child:  Container(
                      margin:const  EdgeInsets.symmetric(horizontal: 20),
                  height:30,
                  width:100,
                  decoration: BoxDecoration(
                  // color:index.isEven? AppColors().orange:AppColors().yellow,
                  borderRadius: BorderRadius.circular(50)
                  ),
                  alignment:Alignment.center,
                  child:Text("7th",)
                  ),),
                   const Expanded(
                       flex:1,
                       child: Icon(Icons.more_vert,color: Colors.grey,))
                  ],
                ),
              ),
            );

          }),
          const  SizedBox(height: 20,),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text("Showing 1-5 from 100 data",style: Theme.of(context).textTheme.headline1?.copyWith(fontSize: 15),),
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

          const  SizedBox(height: 20,),

        ],
      ),
    );
  }
}
