


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
  static String baseurl = "http://asws-env.eba-m7phkd2j.us-east-1.elasticbeanstalk.com";

  
  // static String baseurl = "http://localhost:5000";



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
    static String createTeacher = "/registerNewTeacher";


    static String getallsubjects = "/api/subjects";


  // get all zone names
  static String getzonenames = "/api/zones";
  //  submit attendance
  static String submitattendance = "/attendence/createNewAttendence";
}
