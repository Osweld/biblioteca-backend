package sv.edu.ues.bibliotecabackend.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sv.edu.ues.bibliotecabackend.job.DesactivacionUsuarioJob;
import sv.edu.ues.bibliotecabackend.job.NotificationJob;
import sv.edu.ues.bibliotecabackend.job.PrestamoMorosoJob;
import sv.edu.ues.bibliotecabackend.job.UsuarioSancionadoJob;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(NotificationJob.class)
                .withIdentity("notificationJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("notificationTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(3, 0))
                .build();
    }

    @Bean
    public JobDetail usuarioSancionadoJobDetail() {
        return JobBuilder.newJob(UsuarioSancionadoJob.class)
                .withIdentity("usuarioSancionadoJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger usuarioSancionadoTrigger(JobDetail usuarioSancionadoJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(usuarioSancionadoJobDetail)
                .withIdentity("usuarioSancionadoTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(2, 0))
                .build();
    }

    @Bean
    public JobDetail prestamoMorosoJobDetail() {
        return JobBuilder.newJob(PrestamoMorosoJob.class)
                .withIdentity("prestamoMorosoJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger prestamoMorosoTrigger(JobDetail prestamoMorosoJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(prestamoMorosoJobDetail)
                .withIdentity("prestamoMorosoTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(1, 0))
                .build();
    }


    @Bean
    public JobDetail DesactivacionUsuarioJobDetail() {
        return JobBuilder.newJob(DesactivacionUsuarioJob.class)
                .withIdentity("desactivacionUsuarioJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger DesactivacionUsuarioTrigger(JobDetail prestamoMorosoJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(prestamoMorosoJobDetail)
                .withIdentity("desactivacionUsuarioTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(4, 0))
                .build();
    }
}
