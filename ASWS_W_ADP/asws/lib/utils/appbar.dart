import 'package:asws/pages/Forms/newstudentForm.dart';
import 'package:flutter/material.dart';
// class AppBarWidget extends StatelessWidget {
//   String title;
//   VoidCallback? ontap;
//   AppBarWidget({required this.title,this.ontap});
//
//
//   @override
//   Widget build(BuildContext context) {
//     var width=MediaQuery.of(context).size.width;
//     return AppBar(
//       elevation: 0,
//       foregroundColor: Colors.black,
//
//       backgroundColor: Colors.transparent,
//       title:GestureDetector(
//         onTap: ontap,
//           child: Text(title,style: Theme.of(context).textTheme.headline1?.copyWith(fontSize:width/40 ))) ,
//       actions: [
//         IconButton(
//             onPressed: (){},
//             icon: const  Icon(Icons.notifications_outlined,size: 35,)),
//         const SizedBox(width: 30,),
//         IconButton(
//             onPressed: (){},
//             icon: const  Icon(Icons.settings_outlined,size: 35,)),
//         const SizedBox(width: 30,),
//         Column(
//           mainAxisAlignment: MainAxisAlignment.center,
//           children:const  [
//             Text("Yahiya Ali",style: TextStyle(color: Colors.deepPurple,fontSize: 20),),
//             Text("Admin"),
//
//           ],
//         ),
//         const SizedBox(width: 20,),
//         const  CircleAvatar(),
//         const SizedBox(width: 20,),
//
//
//
//
//       ],
//     );
//   }
// }
appbarwidget({required String title,required BuildContext context,VoidCallback? ontap,}){
  var width=MediaQuery.of(context).size.width;
  return  AppBar(
    elevation: 0,
    foregroundColor: Colors.black,

    backgroundColor: Colors.transparent,
    title:GestureDetector(
        onTap: ontap,
        child: Text(title,style: Theme.of(context).textTheme.headline1?.copyWith(fontSize:width/40 ))) ,
    actions: [
      IconButton(
          onPressed: (){},
          icon: const  Icon(Icons.notifications_outlined,size: 35,)),
      const SizedBox(width: 30,),
      IconButton(
          onPressed: (){},
          icon: const  Icon(Icons.settings_outlined,size: 35,)),
      const SizedBox(width: 30,),
      Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children:const  [
          Text("Yahiya Ali",style: TextStyle(color: Colors.deepPurple,fontSize: 20),),
          Text("Admin"),

        ],
      ),
      const SizedBox(width: 20,),
      const  CircleAvatar(),
      const SizedBox(width: 20,),




    ],
  );
}