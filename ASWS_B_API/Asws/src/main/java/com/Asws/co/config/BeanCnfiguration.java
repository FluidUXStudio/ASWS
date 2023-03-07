package com.Asws.co.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Asws.co.service.FileServiceImpl;
import com.Asws.co.service.JwtService;
import com.Asws.co.service.demoService;
import com.Asws.co.service.Impl.AttendenceServiceImpl;
import com.Asws.co.service.Impl.CenterServiceImpl;
import com.Asws.co.service.Impl.DashBoardServicecImpl;
import com.Asws.co.service.Impl.EmailServiceImpl;
import com.Asws.co.service.Impl.EventsServiceImpl;
import com.Asws.co.service.Impl.NotificationServiceImpl;
import com.Asws.co.service.Impl.RoleServiceImpl;
import com.Asws.co.service.Impl.StudentServiceImpl;
import com.Asws.co.service.Impl.TeacherAttendenceServiceImpl;
import com.Asws.co.service.Impl.TeacherServiceImpl;
import com.Asws.co.service.Impl.UserImpl;
import com.Asws.co.service.Impl.ZoneServiceImpl;

@Configuration
public class BeanCnfiguration {

  


    @Bean
    public RoleServiceImpl roleServiceImpl(){
        return new RoleServiceImpl();
    }

    @Bean
    public UserImpl userServiceImpl(){
        return new UserImpl();
    }

    @Bean
    public FileServiceImpl fileServiceImpl(){
        return new FileServiceImpl();
    }

    @Bean
    public JwtService jwtService(){
        return new JwtService();
    }

    @Bean
    public StudentServiceImpl studentServiceImpl(){
        return new StudentServiceImpl();
    }

    @Bean
    public TeacherServiceImpl TeacherServiceImpl(){
        return new TeacherServiceImpl();
    }

    @Bean
    public CenterServiceImpl centerServiceImpl(){
        return new CenterServiceImpl();
    }

    @Bean
    public demoService demoService(){
        return new demoService();
}

    @Bean
    public EventsServiceImpl eventsServiceImpl(){
        return new EventsServiceImpl();
    }

    @Bean
    public AttendenceServiceImpl attendenceServiceImpl(){
        return new AttendenceServiceImpl();
    }

    @Bean
    public TeacherAttendenceServiceImpl teacherAttendenceServiceImpl(){
        return new TeacherAttendenceServiceImpl();
    }

    @Bean
    public ZoneServiceImpl zoneServiceImpl(){
        return new ZoneServiceImpl();
    }


    @Bean
    public NotificationServiceImpl notification(){
        return new NotificationServiceImpl();
    }

    @Bean
    public DashBoardServicecImpl dashBoardServicecImpl(){
        return new DashBoardServicecImpl();
    }

    @Bean
    public EmailServiceImpl emailServiceImpl(){
        return new EmailServiceImpl();
    }

}
