import 'package:flutter/material.dart';

import '../../utils/appStrings.dart';
import '../../utils/appbar.dart';
class SyllabusScreen extends StatelessWidget {
  const SyllabusScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var width=MediaQuery.of(context).size.width;
    print(width/10);
    return  Scaffold(
      appBar: appbarwidget(title: AppStrings().course, context: context),
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
                  Expanded(child: Text("All Subjects",style: Theme.of(context).textTheme.headline5,),),
                  GestureDetector(
                    onTap: (){



                    },
                    child: Container(
                      padding:const  EdgeInsets.symmetric(horizontal: 30,vertical: 10),
                      decoration: BoxDecoration(
                        color: Theme.of(context).primaryColor,
                        border: Border.all(color: Theme.of(context).primaryColor),
                        borderRadius: BorderRadius.circular(30),

                      ),
                      child:   Text("Add Subject ",style: TextStyle(color: Colors.white),),
                    ),
                  ),
                ],
              ),

              // const   SizedBox(height: 30,),
              // Text(AppStrings().globalleaderboard,style: Theme.of(context).textTheme.headline1,),
              const   SizedBox(height: 10,),
          GridView.builder(
              shrinkWrap: true,
              gridDelegate:const   SliverGridDelegateWithFixedCrossAxisCount(
                mainAxisSpacing: 20,
                crossAxisSpacing: 20,
                crossAxisCount: 4,
              ),
              itemCount: 8,
              itemBuilder: (BuildContext context, int index) {
                return GestureDetector(
                  onTap:(){},
                  child: Card(
                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                    child: Container(
                      padding: const EdgeInsets.all(15),
                      child: Column(
                        children:  [
                          FlutterLogo(size: width/10,),

                      const   Spacer(),
                        const   Text("Kalima",style: TextStyle(fontSize: 15,fontWeight: FontWeight.bold),)








                        ],

                      ),
                    ),


                  ),
                );
              }
          ),









            ],
          ),
        ),
      ),
    );
  }
}
