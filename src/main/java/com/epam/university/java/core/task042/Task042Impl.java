package com.epam.university.java.core.task042;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Task042Impl implements Task042 {
    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }
        TimeProposalResponse proposal = null;
        LocalTime curr = LocalTime.parse(currentTime);

        if (schedule.isEmpty() && isBefore(curr)) {
            proposal = new TimeProposalResponse();
            proposal.setProposedTime("09:00");
            return proposal;
        } else if (schedule.isEmpty() && curr.equals(LocalTime.parse("18:00"))) {
            return new BusyResponse();
        } else if (schedule.isEmpty() && !isNotInTimeRange(curr)) {
            return new AvailableResponse();
        }
        List<LocalTime> timeList = new LinkedList<>();
        for (String str : schedule) {
            timeList.add(LocalTime.parse(str.substring(0, 5)));
            timeList.add(LocalTime.parse(str.substring(6)));
        }
        if (curr.isBefore(timeList.get(0)) && isBefore(curr)) {
            proposal = new TimeProposalResponse();
            proposal.setProposedTime("09:00");
            return proposal;
        }
        boolean isAvailable = true;
        for (int i = 0; i < timeList.size(); i += 2) {
            if (curr.equals(timeList.get(i))) {
                isAvailable = false;
            }
            if (curr.isAfter(timeList.get(i)) && curr.isBefore(timeList.get(i + 1))) {
                isAvailable = false;
            }
        }
        if (isAvailable) {
            return new AvailableResponse();
        }

        Duration duration;
        for (int i = 1; i < timeList.size() - 1; i += 2) {
            duration = Duration.between(timeList.get(i), timeList.get(i + 1));
            long d = duration.toMinutes();
            if (d != 0 && curr.isBefore(timeList.get(i + 1))) {
                proposal = new TimeProposalResponse();
                proposal.setProposedTime(timeList.get(i).toString());
                return proposal;
            }
        }
        if (ifLastSuits(curr, timeList)) {
            proposal = new TimeProposalResponse();
            proposal.setProposedTime(timeList.get(timeList.size() - 1).toString());
            return proposal;
        }

        return new BusyResponse();
    }

    private boolean ifLastSuits(LocalTime curr, List<LocalTime> timeList) {
        return timeList.get(timeList.size() - 1)
                .isBefore(LocalTime.parse("18:00"))
                && curr.isBefore(timeList.get(timeList.size() - 1));
    }

    private boolean isAfter(LocalTime curr) {
        return curr.isAfter(LocalTime.parse("18:00"));
    }

    private boolean isBefore(LocalTime curr) {
        return curr.isBefore(LocalTime.parse("09:00"));
    }

    private boolean isNotInTimeRange(LocalTime curr) {
        return isBefore(curr) || isAfter(curr);
    }
}
