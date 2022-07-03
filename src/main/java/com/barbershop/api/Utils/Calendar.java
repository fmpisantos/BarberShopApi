package com.barbershop.api.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Calendar {
    private static DateFormat dateTemplate = new SimpleDateFormat("YYYY-MM-dd"),
            timeTemplate = new SimpleDateFormat("hh:mm");

    public static JSONArray generateFromStartToEnd(String start, String end, List<Map<String, Object>> reservations, int duration) {
        JSONArray turno = new JSONArray();
        String[] split = start.split(":");
        int startHours = Integer.parseInt(split[0]);
        int startMinutes = Integer.parseInt(split.length > 1 ? split[1] : "0");
        split = end.split(":");
        int endHours = Integer.parseInt(split[0]);
        int endMinutes = Integer.parseInt(split.length > 1 ? split[1] : "0");
        for (int i = startHours; i <= endHours; i++) {
            for (int j = i == startHours ? startMinutes : 0; j < (i == endHours ? endMinutes : 60); j += duration)
                turno.put(new JSONObject().put("hours", i).put("minutes", j).put("string", new StringBuilder().append(String.format("%02d", i)).append(":").append(String.format("%02d", j)).toString()).put("reserved", reservations == null ? false : checkDate(i, j, reservations, duration)));
        }
        return turno;
    }

    public static JSONArray generateCalendarFromDBString(String schedule, List<Map<String, Object>> reservations, int duration) {
        JSONArray jr = new JSONArray();
        String[] turnos = schedule.split(";");
        String[] horas;
        for (String turno : turnos) {
            try {
                horas = turno.split("-");
                List<Object> list = jr.toList();
                list.addAll(generateFromStartToEnd(horas[0], horas[1], reservations, duration).toList());
                jr = new JSONArray(list);
            } catch (Exception ex) {
                System.err.println(String.format("Error generating date for %s", turno));
            }
            ;
        }
        return jr;
    }

    public static boolean localTimeBetween(LocalTime start, LocalTime end, LocalTime between) {
        return between.isAfter(start.minusSeconds(1)) && between.isBefore(end.plusSeconds(1));
    }

    public static boolean checkDate(int hour, int minute, List<Map<String, Object>> reservations, int duration) {
        LocalTime date;
        LocalTime target = LocalTime.parse(new StringBuilder().append(String.format("%02d", hour)).append(":").append(String.format("%02d", minute)).toString());
        for (Map<String, Object> o : reservations) {
            date = parseToLocalTime((Timestamp) o.get("date_time"));
            if (localTimeBetween(target, target.plusMinutes(duration), date) || localTimeBetween(target, target.plusMinutes(duration), date.plusMinutes(parseIntFromObject(o.get("duration"))-1)))
                return true;
        }
        return false;
    }

    public static int parseIntFromObject(Object val) {
        try {
            return (int) val;
        } catch (Exception ex) {
            return 0;
        }
    }

    public static LocalTime parseToLocalTime(int hour, int minute) {
        return LocalTime.parse(new StringBuilder().append(String.format("%02d", hour)).append(":").append(String.format("%02d", minute)).toString());
    }

    public static LocalTime parseToLocalTime(Timestamp date) {
        return parseToLocalTime(date.getHours(), date.getMinutes());
    }

    public static String fromDateToDateTime(Date time) {
        return String.format("%s %s", dateTemplate.format(time), timeTemplate.format(time));
    }

}
