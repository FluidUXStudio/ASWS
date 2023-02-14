import 'package:flutter/material.dart';

import '../../utils/appColors.dart';
class CenterList extends StatelessWidget {
  VoidCallback ontap;
  CenterList(this.ontap);

  @override
  Widget build(BuildContext context) {
    var width=MediaQuery.of(context).size.width;

    return GridView.builder(
        shrinkWrap: true,
        gridDelegate:   SliverGridDelegateWithFixedCrossAxisCount(
          mainAxisSpacing: 20,
          crossAxisSpacing: 20,
          crossAxisCount: 4,
        ),
        itemCount: 12,
        itemBuilder: (BuildContext context, int index) {

          return GestureDetector(
            onTap: ontap,
            child: Card(
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
              child: Container(
                padding:  EdgeInsets.all(width/200),
                child: Column(
                  mainAxisSize: MainAxisSize.min,


                  children: [
                    Row(
                      children: [
                Container(

                height: width/20,
                  width: width/20,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(5),
                    // color: Theme.of(context).primaryColor,
                    image:const  DecorationImage(
                      image: AssetImage("assets/images/masjid.jpeg"),fit: BoxFit.fill
                    )

                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 15),
                  child: Column(crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text("Macca Masjid Center",style: Theme.of(context).textTheme.headline1?.copyWith(fontSize: width/100),overflow: TextOverflow.ellipsis,),
                     const  SizedBox(height: 10,),
                      Row(
                        children: [Icon(Icons.person,color: Colors.grey,size: 12,),Text("Shaik Akbar (Active Volunteer)",style: TextStyle(color: Colors.grey,fontSize:width/150 ),overflow: TextOverflow.ellipsis  ,)
                        ],
                      )


                    ],
                  ),
                )

                      ],
                    )
                   ,
                  const   Divider(),

                    Row(children:  [
                      Expanded(child: ListTile(
                        horizontalTitleGap: 0,
                          contentPadding: EdgeInsets.zero,
                        leading: const Icon(Icons.engineering),
                        title:Text("3 Classes",style: TextStyle(color: Colors.grey,fontSize: width/120),overflow: TextOverflow.ellipsis  ,)
                      )),
                      Expanded(child: ListTile(
                          horizontalTitleGap: 0,
                          contentPadding: EdgeInsets.zero,
                          leading:const  Icon(Icons.person),
                          title:Text("5 Teachers",style: TextStyle(color: Colors.grey,fontSize: width/120),overflow: TextOverflow.ellipsis  ,)
                      ))
                    ],),

                    Row(children:  [
                      Expanded(child: ListTile(
                          horizontalTitleGap: 0,
                          contentPadding: EdgeInsets.zero,
                          leading:const  Icon(Icons.school),
                          title:Text("50 Students",style: TextStyle(color: Colors.grey,fontSize: width/120),overflow: TextOverflow.ellipsis  ,)
                      )),
                      Expanded(child: ListTile(
                          horizontalTitleGap: 0,
                          contentPadding: EdgeInsets.zero,
                          leading:const  Icon(Icons.timer),
                          title:Text("3 Hour/day",style: TextStyle(color: Colors.grey,fontSize: width/120),overflow: TextOverflow.ellipsis  ,)
                      ))
                    ],),
                    SizedBox(height: width/200,),
                    Spacer(),

                    Text("View Center",style: TextStyle(color: AppColors().orange,fontWeight: FontWeight.bold,fontSize: width/90),),
                     SizedBox(height: width/200,),
                  ],

                ),
              ),


            ),
          );
        }
    );
  }
}
