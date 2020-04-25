package functions;

import interfaces.IDoctorActivityGrouping;
import interfaces.IDoctorActivityGroupingInRange;
import interfaces.INonActiveDoctors;
import interfaces.INonActiveDoctorsInTimeRange;

import java.util.Map;

import java.util.stream.Collectors;

public class NonActiveDoctors {

    public static final IDoctorActivityGrouping doctorsActivityGrouping = ((doctors, comments, posts) -> doctors.stream()
            .collect(
                    Collectors.toMap(
                            doctor -> doctor,
                            doctor -> CommonFunctions.getAllCommentsCount.apply(doctor.getUser(), comments) +
                                    CommonFunctions.getAllAnnouncementsCount.apply(doctor.getUser(), posts)
                    )
            )
    );

    public static final INonActiveDoctors nonActiveDocs =
            ((doctors, comments, posts) -> doctorsActivityGrouping.apply(doctors, comments, posts)
                    .entrySet()
                    .stream()
                    .filter(entry -> Long.valueOf(entry.getValue()).equals(Long.valueOf(0)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
            );

    public static final IDoctorActivityGroupingInRange doctorsActivityGroupingInTimeRange = (
            (doctors, comments, posts, startDate, endDate) -> doctors.stream().collect(
                    Collectors.toMap(
                            doctor -> doctor,
                            doctor -> CommonFunctions.getAllCommentsCountInRange.apply(
                                    doctor.getUser(), comments, startDate, endDate
                            ) +
                                    CommonFunctions.getAllAnnouncementsCountInRange.apply(
                                            doctor.getUser(), posts, startDate, endDate
                                    )
                    )
            )
    );






    public static final INonActiveDoctorsInTimeRange nonActiveDocsInTimeRange = (
            (doctors, comments, posts, startDate, endDate) -> doctorsActivityGroupingInTimeRange
                    .apply(doctors, comments, posts, startDate, endDate)
                    .entrySet()
                    .stream()
                    .filter(entry -> Long.valueOf(entry.getValue()).equals(Long.valueOf(0)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
            );

}
