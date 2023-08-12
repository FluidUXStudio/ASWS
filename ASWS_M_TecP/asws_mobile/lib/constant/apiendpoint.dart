


class ApiEndPoints {



    
  //   void fetchData() {
  //   String email = 'example@example.com'; // Replace with the actual email
  //   profile.getProfile(context, email).then((result) {
  //     setState(() {
  //       profileData = result; // Store the result in the profileData variable
  //     });
  //   });
  // }

  
// baseurl
  // static String baseurl = "http://100.20.5.236:8084";

  
  static String baseurl = "http://localhost:8084";



  // authenticate
  static String authenticate = "/authenticate";
  // get all student
  static String getallstudent = "/student/getallStudent";
  // add new  student
  static String addnewstudent = "/student/registerNewStudent";
  // add new event
  static String addnewevent = "/api/events";
  // get all events
  static String getallevents = "/api/events";
  // get all zone names
  static String getzonenames = "/api/zones";
  //  submit attendance
  static String submitattendance = "/attendence/createNewAttendence";
}
