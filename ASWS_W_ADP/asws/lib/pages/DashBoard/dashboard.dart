import 'package:asws/providers/page_provider.dart';
import 'package:asws/utils/appColors.dart';
import 'package:asws/utils/appStrings.dart';
import 'package:flutter/material.dart';
import 'package:hive/hive.dart';
import 'package:provider/provider.dart';

import '../Centers/centers.dart';
import '../Students/students.dart';
import '../Teacher/teachers.dart';
import '../attendence/attendence.dart';
import '../homepage/homepage.dart';
import '../leaderBoard/leaderboard.dart';
import '../../volunteer/volunteer.dart';
import '../sylabus/syllabusScreen.dart';
class DashBoard extends StatefulWidget {
  const DashBoard({Key? key}) : super(key: key);

  @override
  State<DashBoard> createState() => _DashBoardState();
}

class _DashBoardState extends State<DashBoard> {
  final colo=AppColors();
  List<Siderbar> siderbaritems=[];
  List<Widget>widgetbody=[];
  final apst=AppStrings();
  int? selectedIndex=0;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    siderbaritems=[
      Siderbar(title: apst.dashboard, icon: Icons.dashboard),
      Siderbar(title: apst.center, icon: Icons.location_city_outlined),
      Siderbar(title: apst.student, icon: Icons.assignment_ind_outlined),
      Siderbar(title: apst.teacher, icon: Icons.person_outline),
      Siderbar(title: apst.volunteers, icon: Icons.library_books_outlined),
      Siderbar(title: apst.leaderboard, icon: Icons.leaderboard_outlined),
      Siderbar(title: apst.attendence, icon: Icons.fact_check_outlined),
      Siderbar(title: apst.course, icon: Icons.library_books_outlined),
      Siderbar(title: apst.event, icon: Icons.settings_outlined),
      Siderbar(title: apst.setting, icon: Icons.settings_outlined),



    ];
    widgetbody=[
      HomePage(),
      CentersPage(),
      Students(),
      Teachers(),
      VolunteerScreen(),
      LeaderBoard(),
      AttendencePage(),
      SyllabusScreen(),
      Container(),
      Container(),

    ];

  }

  @override
  Widget build(BuildContext context) {
    var page=context.watch<PageProvider>().page;


    var width=MediaQuery.of(context).size.width;


    return Scaffold(

      // small screen
      body: Row(
        children: [
          Expanded(
          flex: 1,
            child: Container(
              height: double.infinity,
            color: Theme.of(context).primaryColor,
              child: SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.only(top: 20,left: 20),
                  child: Column(
                    children: [
                      Row(
                        children: [
                          Container(
                            margin: const EdgeInsets.symmetric(horizontal: 20),
                            height: width/30,
                            width: width/30,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              color: Colors.greenAccent.shade400
                            ),
                            alignment: Alignment.center,
                            child: Text("A",style: TextStyle(fontSize:  width/45,fontWeight: FontWeight.w800,color: colo.whiteColor)),
                          ),
                          Text("ASWS",style: TextStyle(fontSize:  width/45,fontWeight: FontWeight.w800,color: colo.whiteColor),),
                        ],
                      ),
                     const  SizedBox(height: 30,),
                      ListView.builder(
                        shrinkWrap: true,
                        itemCount: siderbaritems.length,
                          itemBuilder: (context,index){
                          return MouseRegion(
                            cursor: SystemMouseCursors.click,
                            child: GestureDetector(
                              onTap: (){
                                setState(() {
                                  context.read<PageProvider>().updatepage(index);
                                  // selectedIndex=index;
                                });

                              },
                              child: Container(
                                margin: EdgeInsets.symmetric(vertical: width/110,),
                                 padding: EdgeInsets.all(width/250),
                               decoration: BoxDecoration(
                                 color:  page==index?colo.whiteColor:Colors.transparent,
                                 borderRadius: const  BorderRadius.only(topLeft:Radius.circular(35),bottomLeft:Radius.circular(35) )
                               ),
                                child:ListTile(
                                  contentPadding: EdgeInsets.zero,
                                  leading: Icon(siderbaritems[index].icon,color:page==index?Theme.of(context).primaryColor: colo.lightwhite,size: width/45,),
                                  title: Text(siderbaritems[index].title,style: TextStyle(color: page==index?Theme.of(context).primaryColor: colo.lightwhite,fontSize: width/75, ),)
                                ) ,
                              ),
                            ),
                          );

                      })
                    ],
                  ),
                ),
              ),

            ),
          ),
    Expanded(
      flex: 5,
    child:widgetbody[page]),

        ],
      ),
    );
  }
}
class Siderbar{
  String title;
  IconData icon;
  Siderbar({required this.title,required this.icon});
}
