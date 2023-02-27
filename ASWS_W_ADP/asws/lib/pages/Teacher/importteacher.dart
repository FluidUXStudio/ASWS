import 'package:asws/utils/appbar.dart';
import 'package:flutter/material.dart';
class ImportTeacher extends StatefulWidget {
  VoidCallback ontap;
  ImportTeacher(this.ontap);


  @override
  State<ImportTeacher> createState() => _ImportTeacherState();
}

class _ImportTeacherState extends State<ImportTeacher> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appbarwidget(title: "Teacher / Import Teacher", context: context,ontap:  widget.ontap),
      body: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [

              Container(
                height: MediaQuery.of(context).size.height*0.9,
                alignment:Alignment.center,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,

                  children: [
                    Text("Upload your files", style: Theme
                        .of(context)
                        .textTheme
                        .headline1,),
                    const SizedBox(height: 50,),
                    Card(
                      child: Container(
                        padding: const EdgeInsets.all(50),
                        height: 250,
                        width: 400,
                        child: Column(
                          children: [
                            Icon(Icons.file_copy_outlined,size: 100,color: Theme.of(context).primaryColor,),
                            const   SizedBox(height: 20,),
                            const  Text("Drag and drop or Choose a file")
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              )








            ],
          ),





    );
  }
}
