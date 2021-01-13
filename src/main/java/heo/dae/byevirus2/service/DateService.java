package heo.dae.byevirus2.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DateService {
    public String format(String date){
        return String.format("%02d", date);
    }

    public LocalDate getStartDate(){
        LocalDate targetStartDate;

        LocalDate localDate = LocalDate.now();
        targetStartDate = localDate.plusDays(-1);

        return targetStartDate;
    }

    public LocalDate getStartDate(LocalDate startDate){
        LocalDate targetStartDate;

        targetStartDate = startDate;

        return targetStartDate;
    }

    public LocalDate getEndDate(){
        LocalDate targetEndDate;

        LocalDate localDate = LocalDate.now();
        targetEndDate = localDate.plusDays(0);

        return targetEndDate;
    }

    public LocalDate getEndDate(LocalDate endDate){
        LocalDate targetEndDate;

        LocalDate localDate = LocalDate.now();

        if (endDate == null) {
            targetEndDate = localDate.plusDays(0);
        } else {
            targetEndDate = endDate;
        }

        return targetEndDate;
    }
}
