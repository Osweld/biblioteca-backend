package sv.edu.ues.bibliotecabackend.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sv.edu.ues.bibliotecabackend.job.SimpleJob;

@Configuration
public class QuartzConfig {

    @Bean
    JobDetail jobDetail() {
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity("simpleJob")
                .storeDurably()
                .build();
    }

    @Bean
    Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("simpleTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(22,34))
                .build();
    }
}
