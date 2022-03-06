package com.barbershop.api.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Calendar {
    public static JSONArray generateFromStartToEnd(String start, String end, List<Map<String, Object>> reservations) {
        JSONArray turno = new JSONArray();
        String[] split = start.split(":");
        int startHours = Integer.parseInt(split[0]);
        int startMinutes = Integer.parseInt(split.length > 1 ? split[1] : "0");
        split = end.split(":");
        int endHours = Integer.parseInt(split[0]);
        int endMinutes = Integer.parseInt(split.length > 1 ? split[1] : "0");
        for (int i = startHours; i <= endHours; i++) {
            for (int j = i == startHours ? startMinutes : 0; j < (i == endHours ? endMinutes : 60); j += 15)
                turno.put(new JSONObject().put("hours", i).put("minutes", j).put("string", new StringBuilder().append(String.format("%02d", i)).append(":").append(String.format("%02d", j)).toString()).put("reserved", reservations == null ? false : checkDate(i, j, reservations)));
        }
        return turno;
    }

    public static JSONArray generateCalendarFromDBString(String schedule, List<Map<String, Object>> reservations) {
        JSONArray jr = new JSONArray();
        String[] turnos = schedule.split(";");
        String[] horas;
        for (String turno : turnos) {
            horas = turno.split("-");
            List<Object> list = jr.toList();
            list.addAll(generateFromStartToEnd(horas[0], horas[1], reservations).toList());
            jr = new JSONArray(list);
        }
        return jr;
    }

    public static boolean checkDate(int hour, int minute, List<Map<String, Object>> reservations) {
        int hours;
        int minutes;
        Timestamp date;
        for (Map<String, Object> o : reservations) {
            date = (Timestamp) o.get("date_time");
            hours = date.getHours();
            minutes = date.getMinutes();
            if (hours == hour && minutes == minute)
                return true;
        }
        return false;
    }

}
