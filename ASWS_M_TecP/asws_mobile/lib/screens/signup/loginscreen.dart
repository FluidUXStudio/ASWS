import 'dart:convert';

import 'package:asws_mobile/constant/apiendpoint.dart';
import 'package:asws_mobile/screens/signup/signinscreen.dart';
import 'package:asws_mobile/utils/buttonutils.dart';
import 'package:asws_mobile/utils/loader.dart';
import 'package:asws_mobile/utils/textutils.dart';

import 'package:asws_mobile/providers/profile.dart';
import 'package:asws_mobile/utils/toast.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../providers/attendanceprovider.dart';
import '../../utils/textfeildutils.dart';
import '../attendance/attendencelist.dart';
import '../dashboard/dashboard.dart';
import 'forgetpasswordscreen.dart';

class LoginScreen extends StatelessWidget {
  LoginScreen({Key? key}) : super(key: key);
  final _formKey = GlobalKey<FormState>();
  TextEditingController emailcontroller = TextEditingController();
  TextEditingController passwordcontroller = TextEditingController();
  // Obtain shared preferences.

  @override
  Widget build(BuildContext context) {
    
    return Scaffold(
      body: Form(
        key: _formKey,
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                height: MediaQuery.of(context).size.height * 0.2,
                padding: const EdgeInsets.all(30),
                width: double.infinity,
                color: Colors.grey.shade300,
                alignment: Alignment.bottomLeft,
                child: heading1("Log in"),
              ),
              Container(
                height: MediaQuery.of(context).size.height * 0.8,
                padding: const EdgeInsets.all(20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Feilds(
                      controller: emailcontroller,
                      hinttext: 'Email',
                      ispassword: false,
                      title: "Your Email",
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Feilds(
                      controller: passwordcontroller,
                      hinttext: 'Password',
                      ispassword: true,
                      title: "Password",
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: GestureDetector(
                        onTap: () {
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => ForgetPasswordScreen()));
                        },
                        child: normalgreytext("Forget password?"),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Custombutton(
                        title: "Log in",
                        ontap: () {
                          FocusManager.instance.primaryFocus?.unfocus();
                          // Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context)=>DashBoard()));
                          if (_formKey.currentState!.validate()) {
                            loginApiCall(emailcontroller.text,
                                passwordcontroller.text, context);
                          }
                        }),
                    const SizedBox(
                      height: 20,
                    ),
                    Center(
                      child: GestureDetector(
                        onTap: () {
                          Navigator.of(context).pushReplacement(
                              MaterialPageRoute(
                                  builder: (context) => SignInScreen()));
                        },
                        child: RichText(
                          text: TextSpan(
                            text: "Don't have an account? ",
                            style: const TextStyle(
                                fontWeight: FontWeight.w500,
                                color: Colors.grey),
                            children: <TextSpan>[
                              TextSpan(
                                  text: 'Sign up',
                                  style: TextStyle(
                                      fontWeight: FontWeight.w500,
                                      color: Theme.of(context).primaryColor)),
                            ],
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Row(
                      children: [
                        const Expanded(
                            child: Divider(
                          thickness: 1,
                        )),
                        Expanded(
                            child: Container(
                          margin: const EdgeInsets.symmetric(horizontal: 10),
                          child: normalgreytext("Or login with"),
                        )),
                        const Expanded(
                            child: Divider(
                          thickness: 1,
                        ))
                      ],
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Container(
                          margin: const EdgeInsets.symmetric(horizontal: 20),
                          height: 50,
                          width: 50,
                          child: Image.asset("assets/images/google.png"),
                        ),
                        Container(
                          margin: const EdgeInsets.symmetric(horizontal: 20),
                          height: 50,
                          width: 50,
                          child: Image.asset("assets/images/facebook.png"),
                        )
                      ],
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  void loginApiCall(String email, String password, ctx) async {
    GlobalMethods().showLoader(ctx, true);
    final prefs = await SharedPreferences.getInstance();
    var result;

    var data = {
      "userName": email,
      "userPassword": password,
    };
    var body = json.encode(data);
    final url = Uri.parse(ApiEndPoints.baseurl + ApiEndPoints.authenticate);

    try {
      var response = await http.post(
        url,
        headers: {"Content-Type": "application/json"},
        body: body,
      );

      GlobalMethods().showLoader(ctx, false);

      if (response.body.isNotEmpty) {
        result = json.decode(response.body);
      }

      debugPrint(result.toString());

      if (response.statusCode == 200) {
        debugPrint("Successfully Hit the API");

        debugPrint(result.toString());
        debugPrint("token===${result['jwtToken']}");
        await prefs.setString('token', result['jwtToken']);
        await prefs.setString('email', email);

        Future<dynamic> details = profile.getProfile(ctx, email);

        dynamic data = await details;
        String jsonData = json.encode(data);
        List<dynamic> pdata = jsonDecode(await jsonData);
        for (var pof in pdata) {
          var cs = pof['center'];
          var zs = pof['zone'];
          await prefs.setString('centerId', cs['id'].toString());
          await prefs.setString('teacherId',pof['id'].toString());
          await prefs.setString('zoneId', zs['id'].toString());
        }

        final String? s = prefs.getString('email');

        debugPrint(result.toString());
        debugPrint(s);
        
        Navigator.of(ctx).pushReplacement(
          MaterialPageRoute(builder: (context) => DashBoard()),
        );
      } else {
        showToast("Something went wrong");
        debugPrint("Something went wrong");
      }
    } catch (error) {
      debugPrint(error.toString());
    }
  }
}
