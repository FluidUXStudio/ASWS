// package com.ahlesunnat.asws.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.ahlesunnat.asws.controller.providers.AuthenticationRequest;
// import com.ahlesunnat.asws.controller.providers.AuthenticationResponse;
// import com.ahlesunnat.asws.controller.providers.TokenProvider;

// @RestController
// @RequestMapping("/api/authenticate")
// public class AuthController {

//     private final UserDetailsService userDetailsService;
    
//     private final TokenProvider tokenProvider;
//     // @Autowired
//     // private DaoAuthenticationProvider authenticationManager;

//     public AuthController(UserDetailsService userDetailsService,
//             TokenProvider tokenProvider) {
//         this.userDetailsService = userDetailsService;
//         this.tokenProvider = tokenProvider;
//     }

//     @PostMapping
//     public ResponseEntity<AuthenticationResponse> authenticate(
//             @RequestBody AuthenticationRequest authenticationRequest) {
//         // authenticationManager.authenticate(new
//         // UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
//         // // authenticationRequest.getPassword()));
//         // SecurityContextHolder.getContext()
//         //         .setAuthentication(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//         //                 authenticationRequest.getEmail(), authenticationRequest.getPassword())));
//         UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

//         // Generate a JWT token using TokenProvider
//         String token = tokenProvider.createToken(userDetails);

//         // Create a response with the JWT token and other user details if needed
//         AuthenticationResponse response = new AuthenticationResponse(token, "Bearer", null); // Replace "null" with the
//                                                                                              // token expiration time if
//         return ResponseEntity.ok(response);
//     }

// }
